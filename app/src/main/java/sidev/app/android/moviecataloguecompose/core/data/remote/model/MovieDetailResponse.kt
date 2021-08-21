package sidev.app.android.moviecataloguecompose.core.data.remote.model

import com.google.gson.annotations.SerializedName


data class MovieDetailResponse(
  val id: Int,
  val vote_average: Double,
  val vote_count: Int,
  @SerializedName(
    "original_title",
    alternate = ["original_name"],
  )
  val name: String,
  val tagline: String?,
  val overview: String?,
  val homepage: String?,
  val poster_path: String?,
  val backdrop_path: String?,
  @SerializedName(
    "realease_date",
    alternate = ["first_air_date"],
  )
  val date: String?,
  val popularity: Double,
  val runtime: Int?, // Duration in minutes. Null if this is a TV show.
  val genres: List<MovieDetailGenreResponse>,
  val production_companies: List<MovieDetailCompanyResponse>,
)

data class MovieDetailGenreResponse(
  val id: Int,
  val name: String,
)

data class MovieDetailCompanyResponse(
  val id: Int,
  val logo_path: String?,
  val name: String,
  val origin_country: String,
)
