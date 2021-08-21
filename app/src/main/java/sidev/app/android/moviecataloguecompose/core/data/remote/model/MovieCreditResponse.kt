package sidev.app.android.moviecataloguecompose.core.data.remote.model

import com.google.gson.annotations.SerializedName


data class MovieCreditResponse(
  @SerializedName("id")
  val movieId: Int,
  val cast: List<MovieCastResponse>,
  val crew: List<MovieCrewResponse>,
)

data class MovieCastResponse(
  val id: Int,
  val gender: Int,
  val name: String,
  val profile_path: String?,
  val character: String,
  val popularity: Double,
)

data class MovieCrewResponse(
  val id: Int,
  val gender: Int,
  val name: String,
  val profile_path: String?,
  val known_for_department: String,
  val department: String,
  val popularity: Double,
)