package sidev.app.android.moviecataloguecompose.core.domain.model

import androidx.annotation.DrawableRes

/*
enum class ImgSrc {
  REMOTE,
  ASSET,
  //FILE,
}
 */

sealed class ImgData {
  abstract val link: Any
}

data class RemoteImg(
  override val link: String,
): ImgData()

data class AssetImg(
  @DrawableRes
  override val link: Int,
): ImgData()