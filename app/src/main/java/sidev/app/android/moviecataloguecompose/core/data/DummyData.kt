package sidev.app.android.moviecataloguecompose.core.data

import sidev.app.android.moviecataloguecompose.core.domain.model.*
import sidev.app.android.moviecataloguecompose.util.Const
import sidev.app.android.moviecataloguecompose.util.faker
import sidev.app.android.moviecataloguecompose.util.getLoop
import sidev.app.android.moviecataloguecompose.util.randomSubList

//private val faker = Faker()

val movieTypes = listOf(
  Const.KEY_TV,
  Const.KEY_MOVIE,
)

val dummyImgUrl = listOf(
  "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/1a052c60-b6a8-476f-87d9-04b7f5f0f903/d52ha02-7eebab18-a875-4185-94de-6fd44be0206a.jpg/v1/fill/w_900,h_675,q_75,strp/smiling_cat_by_omniamohamed_d52ha02-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9Njc1IiwicGF0aCI6IlwvZlwvMWEwNTJjNjAtYjZhOC00NzZmLTg3ZDktMDRiN2Y1ZjBmOTAzXC9kNTJoYTAyLTdlZWJhYjE4LWE4NzUtNDE4NS05NGRlLTZmZDQ0YmUwMjA2YS5qcGciLCJ3aWR0aCI6Ijw9OTAwIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.uEgwgBfq7faRJ9GPWtsJLmc8_kqG11HHAfdtNX5j56A",
  "https://i.kym-cdn.com/photos/images/newsfeed/001/394/314/c62.jpg",
  "https://64.media.tumblr.com/30243eb75aa86ee15c7f7f40923b148e/tumblr_pak3z2ET3g1r9qwkso1_500.jpg",
  "https://www.meme-arsenal.com/memes/2e9b785cdc7ce57813711033e06a5f53.jpg",
  "https://i.kym-cdn.com/photos/images/newsfeed/001/471/124/1fe.jpg",
)

val dummyCompanyLogoUrl = listOf(
  "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/2048px-Google_%22G%22_Logo.svg.png",
  "https://upload.wikimedia.org/wikipedia/commons/5/51/Facebook_f_logo_%282019%29.svg",
  "https://i.pinimg.com/originals/10/63/aa/1063aa965910d4093a4e98bde052885c.png",
  "https://thumb.viva.co.id/media/frontend/thumbs3/2017/03/16/58ca0396d0b9e-logo-amazon_665_374.jpg",
  "https://assets.stickpng.com/images/580b57fcd9996e24bc43c53e.png",
  "https://www.freepnglogos.com/uploads/logo-ig-png/logo-ig-instagram-windows-phone-all-you-need-know-9.png",
  "https://p.kindpng.com/picc/s/19-195256_whatsapp-icon-whatsapp-logo-jpg-download-hd-png.png",
  "https://cdn.icon-icons.com/icons2/2699/PNG/512/line_logo_icon_171239.png",
  "https://kopitekno.com/wp-content/uploads/2019/08/android_logo-696x464.jpg",
  "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/1724px-Apple_logo_black.svg.png",
  "https://logowik.com/content/uploads/images/flutter5786.jpg",
  "https://cdn.freelogovectors.net/wp-content/uploads/2018/12/react_logo.png",
)
val dummyCompanyLogo = dummyCompanyLogoUrl.map {
  RemoteImg(it)
}

val dummyProfileImgUrl = listOf(
  "https://randomuser.me/api/portraits/women/17.jpg",
  "https://randomuser.me/api/portraits/women/90.jpg",
  "https://randomuser.me/api/portraits/women/50.jpg",
  "https://randomuser.me/api/portraits/women/93.jpg",
  "https://randomuser.me/api/portraits/women/80.jpg",
  "https://randomuser.me/api/portraits/men/23.jpg",
  "https://randomuser.me/api/portraits/men/49.jpg",
  "https://randomuser.me/api/portraits/men/3.jpg",
  "https://randomuser.me/api/portraits/men/90.jpg",
  "https://randomuser.me/api/portraits/men/80.jpg",
  "https://randomuser.me/api/portraits/lego/8.jpg",
  "https://randomuser.me/api/portraits/lego/6.jpg",
  "https://randomuser.me/api/portraits/lego/3.jpg",
)
val dummyProfileImg = dummyProfileImgUrl.map {
  RemoteImg(it)
}

val dummyImg = dummyImgUrl.map {
  RemoteImg(it)
}

val dummyMovieList = List(20) {
  Movie(
    id = it,
    title = "Title $it",
    date = faker.date().birthday(),
    voteAverage = faker.random().nextDouble() * 100,
    voteCount = faker.random().nextInt(10_000),
    poster = dummyImg.getLoop(it),
    type = movieTypes.random(),
  )
}

val dummyCompanies = List(faker.random().nextInt(4, 14)) {
  Company(
    id = it,
    name = faker.company().name(),
    logo = dummyCompanyLogo.random(),
  )
}

val dummyCast = List(faker.random().nextInt(6, 20)) {
  Cast(
    id = it,
    name = faker.name().fullName(),
    character = faker.superhero().name(),
    profile = dummyProfileImg.random(),
  )
}

val dummyCrew = List(faker.random().nextInt(8, 23)) {
  Crew(
    id = it,
    name = faker.name().fullName(),
    profile = dummyProfileImg.random(),
    department = faker.job().position(),
  )
}

val dummyMovieDetailList = dummyMovieList.map {
  MovieDetail(
    movie = it,
    tagline = faker.lorem().words(faker.random().nextInt(10)).joinToString(separator = " "),
    overview = faker.lorem().paragraphs(faker.random().nextInt(1, 3)).joinToString(separator = "\n\n"),
    homepage = faker.internet().url(),
    duration = faker.random().nextDouble() * faker.random().nextInt(1, 1000),
    genres = faker.lorem().words(faker.random().nextInt(10)),
    productionCompanies = dummyCompanies.randomSubList(),
    casts = dummyCast.randomSubList(),
    crews = dummyCrew.randomSubList(),
    posters = dummyImg.randomSubList(1),
    backdrops = dummyImg.randomSubList(1),
    logos = dummyImg.randomSubList(1),
  )
}

fun getDummyMovieDetail(id: Int): MovieDetail = dummyMovieDetailList.find { it.movie.id == id }!!