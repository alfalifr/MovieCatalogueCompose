package sidev.app.android.moviecataloguecompose.di

import sidev.app.android.moviecataloguecompose.ui.page.detail.DetailViewModel
import sidev.app.android.moviecataloguecompose.ui.page.list.ListViewModel

object ViewModelDi {
  var src: ViewModelDiSrc = ViewModelDiSrcImpl
}

interface ViewModelDiSrc {
  fun list(): ListViewModel
  fun detail(): DetailViewModel
}

object ViewModelDiSrcImpl: ViewModelDiSrc {
  override fun list(): ListViewModel = ListViewModel(
    RepoDi.src.movie()
  )
  override fun detail(): DetailViewModel = DetailViewModel(
    RepoDi.src.movie()
  )
}