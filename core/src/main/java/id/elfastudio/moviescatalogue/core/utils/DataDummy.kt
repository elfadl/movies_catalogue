package id.elfastudio.moviescatalogue.core.utils

import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieEntity
import id.elfastudio.moviescatalogue.core.data.source.remote.response.*
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import id.elfastudio.moviescatalogue.core.domain.model.TvShow

object DataDummy {

    fun generateMovies(): List<Movie> = listOf(
        Movie(
            1,
            "A Star Is Born",
            listOf(
                "Drama",
                "Romance"
            ),
            "05 October 2018",
            "2h 16m",
            75,
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            123.4,
            false
        ),
        Movie(
            2,
            "Alita: Battle Angel",
            listOf(
                "Action",
                "Science Fiction",
                "Adventure"
            ),
            "2019-02-14 (US)",
            "2h 2m",
            72,
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        ),
        Movie(
            3,
            "Aquaman",
            listOf(
                "Action",
                "Adventure",
                "Fantasy"
            ),
            "2018-12-21 (US)",
            "2h 23m",
            69,
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        ),
        Movie(
            4,
            "Bohemian Rhapsody",
            listOf(
                "Music",
                "Drama",
                "History"
            ),
            "2018-10-30 (ID)",
            "2h 15m",
            80,
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        ),
        Movie(
            5,
            "Cold Pursuit",
            listOf(
                "Action",
                "Crime",
                "Thriller"
            ),
            "2019-02-08 (US)",
            "1h 59m",
            57,
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        ),
        Movie(
            6,
            "Creed II",
            listOf(
                "Drama"
            ),
            "2018-11-21 (US)",
            "2h 10m",
            69,
            "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        ),
        Movie(
            7,
            "Glass",
            listOf(
                "Thriller",
                "Drama",
                "Science Fiction"
            ),
            "2019-01-18 (US)",
            "2h 9m",
            67,
            "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        ),
        Movie(
            8,
            "How to Train Your Dragon: The Hidden World",
            listOf(
                "Animation",
                "Family",
                "Adventure"
            ),
            "2019-01-09 (ID)",
            "1h 44m",
            78,
            "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        ),
        Movie(
            9,
            "Avengers: Infinity War",
            listOf(
                "Adventure",
                "Action",
                "Science Fiction"
            ),
            "2018-04-27 (US)",
            "2h 29m",
            83,
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        ),
        Movie(
            10,
            "Spider-Man: Into the Spider-Verse",
            listOf(
                "Action",
                "Adventure",
                "Animation",
                "Science Fiction",
                "Comedy"
            ),
            "2018-12-14 (US)",
            "1h 57m",
            84,
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0,
            false
        )
    )

    fun generateMovieEntities(): List<MovieEntity> = listOf(
        MovieEntity(
            1,
            "A Star Is Born",
            listOf(
                "Drama",
                "Romance"
            ),
            "05 October 2018",
            "2h 16m",
            75,
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            123.4
        ),
        MovieEntity(
            2,
            "Alita: Battle Angel",
            listOf(
                "Action",
                "Science Fiction",
                "Adventure"
            ),
            "2019-02-14 (US)",
            "2h 2m",
            72,
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        ),
        MovieEntity(
            3,
            "Aquaman",
            listOf(
                "Action",
                "Adventure",
                "Fantasy"
            ),
            "2018-12-21 (US)",
            "2h 23m",
            69,
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        ),
        MovieEntity(
            4,
            "Bohemian Rhapsody",
            listOf(
                "Music",
                "Drama",
                "History"
            ),
            "2018-10-30 (ID)",
            "2h 15m",
            80,
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        ),
        MovieEntity(
            5,
            "Cold Pursuit",
            listOf(
                "Action",
                "Crime",
                "Thriller"
            ),
            "2019-02-08 (US)",
            "1h 59m",
            57,
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        ),
        MovieEntity(
            6,
            "Creed II",
            listOf(
                "Drama"
            ),
            "2018-11-21 (US)",
            "2h 10m",
            69,
            "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        ),
        MovieEntity(
            7,
            "Glass",
            listOf(
                "Thriller",
                "Drama",
                "Science Fiction"
            ),
            "2019-01-18 (US)",
            "2h 9m",
            67,
            "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        ),
        MovieEntity(
            8,
            "How to Train Your Dragon: The Hidden World",
            listOf(
                "Animation",
                "Family",
                "Adventure"
            ),
            "2019-01-09 (ID)",
            "1h 44m",
            78,
            "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        ),
        MovieEntity(
            9,
            "Avengers: Infinity War",
            listOf(
                "Adventure",
                "Action",
                "Science Fiction"
            ),
            "2018-04-27 (US)",
            "2h 29m",
            83,
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        ),
        MovieEntity(
            10,
            "Spider-Man: Into the Spider-Verse",
            listOf(
                "Action",
                "Adventure",
                "Animation",
                "Science Fiction",
                "Comedy"
            ),
            "2018-12-14 (US)",
            "1h 57m",
            84,
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
            1.0
        )
    )

    fun generateTvShow(): List<TvShow> = listOf(
        TvShow(
            101,
            "Arrow",
            listOf(
                "Crime",
                "Drama",
                "Mistery",
                "Action & Adventure"
            ),
            "03 January 2021",
            "42m",
            67,
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "/slkghjojhgagha.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            102,
            "Doom Patrol",
            listOf(
                "Sci-Fi & Fantasy",
                "Drama"
            ),
            "6 Februari 2021",
            "49m",
            77,
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            "/ajbjdgajoetkojgsv.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            103,
            "Dragon Ball",
            listOf(
                "Animation",
                "Action & Adventure",
                "Sci-Fi & Fantasy"
            ),
            "7 April 1990",
            "25m",
            82,
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
            "/lijgwegaojkwgw.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            104,
            "Fairy Tail",
            listOf(
                "Action & Adventure",
                "Animation",
                "Comedy",
                "Sci-Fi & Fantasy",
                "Mystery"
            ),
            "23 Agustus 2002",
            "25m",
            78,
            "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
            "/asihfoihaohesf.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            105,
            "Family Guy",
            listOf(
                "Animation",
                "Comedy"
            ),
            "17 Desember 2010",
            "22m",
            71,
            "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            "/kjahdlighfoawfw.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            106,
            "The Flash",
            listOf(
                "Drama",
                "Sci-Fi & Fantasy"
            ),
            "28 Januari 2002",
            "44m",
            78,
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "/sqoerprmmcisjarf.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            107,
            "Gotham",
            listOf(
                "Drama",
                "Crime",
                "Sci-Fi & Fantasy"
            ),
            "8 Mei 2016",
            "43m",
            76,
            "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            "/sajdowjotwjog.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            108,
            "Grey's Anatomy",
            listOf(
                "Drama"
            ),
            "19 Maret 2003",
            "43m",
            82,
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "/ksjfpopwktwg.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            109,
            "Hanna",
            listOf(
                "Action & Adventure",
                "Drama"
            ),
            "14 September 2021",
            "50m",
            76,
            "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
            "/mnklbnlsjovsjof.jpg",
            123.4,
            arrayListOf(),
            false
        ),
        TvShow(
            110,
            "Naruto Shippūden",
            listOf(
                "Animation",
                "Action & Adventure",
                "Sci-Fi & Fantasy"
            ),
            "14 April 2002",
            "25m",
            null,
            "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
            "/nvjosjfosjfijsijijgwr.jpg",
            123.4,
            arrayListOf(),
            false
        )
    )

    fun generateTvShowResponses(): TvShowResponse = TvShowResponse(
        listOf(
            ResultsTvShow(
                "2018-10-05 (US)",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "en",
                "A Star Is Born",
                123.4,
                123.4,
                "A Star Is Born",
                1,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2019-02-14 (US)",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "en",
                "Alita: Battle Angel",
                123.4,
                123.4,
                "Alita: Battle Angel",
                2,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2018-12-21 (US)",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                "Aquaman",
                123.4,
                123.4,
                "Aquaman",
                3,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2018-10-30 (ID)",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "en",
                "Bohemian Rhapsody",
                123.4,
                123.4,
                "Bohemian Rhapsody",
                4,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2019-02-08 (US)",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "en",
                "Cold Pursuit",
                123.4,
                123.4,
                "Cold Pursuit",
                5,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2018-11-21 (US)",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "en",
                "Creed II",
                123.4,
                123.4,
                "Creed II",
                6,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2019-01-18 (US)",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "en",
                "Glass",
                123.4,
                123.4,
                "Glass",
                7,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2019-01-09 (ID)",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "en",
                "How to Train Your Dragon: The Hidden World",
                123.4,
                123.4,
                "How to Train Your Dragon: The Hidden World",
                8,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2018-04-27 (US)",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "en",
                "Avengers: Infinity War",
                123.4,
                123.4,
                "Avengers: Infinity War",
                9,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            ),
            ResultsTvShow(
                "2018-12-14 (US)",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "en",
                "Spider-Man: Into the Spider-Verse",
                123.4,
                123.4,
                "Spider-Man: Into the Spider-Verse",
                10,
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
                listOf("en")
            )
        )
    )

    fun generateDetailMovieResponse() = DetailMovieResponse(
        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
        "en",
        "A Star Is Born",
        136,
        false,
        "A Star Is Born",
        "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg",
        "2018-10-05 (US)",
        listOf(
            GenresItem("Drama", 1),
            GenresItem("Romance", 1)
        ),
        123.4,
        7.5,
        "",
        1,
        123,
        "Released"
    )

    fun generateMovieResponses(): MovieResponse = MovieResponse(
        listOf(
            ResultsMovie(
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "en",
                "A Star Is Born",
                "2018-10-05 (US)",
                123.4,
                123.4,
                1,
                false,
                "A Star Is Born",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "en",
                "Alita: Battle Angel",
                "2019-02-14 (US)",
                123.4,
                123.4,
                2,
                false,
                "Alita: Battle Angel",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                "Aquaman",
                "2018-12-21 (US)",
                123.4,
                123.4,
                3,
                false,
                "Aquaman",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "en",
                "Bohemian Rhapsody",
                "2018-10-30 (ID)",
                123.4,
                123.4,
                4,
                false,
                "Bohemian Rhapsody",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "en",
                "Cold Pursuit",
                "2019-02-08 (US)",
                123.4,
                123.4,
                5,
                false,
                "Cold Pursuit",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "en",
                "Creed II",
                "2018-11-21 (US)",
                123.4,
                123.4,
                6,
                false,
                "Creed II",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "en",
                "Glass",
                "2019-01-18 (US)",
                123.4,
                123.4,
                7,
                false,
                "Glass",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "en",
                "How to Train Your Dragon: The Hidden World",
                "2019-01-09 (ID)",
                123.4,
                123.4,
                8,
                false,
                "How to Train Your Dragon: The Hidden World",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "en",
                "Avengers: Infinity War",
                "2018-04-27 (US)",
                123.4,
                123.4,
                9,
                false,
                "Avengers: Infinity War",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            ),
            ResultsMovie(
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "en",
                "Spider-Man: Into the Spider-Verse",
                "2018-12-14 (US)",
                123.4,
                123.4,
                10,
                false,
                "Spider-Man: Into the Spider-Verse",
                listOf(1, 2),
                123,
                "/dhRcIc6acdXG83CJ2z9Hns6Fkg2.jpg"
            )
        )
    )

    fun generateDetailTvSHowResponse() = DetailTvShowResponse(
        listOf(42),
        "2021-01-03 (US)",
        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
        "en",
        listOf(
            SeasonsItem(
                "2012-01-03",
                "",
                23,
                "",
                1,
                1,
                ""
            ),
            SeasonsItem(
                "2013-01-03",
                "",
                23,
                "",
                2,
                2,
                ""
            ),
            SeasonsItem(
                "2014-01-03",
                "",
                23,
                "",
                3,
                3,
                ""
            ),
            SeasonsItem(
                "2015-01-03",
                "",
                23,
                "",
                4,
                4,
                ""
            ),
            SeasonsItem(
                "2016-01-03",
                "",
                23,
                "",
                5,
                5,
                ""
            ),
            SeasonsItem(
                "2017-01-03",
                "",
                23,
                "",
                6,
                6,
                ""
            ),
            SeasonsItem(
                "2018-01-03",
                "",
                22,
                "",
                7,
                7,
                ""
            ),
            SeasonsItem(
                "2019-01-03",
                "",
                10,
                "",
                8,
                8,
                ""
            )
        ),
        123,
        listOf("en"),
        "Released",
        "/slkghjojhgagha.jpg",
        listOf("en"),
        listOf(
            GenresItem("Crime", 1),
            GenresItem("Drama", 2),
            GenresItem("Mistery", 3),
            GenresItem("Action & Adventure", 4)
        ),
        "Arrow",
        123.4,
        6.7,
        "Arrow",
        "tagline",
        101,
        2,
        false,
        123,
        ""
    )

}