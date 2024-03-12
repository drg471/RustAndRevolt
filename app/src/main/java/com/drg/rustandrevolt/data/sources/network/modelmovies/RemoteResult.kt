package com.drg.rustandrevolt.data.sources.network.modelmovies

data class RemoteResult(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)