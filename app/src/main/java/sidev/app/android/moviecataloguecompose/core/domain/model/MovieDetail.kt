package sidev.app.android.moviecataloguecompose.core.domain.model

data class MovieDetail(
  val movie: Movie,
  val tagline: String?,
  val overview: String,
  val homepage: String,
  val duration: Number?, // Duration in minutes. Null if this is a TV show.
  val genres: List<String>,
  val productionCompanies: List<Company>,
  val casts: List<Cast>,
  val crews: List<Crew>,
  val posters: List<ImgData>,
  val backdrops: List<ImgData>,
  val logos: List<ImgData>,
)