package com.sbz.code_piler.DataModel

data class ApiResponse(
    val error: String,
    val info: String,
    val language: String,
    val output: String,
    val status: Int,
    val timeStamp: Long
)