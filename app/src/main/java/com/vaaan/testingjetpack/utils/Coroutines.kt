package com.vaaan.testingjetpack.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {

    // coroutine for background thread work
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    fun mainScreen(work:(() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

}