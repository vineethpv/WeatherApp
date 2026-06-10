package com.vpvn.weatherapp

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_run_stateflow() {
        runBlocking {
            val stateFlow = MutableStateFlow(1)
            launch {
                stateFlow.collect { println("Collector1 : $it") }
            }
            //delay(100)
            stateFlow.value = 2
            delay(10)
            stateFlow.value = 3

            launch {
                stateFlow.collect { println("Collector2 : $it") }
            }

            stateFlow.value = 4
        }
    }

    @Test
    fun run_test_sharedFlow() {
        runBlocking {
            val sharedFlow = MutableSharedFlow<Int>(replay = 1)
            launch {
                sharedFlow.collect { println("Collector1 : $it") }
            }
            //delay(100)
            sharedFlow.emit(1)
            sharedFlow.emit(2)

            launch {
                sharedFlow.collect { println("Collector2 : $it") }
            }

            sharedFlow.emit(3)
        }
    }

    @Test
    fun run_block_catch() {
        runBlocking {
            val result = runCatching {
                10 / 0
            }
            result.onSuccess {
                println("Success: $it")
            }.onFailure {
                println("Error: ${it.message}")
            }
        }
    }

    @Test
    fun run_test_channel() {
        runBlocking {
            val channel = Channel<Int>()

            launch {
                for (i in 1..5) {
                    channel.send(i)
                    println("Send $i")
                }
                delay(500)
                channel.close()
            }

            launch {
                channel.consumeEach {
                    println("Received $it")
                }
                println("isClosedForSend - $channel.isClosedForSend")
                println("isClosedForReceive - ${channel.isClosedForReceive}")
            }
        }
    }

    @Test
    fun run_launch_async() {
        runBlocking {
            println("Start")
            val job = launch {
                println("Job start")
                delay(200)
                println("Job end")
            }

            val deferred = async {
                println("Async start")
                delay(100)
                println("Async end")
                45
            }
            println("Async Result ${deferred.await()}")
            job.join()
            println("End")
        }
    }

    @Test
    fun check_supervisorScope_behaviour() {
        runBlocking {
            val customScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
            customScope.launch {
                launch {
                    delay(100)
                    println("Child one")
                }
                launch {
                    throw RuntimeException("Child two failed")
                }
            }
        }
    }

    @Test
    fun check_code_snippets1() {
        runBlocking {
            println("Start")
            val scope = CoroutineScope(SupervisorJob())

            scope.launch {
                launch {
                    delay(100)
                    println("Child 1 completed")
                }

                launch {
                    delay(50)
                    println("Child 2 throwing exception")
                    throw RuntimeException("Error in Child 2")
                }

                launch {
                    delay(150)
                    println("Child 3 completed")
                }
            }

            delay(300)
            println("End")
        }
    }

    @Test
    fun check_code_snippets2() {
        runBlocking {
            println("Start")

            try {
                coroutineScope {
                    launch {
                        delay(100)
                        println("Child 1")
                    }

                    launch {
                        delay(50)
                        println("Child 2 fails")
                        throw RuntimeException("Boom")
                    }
                }
            } catch (e: Exception) {
                println("Caught ${e.message}")
            }

            println("End")
        }
    }

    @Test
    fun check_code_snippet3() {
        runBlocking {
            println("Start")

            val deferred = async {
                println("Async started")
                throw RuntimeException("Failure in Async")
            }

            try {
                println("Before await")
                deferred.await() //“async captures exceptions and rethrows them on await().”
                println("After await")
            } catch (e: Exception) {
                println("Caught: ${e.message}")
            }
            println("End")
        }
    }

    //“launch propagates exceptions immediately and works with CoroutineExceptionHandler,
    // while async captures exceptions and requires await() to handle them.
    // SupervisorJob isolates failures but doesn’t suppress exceptions.”

    @Test
    fun check_code_snippet4() {
        runBlocking {
            println("Start")

            async {
                println("Async started")
                throw RuntimeException("Boom!") //App crashes
                // "End" may NOT print (depends on timing)
            }

            delay(100)
            println("End")
        }
    }

    @Test
    fun snippet4() {
        runBlocking {
            val handler = CoroutineExceptionHandler { context, throwable ->
                println("Handler caught: ${throwable.message}")
            }
            val scope = CoroutineScope(Job() + handler)
            scope.launch { throw RuntimeException("Launch Failed") }
            delay(100)
        }
    }

    @Test
    fun snippet5() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, e ->
                println("Handler caught: ${e.message}")
            }

            val scope = CoroutineScope(Job() + handler)

            val deferred = scope.async {
                throw RuntimeException("Async failed")
            }

            delay(100)
        }
    }

    @Test
    fun snippet6() {
        runBlocking {
            val scope = CoroutineScope(SupervisorJob())

            val d1 = scope.async {
                delay(100)
                println("Task 1 done")
                "Result 1"
            }

            val d2 = scope.async {
                delay(50)
                println("Task 2 fails")
                //throw RuntimeException("Error in Task 2")
            }

            try {
                println(d1.await())
                println(d2.await())
            } catch (e: Exception) {
                println("Caught: ${e.message}")
            }
        }
    }

    @Test
    fun snippet7() {
        runBlocking {
            try {
                launch {//launch is asynchronous ,try-catch only works for synchronous code
                    throw RuntimeException("Boom inside launch")
                }
            } catch (e: Exception) {
                println("Caught: ${e.message}")
            }

            delay(100)
        }
    }
}