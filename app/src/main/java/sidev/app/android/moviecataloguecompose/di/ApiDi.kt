package sidev.app.android.moviecataloguecompose.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sidev.app.android.moviecataloguecompose.core.data.remote.api.MovieApi
import sidev.app.android.moviecataloguecompose.util.Const

object ApiDi {
  var src: ApiDiSrc = ApiDiSrcImpl
}

interface ApiDiSrc {
  fun movie(): MovieApi
}

object ApiDiSrcImpl: ApiDiSrc {
  val client: OkHttpClient by lazy {
    OkHttpClient.Builder()
      //For default API_KEY
      .addInterceptor {
        val oldReq = it.request()
        val oldUrl = oldReq.url()

        val newUrl = oldUrl.newBuilder()
          .addQueryParameter(Const.KEY_API_KEY, Const.API_KEY)
          .build()

        val newReq = oldReq.newBuilder()
          .url(newUrl)
          .build()

        it.proceed(newReq)
      }.build()
  }

  val retrofit: Retrofit by lazy {
    Retrofit.Builder()
      .baseUrl(Const.API_ROOT +"/")
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  override fun movie(): MovieApi =
    retrofit.create(MovieApi::class.java)
}