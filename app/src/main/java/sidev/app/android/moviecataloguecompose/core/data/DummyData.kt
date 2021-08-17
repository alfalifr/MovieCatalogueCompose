package sidev.app.android.moviecataloguecompose.core.data

import com.github.javafaker.Faker
import sidev.app.android.moviecataloguecompose.core.domain.model.Movie
import sidev.app.android.moviecataloguecompose.core.domain.model.MovieDetail

private val faker = Faker()

val dummyImgUrl = listOf(
  "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/1a052c60-b6a8-476f-87d9-04b7f5f0f903/d52ha02-7eebab18-a875-4185-94de-6fd44be0206a.jpg/v1/fill/w_900,h_675,q_75,strp/smiling_cat_by_omniamohamed_d52ha02-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9Njc1IiwicGF0aCI6IlwvZlwvMWEwNTJjNjAtYjZhOC00NzZmLTg3ZDktMDRiN2Y1ZjBmOTAzXC9kNTJoYTAyLTdlZWJhYjE4LWE4NzUtNDE4NS05NGRlLTZmZDQ0YmUwMjA2YS5qcGciLCJ3aWR0aCI6Ijw9OTAwIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.uEgwgBfq7faRJ9GPWtsJLmc8_kqG11HHAfdtNX5j56A",
  "https://i.kym-cdn.com/photos/images/newsfeed/001/394/314/c62.jpg",
  "https://64.media.tumblr.com/30243eb75aa86ee15c7f7f40923b148e/tumblr_pak3z2ET3g1r9qwkso1_500.jpg",
  "https://www.meme-arsenal.com/memes/2e9b785cdc7ce57813711033e06a5f53.jpg",
  "https://i.kym-cdn.com/photos/images/newsfeed/001/471/124/1fe.jpg",
)
fun getDummyImgUrl(i: Int): String = dummyImgUrl[i % dummyImgUrl.size]

val dummyMovieList = List(20) {
  Movie(
    it,
    "Title $it",
    faker.date().birthday(),
    getDummyImgUrl(it),
  )
}

val dummyMovieDetailList = dummyMovieList.map {
  MovieDetail(
    it,
    faker.lorem().paragraph(5),
    faker.lorem().words(
      faker.random().nextInt(3, 10)
    )
  )
}

fun getDummyMovieDetail(id: Int): MovieDetail = dummyMovieDetailList.find { it.movie.id == id }!!