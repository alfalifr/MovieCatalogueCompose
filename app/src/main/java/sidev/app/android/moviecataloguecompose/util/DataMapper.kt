package sidev.app.android.moviecataloguecompose.util

import android.util.Log
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import sidev.app.android.moviecataloguecompose.core.data.remote.model.*
import sidev.app.android.moviecataloguecompose.core.domain.model.*

fun MovieTrendingResponse.toDomainModels(
  type: String,
): List<Movie> = results.map {
  Movie(
    id = it.id,
    title = it.name,
    voteAverage = it.vote_average,
    voteCount = it.vote_count,
    date = it.date?.let { stdApiDateFormat.parse(it) },
    poster = RemoteImg(
      Const.ENDPOINT_IMG_300x450 +it.poster_path
    ),
    type = type,
  )
}

fun MovieDetailResponse.toDomainModel(
  type: String,
) = Movie(
  id = id,
  title = name,
  voteAverage = vote_average,
  voteCount = vote_count,
  date = date?.let { stdApiDateFormat.parse(it) },
  poster = RemoteImg(
    Const.ENDPOINT_IMG_300x450 +poster_path
  ),
  type = type,
)

fun MovieDetailResponse.toDomainDetailModel(
  type: String,
  casts: List<MovieCastResponse>,
  crews: List<MovieCrewResponse>,
  posters: List<MovieImageDataResponse>,
  backdrops: List<MovieImageDataResponse>,
  logos: List<MovieImageDataResponse>,
) = MovieDetail(
  movie = toDomainModel(type),
  tagline = tagline,
  homepage = homepage,
  overview = overview,
  duration = runtime,
  genres = genres.map { it.name },
  productionCompanies = production_companies.map { it.toDomainModel() },
  casts = casts.map { it.toDomainModel() },
  crews = crews.map { it.toDomainModel() },
  posters = posters.map { it.toPosterDomainModel() },
  backdrops = backdrops.map { it.toBackdropDomainModel() },
  logos = logos.map { it.toLogoDomainModel() },
)


fun MovieImageDataResponse.toPosterDomainModel() = RemoteImg(
  Const.ENDPOINT_IMG_300x450 +file_path
)
fun MovieImageDataResponse.toBackdropDomainModel() = RemoteImg(
  Const.ENDPOINT_IMG_533x300 +file_path
)
//TODO: I don't know the exact size of logo dimension.
fun MovieImageDataResponse.toLogoDomainModel() = RemoteImg(
  Const.ENDPOINT_IMG_276x350_FACE +file_path
)

fun MovieDetailCompanyResponse.toDomainModel() = Company(
  id = id,
  logo = logo_path?.let { RemoteImg(it) },
  name = name,
)

fun MovieCastResponse.toDomainModel() = Cast(
  id = id,
  name = name,
  character = character,
  profile = profile_path?.let {
    RemoteImg(
      Const.ENDPOINT_IMG_276x350_FACE +it
    )
  },
)

fun MovieCrewResponse.toDomainModel() = Crew(
  id = id,
  name = name,
  department = known_for_department,
  profile = profile_path?.let {
    RemoteImg(
      Const.ENDPOINT_IMG_276x350_FACE +it
    )
  }
)



fun List<Crew>.getDirectorNames(): List<String> = asSequence()
  .filter { it.department.lowercase(java.util.Locale.ENGLISH) == Const.POSITION_DIRECTOR }
  .map { it.name }
  .collectFirst(3)

fun List<Crew>.getProducerNames(): List<String> = asSequence()
  .filter { it.department.lowercase(java.util.Locale.ENGLISH) == Const.POSITION_PRODUCER }
  .map { it.name }
  .collectFirst(3)