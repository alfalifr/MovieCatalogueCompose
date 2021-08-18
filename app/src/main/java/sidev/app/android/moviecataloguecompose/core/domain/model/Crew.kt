package sidev.app.android.moviecataloguecompose.core.domain.model

data class Crew(
  val id: Int,
  val name: String,
  val profile: ImgData?,
  val department: String,
)