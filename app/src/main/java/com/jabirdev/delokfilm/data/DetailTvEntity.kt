package com.jabirdev.delokfilm.data

import com.google.gson.annotations.SerializedName
import com.jabirdev.delokfilm.models.*

data class DetailTvEntity(
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("episode_run_time")
    val episodeRuntime: List<Int>,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("genres")
    val genres: List<GenresModel>,

    @SerializedName("homepage")
    val homepage: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("in_production")
    val inProduction: Boolean,

    @SerializedName("languages")
    val languages: List<String>,

    @SerializedName("last_air_date")
    val lastAirDate: String,

    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: DetailTvShowModel.LastEpisodeToAir,

    @SerializedName("name")
    val name: String,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Int?,

    @SerializedName("networks")
    val networks: List<DetailTvShowModel.TvNetworks>,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,

    @SerializedName("origin_country")
    val originCountry: List<String>,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Float,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesModel>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesModel>,

    @SerializedName("seasons")
    val seasons: List<DetailTvShowModel.TvSeasons>,

    @SerializedName("spoken_languages")
    val spokenLanguage: List<SpokenLanguageModel>,

    @SerializedName("status")
    val status: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int
)
