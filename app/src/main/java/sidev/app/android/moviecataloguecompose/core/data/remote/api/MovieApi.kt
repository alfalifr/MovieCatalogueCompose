package sidev.app.android.moviecataloguecompose.core.data.remote.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sidev.app.android.moviecataloguecompose.core.data.remote.model.MovieCreditResponse
import sidev.app.android.moviecataloguecompose.core.data.remote.model.MovieDetailResponse
import sidev.app.android.moviecataloguecompose.core.data.remote.model.MovieImageResponse
import sidev.app.android.moviecataloguecompose.core.data.remote.model.MovieTrendingResponse
import sidev.app.android.moviecataloguecompose.util.Const

interface MovieApi {

  @GET("${Const.KEY_TRENDING}/{type}/{time}")
  suspend fun getTrendingList(
    @Path("type") type: String = "all",
    @Path("time") time: String = "day",
  ): MovieTrendingResponse


  @GET("${Const.KEY_TV}/popular")
  suspend fun getTvPopular(
    @Query("page") page: Int = 1,
  ): MovieTrendingResponse

  @GET("${Const.KEY_MOVIE}/popular")
  suspend fun getMoviePopular(
    @Query("page") page: Int = 1,
  ): MovieTrendingResponse


  @GET("${Const.KEY_TV}/{id}}")
  suspend fun getTvDetail(
    @Path("id") id: Int,
  ): MovieDetailResponse

  @GET("${Const.KEY_MOVIE}/{id}")
  suspend fun getMovieDetail(
    @Path("id") id: Int,
  ): MovieDetailResponse


  @GET("${Const.KEY_TV}/{id}/images")
  suspend fun getTvImages(
    @Path("id") id: Int,
  ): MovieImageResponse

  @GET("${Const.KEY_MOVIE}/{id}/images")
  suspend fun getMovieImages(
    @Path("id") id: Int,
  ): MovieImageResponse


  @GET("${Const.KEY_TV}/{id}/credits")
  suspend fun getTvCredits(
    @Path("id") id: Int,
  ): MovieCreditResponse

  @GET("${Const.KEY_MOVIE}/{id}/credits")
  suspend fun getMovieCredits(
    @Path("id") id: Int,
  ): MovieCreditResponse
}




