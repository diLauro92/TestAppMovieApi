package com.dilauro.testmovie.data.dataclasses

data class Movies(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: MutableList<Result>,
    val status: String
)