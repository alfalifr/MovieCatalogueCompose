package sidev.app.android.moviecataloguecompose.core.domain.model

data class MovieDetail(
  val movie: Movie,
  val desc: String,
  val genres: List<String>,
)