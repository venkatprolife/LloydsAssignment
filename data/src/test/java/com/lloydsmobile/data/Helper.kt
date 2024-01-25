package com.lloydsmobile.data

import java.io.InputStreamReader

object Helper {
    fun readFileResource(filenane: String): String {
        val inputStrean = Helper::class.java.getResourceAsStream(filenane)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStrean, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}
