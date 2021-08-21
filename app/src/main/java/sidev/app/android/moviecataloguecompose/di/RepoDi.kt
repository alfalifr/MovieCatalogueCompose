package sidev.app.android.moviecataloguecompose.di

import sidev.app.android.moviecataloguecompose.core.data.repo.MovieRepoDummy
import sidev.app.android.moviecataloguecompose.core.data.repo.MovieRepoImpl
import sidev.app.android.moviecataloguecompose.core.domain.repo.MovieRepo

object RepoDi {
  var src: RepoDiSrc = RepoDiSrcImpl //RepoDiSrcDummy
}

interface RepoDiSrc {
  fun movie(): MovieRepo
}

object RepoDiSrcDummy: RepoDiSrc {
  override fun movie(): MovieRepo = MovieRepoDummy
}

object RepoDiSrcImpl: RepoDiSrc {
  override fun movie(): MovieRepo = MovieRepoImpl(
    ApiDi.src.movie(),
  )
}