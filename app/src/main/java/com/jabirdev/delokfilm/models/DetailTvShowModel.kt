package com.jabirdev.delokfilm.models

import com.google.gson.annotations.SerializedName

data class DetailTvShowModel(
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
    val lastEpisodeToAir: LastEpisodeToAir,

    @SerializedName("name")
    val name: String,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Int?,

    @SerializedName("networks")
    val networks: List<TvNetworks>,

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
    val seasons: List<TvSeasons>,

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

) {

    data class LastEpisodeToAir(
        @SerializedName("air_date")
        val airDate: String,

        @SerializedName("episode_number")
        val episodeNumber: Int,

        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("production_code")
        val productionCode: String,

        @SerializedName("season_number")
        val seasonNumber: Int,

        @SerializedName("still_path")
        val stillPath: String,

        @SerializedName("vote_average")
        val voteAverage: Float,

        @SerializedName("vote_count")
        val voteCount: Int
    )

    data class TvNetworks(
        @SerializedName("name")
        val name: String,

        @SerializedName("id")
        val id: Int,

        @SerializedName("logo_path")
        val logoPath: String,

        @SerializedName("origin_country")
        val originCountry: String
    )

    data class TvSeasons(
        @SerializedName("air_date")
        val airDate: String,

        @SerializedName("episode_count")
        val episodeCount: Int,

        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("poster_path")
        val posterPath: String,

        @SerializedName("season_number")
        val seasonNumber: Int

    )

}
