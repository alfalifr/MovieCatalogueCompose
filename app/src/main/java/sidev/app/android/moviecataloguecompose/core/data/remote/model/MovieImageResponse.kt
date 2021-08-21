package sidev.app.android.moviecataloguecompose.core.data.remote.model

import com.google.gson.annotations.SerializedName


data class MovieImageResponse(
  @SerializedName("id")
  val movieId: Int,
  val backdrops: List<MovieImageDataResponse>,
  val logos: List<MovieImageDataResponse>,
  val posters: List<MovieImageDataResponse>,
)

data class MovieImageDataResponse(
  val aspect_ratio: Double,
  val height: Int,
  val width: Int,
  val file_path: String,
  val vote_average: Double,
  val vote_count: Int,
)