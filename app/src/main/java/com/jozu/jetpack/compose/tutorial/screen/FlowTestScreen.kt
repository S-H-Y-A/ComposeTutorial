package com.jozu.jetpack.compose.tutorial.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 *
 * Created by jozuko on 2023/07/20.
 * Copyright (c) 2023 Studio Jozu. All rights reserved.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowTestScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Button1()
                Button2()
                Button3()
                Button4()
            }
        }
    }
}

@Composable
private fun ResultText(state: FlowFuture<String>) {
    val text = when (state) {
        is FlowFuture.Idle -> "Idle"
        is FlowFuture.Success -> "Success(${state.value})"
        is FlowFuture.Proceeding -> "Proceeding(${state.value})"
        is FlowFuture.Error -> "Error(${state.error.localizedMessage})"
    }
    Log.d("ResultText", text)
    Text(text)
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun Button1() {
    val coroutineScope = rememberCoroutineScope()
    val buttonState: MutableState<FlowFuture<String>> = remember { mutableStateOf(FlowFuture.Idle) }

    Button(
        onClick = {
            coroutineScope.launch {
                FlowTest.flow1().collect {
                    buttonState.value = it
                }
            }
        },
    ) {
        Text("button1")
    }
    ResultText(buttonState.value)
}

@Composable
private fun Button2() {
    val coroutineScope = rememberCoroutineScope()
    val buttonState: MutableState<FlowFuture<String>> = remember { mutableStateOf(FlowFuture.Idle) }

    Button(
        onClick = {
            coroutineScope.launch {
                FlowTest.flow2().collect {
                    buttonState.value = it
                }
            }
        },
    ) {
        Text("button2")
    }
    ResultText(buttonState.value)
}

@Composable
private fun Button3() {
    val coroutineScope = rememberCoroutineScope()
    val buttonState: MutableState<FlowFuture<String>> = remember { mutableStateOf(FlowFuture.Idle) }

    Button(
        onClick = {
            coroutineScope.launch {
                FlowTest.flow3().collect {
                    buttonState.value = it
                }
            }
        },
    ) {
        Text("button3")
    }
    ResultText(buttonState.value)
}

@Composable
private fun Button4() {
    val coroutineScope = rememberCoroutineScope()
    val buttonState: MutableState<FlowFuture<String>> = remember { mutableStateOf(FlowFuture.Idle) }

    Button(
        onClick = {
            coroutineScope.launch {
                FlowTest.flow4().collect {
                    buttonState.value = it
                }
            }
        },
    ) {
        Text("button4")
    }
    ResultText(buttonState.value)
}

sealed class FlowFuture<out T> {
    object Idle : FlowFuture<Nothing>()
    data class Proceeding(val value: String) : FlowFuture<String>()
    data class Success(val value: String) : FlowFuture<String>()
    data class Error(val error: Throwable) : FlowFuture<Nothing>()
}

object FlowTest {
    fun flow1(): Flow<FlowFuture<String>> {
        return flow<FlowFuture<String>> {
            Log.d("FlowTest", "flow1")
            delay(10L)
            throw IllegalArgumentException("1")
        }.onStart {
            Log.d("FlowTest", "flow1 onStart")
            emit(FlowFuture.Proceeding("1"))
        }.catch {
            Log.d("FlowTest", "flow1 catch")
            emit(FlowFuture.Error(it))
        }.flowOn(Dispatchers.IO)
    }

    fun flow2(): Flow<FlowFuture<String>> {
        return flow<FlowFuture<String>> {
            Log.d("FlowTest", "flow2")
            delay(10L)
            emit(FlowFuture.Success("1"))
        }.map {
            Log.d("FlowTest", "flow2 map")
            throw IllegalArgumentException("2")
            it
        }.onStart {
            Log.d("FlowTest", "flow2 onStart")
            emit(FlowFuture.Proceeding("1"))
        }.catch {
            Log.d("FlowTest", "flow2 catch")
            emit(FlowFuture.Error(it))
        }.flowOn(Dispatchers.IO)
    }

    fun flow3(): Flow<FlowFuture<String>> {
        return flow<FlowFuture<String>> {
            Log.d("FlowTest", "flow3")
            delay(10L)
            emit(FlowFuture.Success("1"))
        }.map {
            Log.d("FlowTest", "flow3 map 1")
            throw IllegalArgumentException("2")
            it
        }.map {
            Log.d("FlowTest", "flow3 map 2")
            it
        }.onStart {
            Log.d("FlowTest", "flow3 onStart")
            emit(FlowFuture.Proceeding("1"))
        }.catch {
            Log.d("FlowTest", "flow3 catch")
            emit(FlowFuture.Error(it))
        }.flowOn(Dispatchers.IO)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun flow4(): Flow<FlowFuture<String>> {
        return flow<FlowFuture<String>> {
            Log.d("FlowTest", "flow4")
            delay(10L)
//            throw IllegalArgumentException("1")
            emit(FlowFuture.Success("1"))
        }.flatMapConcat {
            flowTest()
        }.flatMapConcat {
            flow<FlowFuture<String>> {
                Log.d("FlowTest", "flow4 flatMapConcat 2")
                delay(10L)
                throw IllegalArgumentException("3")
                emit(FlowFuture.Success("3"))
            }
        }.onStart {
            Log.d("FlowTest", "flow4 onStart")
            emit(FlowFuture.Proceeding("1"))
        }.catch {
            Log.d("FlowTest", "flow4 catch")
            emit(FlowFuture.Error(Throwable("1-${it.localizedMessage}")))
        }.flowOn(Dispatchers.IO)
    }

    private fun flowTest(): Flow<FlowFuture<String>> {
        return flow<FlowFuture<String>> {
            Log.d("FlowTest", "flow4 flatMapConcat 1")
            delay(10L)
            throw IllegalArgumentException("2")
            emit(FlowFuture.Success("2"))
        }
    }
}