package com.petrlr14.mvvm.services.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.petrlr14.mvvm.database.entities.GitHubRepo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


const val GITHUB_BASE_URL = "https://api.github.com/"

interface GithubService {

    /*
        Deferred para que se ejecute de manera asincrona y poder hacer .await
    * */
    @GET("/users/{user}/repos")
    fun getAllReposPerUser(@Path("user")user:String):Deferred<Response<List<GitHubRepo>>>

    @POST("")
    @FormUrlEncoded
    fun funcion()

    companion object{
        /*
        * CallAdapterFactory para poder devolver Deferred y poder usar .await
        * este metodo debe estar en cada servicio que se tenga en el proyecto
        * */
        fun getGithubServices():GithubService = Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(GithubService::class.java)
    }
}