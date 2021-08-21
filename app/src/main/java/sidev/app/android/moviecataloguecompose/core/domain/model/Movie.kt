package sidev.app.android.moviecataloguecompose.core.domain.model

import java.util.*

data class Movie(
  val id: Int,
  val title: String,
  val date: Date?,
  val voteAverage: Number,
  val voteCount: Int,
  val poster: ImgData?,
  val type: String,
)