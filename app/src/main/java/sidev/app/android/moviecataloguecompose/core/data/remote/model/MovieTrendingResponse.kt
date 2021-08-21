package sidev.app.android.moviecataloguecompose.core.data.remote.model

import com.google.gson.annotations.SerializedName


data class MovieTrendingResponse(
  val page: Int,
  val results: List<MovieTrendingDataResponse>,
)

data class MovieTrendingDataResponse(
  val id: Int,
  val vote_average: Double,
  val vote_count: Int,
  @SerializedName(
    "original_title",
    alternate = ["original_name"],
  )
  val name: String,
  val overview: String,
  val poster_path: String?,
  @SerializedName(
    "release_date",
    alternate = ["first_air_date"],
  )
  val date: String?,
  val popularity: Double,
  val media_type: String?,
)