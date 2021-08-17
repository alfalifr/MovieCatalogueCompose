package sidev.app.android.moviecataloguecompose.core.domain.model

import java.util.*

data class Movie(
  val id: Int,
  val title: String,
  val release: Date,
  val posterUrl: String,
)