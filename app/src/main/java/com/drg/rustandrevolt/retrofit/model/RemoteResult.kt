package com.drg.rustandrevolt.retrofit.model

data class RemoteResult(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)