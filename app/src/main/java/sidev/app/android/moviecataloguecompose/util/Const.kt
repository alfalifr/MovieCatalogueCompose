package sidev.app.android.moviecataloguecompose.util

import sidev.app.android.moviecataloguecompose.BuildConfig

object Const {
  const val SCORE_SCALE = 10 //100

  const val API_ROOT = "https://api.themoviedb.org/3"
  const val API_KEY = BuildConfig.API_KEY

  const val ENDPOINT_PUBLIC = "https://themoviedb.org"
  const val ENDPOINT_IMG = "$ENDPOINT_PUBLIC/t/p"
  const val ENDPOINT_IMG_300x450 = "$ENDPOINT_IMG/w300_and_h450_bestv2"
  const val ENDPOINT_IMG_533x300 = "$ENDPOINT_IMG/w533_and_h300_bestv2"
  const val ENDPOINT_IMG_276x350_FACE = "$ENDPOINT_IMG/w276_and_h350_face/"

  const val KEY_API_KEY = "api_key"

  const val KEY_TRENDING = "trending"
  const val KEY_MOVIE = "movie"
  const val KEY_TV = "tv"

  const val KEY_ID = "id"
  const val KEY_TYPE = "type"

  const val POSITION_DIRECTOR = "directing"
  const val POSITION_PRODUCER = "production"
  const val POSITION_WRITER = "writing"
  const val POSITION_ARTIST = "art"
}