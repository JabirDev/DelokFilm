package com.jabirdev.delokfilm.data

import com.google.gson.annotations.SerializedName
import com.jabirdev.delokfilm.models.*

data class DetailMovieEntity(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: DetailMovieModel.BelongsToCollection,

    @SerializedName("budget")
    val budget: Int,

    @SerializedName("genres")
    val genres: List<GenresModel>,

    @SerializedName("homepage")
    val homepage: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("imdb_id")
    val imdbId: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

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

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("revenue")
    val revenue: Int,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageModel>,

    @SerializedName("status")
    val status: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("voteCount")
    val voteCount: Int
)
