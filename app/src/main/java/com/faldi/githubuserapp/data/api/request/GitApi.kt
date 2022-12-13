package com.faldi.githubuserapp.data.api.request


import com.google.gson.annotations.SerializedName

data class GitApi(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)