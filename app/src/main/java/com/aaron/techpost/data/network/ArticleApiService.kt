package com.aaron.techpost.data.network

import com.aaron.techpost.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://newsapi.org/"
private const val HEADER_API_KEY = "X-Api-Key:${BuildConfig.API_KEY}"

private val moshi = Moshi.Builder()
    .add(NetworkArticleListJsonAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

/**
 * Retrofit service to fetch articles from the api.
 */
interface ArticleApiService {
    /**
     * Retrieve the list of technology articles the api.
     */
    @WrappedNetworkArticleList
    @Headers(HEADER_API_KEY)
    @GET("v2/top-headlines?category=technology&language=en")
    suspend fun getArticles(): List<NetworkArticle>
}

/**
 * Exposes the ArticleApiService to the rest of the app as a singleton.
 */
object ArticleApi {
    val retrofitService: ArticleApiService by lazy {
        retrofit.create(ArticleApiService::class.java)
    }
}