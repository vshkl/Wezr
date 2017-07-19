package by.vshkl.android.wezr.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object BoilerplateServiceFactory {

    fun makeBoilerplateService(): BoilerplateService {
        return makeService()
    }

    private fun makeService(): BoilerplateService {
        val retrofit = Retrofit.Builder()
                .baseUrl("")
                .client(makeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(BoilerplateService::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

}