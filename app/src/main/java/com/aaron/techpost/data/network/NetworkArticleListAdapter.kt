package com.aaron.techpost.data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

/**
 * Annotation for [NetworkArticleListJsonAdapter] to mark custom deserialization of
 * received data from the api.
 */
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WrappedNetworkArticleList

/**
 * Models the json object received from the api and then retrieves only the nested
 * 'articles' json array.
 */
data class NetworkArticleList(val articles: List<NetworkArticle>)

/**
 * Custom Json adapter for unpacking the nested 'articles' json array from
 * the api's json object response.
 */
class NetworkArticleListJsonAdapter {
    @WrappedNetworkArticleList
    @FromJson
    fun fromJson(json: NetworkArticleList): List<NetworkArticle> {
        return json.articles
    }

    @ToJson
    fun toJson(@WrappedNetworkArticleList value: List<NetworkArticle>): NetworkArticleList {
        throw UnsupportedOperationException("NetworkArticle to Json conversion is not supported")
    }
}