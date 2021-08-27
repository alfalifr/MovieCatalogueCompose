package sidev.app.android.moviecataloguecompose.core.data.repo

import sidev.app.android.moviecataloguecompose.core.data.dummyMovieList
import sidev.app.android.moviecataloguecompose.core.data.getDummyMovieDetail
import sidev.app.android.moviecataloguecompose.core.data.remote.api.MovieApi
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieRepo
import sidev.app.android.moviecataloguecompose.util.Const
import sidev.app.android.moviecataloguecompose.util.toDomainDetailModel
import sidev.app.android.moviecataloguecompose.util.toDomainModels
import kotlin.math.log

object MovieRepoDummy: MovieRepo {
  override suspend fun getTvPopular(): List<Movie> = dummyMovieList
  override suspend fun getMoviePopular(): List<Movie> = dummyMovieList

  override suspend fun getTrendingList(): List<Movie> = dummyMovieList

  override suspend fun getTvDetail(id: Int): MovieDetail = getDummyMovieDetail(id)
  override suspend fun getMovieDetail(id: Int): MovieDetail = getDummyMovieDetail(id)
}

class MovieRepoImpl(
  private val movieApi: MovieApi,
): MovieRepo {

  override suspend fun getTvPopular(): List<Movie> =
    movieApi.getTvPopular().toDomainModels(Const.KEY_TV)

  override suspend fun getMoviePopular(): List<Movie> =
    movieApi.getMoviePopular().toDomainModels(Const.KEY_MOVIE)

  override suspend fun getTrendingList(): List<Movie> =
    movieApi.getTrendingList().toDomainModels()

  override suspend fun getTvDetail(id: Int): MovieDetail {
    val detail = movieApi.getTvDetail(id)
    val images = movieApi.getTvImages(id)
    val credits = movieApi.getTvCredits(id)

    return detail.toDomainDetailModel(
      type = Const.KEY_TV,
      casts = credits.cast,
      crews = credits.crew,
      posters = images.posters,
      backdrops = images.backdrops,
      logos = images.logos,
    )
  }

  override suspend fun getMovieDetail(id: Int): MovieDetail {
    val detail = movieApi.getMovieDetail(id)
    val images = movieApi.getMovieImages(id)
    val credits = movieApi.getMovieCredits(id)

    return detail.toDomainDetailModel(
      type = Const.KEY_MOVIE,
      casts = credits.cast,
      crews = credits.crew,
      posters = images.posters,
      backdrops = images.backdrops,
      logos = images.logos,
    )
  }
}