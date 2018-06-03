package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.Movie
import studio.forface.rxtmdbapi.models.ResultPage
import studio.forface.rxtmdbapi.models.TvShow
import studio.forface.rxtmdbapi.utils.Sorting

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 *
 * NOTE: IF YOU ARE USING KOTLIN, FOR "COMMA SEPARATE VALUE"'S YOU CAN PASS A LIST, WHICH
 * WILL AUTOMATICALLY CONVERTED TO THE REQUIRED STRING.
 *
 * "GTE" = Greater Than or Equal.
 * "LTE" = Less Than or Equal.
 */

private const val BASE_PATH = "discover"
interface TmdbDiscover {

    /**
     * Discover movies by different types of data like average rating, number of votes, genres and
     * certifications. You can get a valid list of certifications from here:
     * https://developers.themoviedb.org/3/certifications/get-movie-certifications .
     *
     * Discover also supports a nice list of sort options.
     * @see Sorting.MovieSorting for all of the available options.
     *
     * Please note, when using certification \ certification.lte you must also specify
     * certification_country. These two parameters work together in order to filter the results.
     * You can only filter results with the countries we have added to certifications list here:
     * https://developers.themoviedb.org/3/certifications/get-movie-certifications .
     *
     * If you specify the region parameter, the regional release date will be used instead of the
     * primary release date. The date returned will be the first date based on your query (ie. if
     * a with_release_type is specified). It's important to note the order of the release types
     * that are used. Specifying "2|3" would return the limited theatrical release date as opposed
     * to "3|2" which would return the theatrical date.
     *
     * Also note that a number of filters support being comma (,) or pipe (|) separated. Comma's
     * are treated like an AND and query while pipe's are an OR.
     *
     * Some examples of what can be done with discover can be found here:
     * https://www.themoviedb.org/documentation/api/discover .
     *
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy Choose from one of the many available sort options from [Sorting.MovieSorting].
     * @param includeAdults choose whether to include adult (pornography) content in the results.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param region an ISO 3166-1 code to filter release dates. Must be uppercase.
     * @param year A filter to limit the results to a specific year (looking at all release dates).
     * @param withGenres Comma separated value of genre ids that you want to include in the results.
     * @param withoutGenres Comma separated value of genre ids that you want to exclude from the
     * results.
     * @param withKeywords A comma separated list of keyword ID's. Only include movies that have
     * one of the ID's added as a keyword.
     * @param withoutKeywords Exclude items with certain keywords. You can comma and pipe separate
     * these values to create an 'AND' or 'OR' logic.
     * @param withPeople A comma separated list of person ID's. Only include movies that have one
     * of the ID's added as a either a actor or a crew member.
     * @param withCast A comma separated list of person ID's. Only include movies that have one of
     * the ID's added as an actor.
     * @param withCrew A comma separated list of person ID's. Only include movies that have one of
     * the ID's added as a crew member.
     * @param withCompanies A comma separated list of production company ID's. Only include movies
     * that have one of the ID's added as a production company.
     * @param voteAverageGte Filter and only include movies that have a rating that is greater or
     * equal to the specified value.
     * @param voteAverageLte Filter and only include movies that have a rating that is less than or
     * equal to the specified value.
     * @param voteCountGte Filter and only include movies that have a vote count that is greater or
     * equal to the specified value.
     * @param voteCountLte Filter and only include movies that have a vote count that is less than
     * or equal to the specified value.
     * @param releaseDateGte Filter and only include movies that have a release date (looking at
     * all release dates) that is greater or equal to the specified value.
     * @param releaseDateLte Filter and only include movies that have a release date (looking at
     * all release dates) that is less than or equal to the specified value.
     * @param primaryReleaseYear A filter to limit the results to a specific primary release year.
     * @param primaryReleaseDateGte Filter and only include movies that have a primary release date
     * that is greater or equal to the specified value.
     * @param primaryReleaseDateLte Filter and only include movies that have a primary release date
     * that is less than or equal to the specified value.
     * @param releaseType Specify a comma (AND) or pipe (OR) separated value to filter release
     * types by. These release types map to the same values found on the movie release date method.
     * @param originalLanguage Specify an ISO 639-1 string to filter results by their original
     * language value.
     * @param runtimeGte Filter and only include movies that have a runtime that is greater or
     * equal to a value.
     * @param runtimeLte Filter and only include movies that have a runtime that is less than or
     * equal to a value.
     * @param includeVideo A filter to include or exclude videos.
     * @param certification Filter results with a valid certification from the
     * 'certification_country' field.
     * @param certificationLte Filter and only include movies that have a certification that is
     * less than or equal to the specified value.
     * @param certificationCountry Used in conjunction with the certification filter, use this to
     * specify a country with a valid certification.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/movie")
    fun movieDiscover(
            @Query("page")          page: Int? = 1,
            @Query("sort_by")       sortBy: Sorting.MovieSorting = Sorting.Popularity.DESCENDING,
            @Query("include_adult") includeAdults: Boolean? = TmdbApiConfig.includeAdults,
            @Query("language")                  language: String? = TmdbApiConfig.language,
            @Query("region")                    region: String? = null,
            @Query("year")                      year: Int? = null,
            @Query("with_genres")               withGenres: List<Int>? = null,
            @Query("without_genres")            withoutGenres: List<Int>? = null,
            @Query("with_keywords")             withKeywords: List<String>? = null,
            @Query("without_keywords")          withoutKeywords: List<String>? = null,
            @Query("with_people")               withPeople: List<String>? = null,
            @Query("with_cast")                 withCast: List<String>? = null,
            @Query("with_crew")                 withCrew: List<String>? = null,
            @Query("with_companies")            withCompanies: List<String>? = null,
            @Query("vote_average_gte")          voteAverageGte: Number? = null,
            @Query("vote_average_lte")          voteAverageLte: Number? = null,
            @Query("vote_count_gte")            voteCountGte: Int? = null,
            @Query("vote_count_lte")            voteCountLte: Int? = null,
            @Query("release_date_gte")          releaseDateGte: String? = null,
            @Query("release_date_lte")          releaseDateLte: String? = null,
            @Query("primary_release_year")      primaryReleaseYear: Int? = null,
            @Query("primary_release_date_gte")  primaryReleaseDateGte: String? = null,
            @Query("primary_release_date_lte")  primaryReleaseDateLte: String? = null,
            @Query("with_release_type")         releaseType: Int? = null,
            @Query("with_original_language")    originalLanguage: String? = null,
            @Query("with_runtime_gte")          runtimeGte: Int? = null,
            @Query("with_runtime_lte")          runtimeLte: Int? = null,
            @Query("include_video")             includeVideo: Boolean? = null,
            @Query("certification")             certification: String? = null,
            @Query("certification_lte")         certificationLte: String? = null,
            @Query("certification_country")     certificationCountry: String? = null
    ): Single<ResultPage<Movie>>

    /**
     * Discover movies by different types of data like average rating, number of votes, genres and
     * certifications. You can get a valid list of certifications from here:
     * https://developers.themoviedb.org/3/certifications/get-movie-certifications .
     *
     * Discover also supports a nice list of sort options.
     * @see Sorting.MovieSorting for all of the available options.
     *
     * Please note, when using certification \ certification.lte you must also specify
     * certification_country. These two parameters work together in order to filter the results.
     * You can only filter results with the countries we have added to certifications list here:
     * https://developers.themoviedb.org/3/certifications/get-movie-certifications .
     *
     * If you specify the region parameter, the regional release date will be used instead of the
     * primary release date. The date returned will be the first date based on your query (ie. if
     * a with_release_type is specified). It's important to note the order of the release types
     * that are used. Specifying "2|3" would return the limited theatrical release date as opposed
     * to "3|2" which would return the theatrical date.
     *
     * Also note that a number of filters support being comma (,) or pipe (|) separated. Comma's
     * are treated like an AND and query while pipe's are an OR.
     *
     * Some examples of what can be done with discover can be found here:
     * https://www.themoviedb.org/documentation/api/discover .
     *
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy Choose from one of the many available sort options from [Sorting.MovieSorting].
     * @param includeAdults choose whether to include adult (pornography) content in the results.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param screenedTheatrically Filter results to include items that have been screened
     * theatrically.
     * @param firstAirDateYear Filter and only include TV shows that have a original air date year
     * that equal to the specified value. Can be used in conjunction with the
     * "include_null_first_air_dates" filter if you want to include items with no air date.
     * @param withGenres Comma separated value of genre ids that you want to include in the results.
     * @param withoutGenres Comma separated value of genre ids that you want to exclude from the
     * results.
     * @param withKeywords A comma separated list of keyword ID's. Only include movies that have
     * one of the ID's added as a keyword.
     * @param withoutKeywords Exclude items with certain keywords. You can comma and pipe separate
     * these values to create an 'AND' or 'OR' logic.
     * @param withCompanies A comma separated list of production company ID's. Only include movies
     * that have one of the ID's added as a production company.
     * @param withNetworks Comma separated value of network ids that you want to include in the
     * results.
     * @param voteAverageGte Filter and only include movies that have a rating that is greater or
     * equal to the specified value.
     * @param voteAverageLte Filter and only include movies that have a rating that is less than or
     * equal to the specified value.
     * @param voteCountGte Filter and only include movies that have a vote count that is greater or
     * equal to the specified value.
     * @param voteCountLte Filter and only include movies that have a vote count that is less than
     * or equal to the specified value.
     * @param airDateGte Filter and only include TV shows that have a air date (by looking at all
     * episodes) that is greater or equal to the specified value.
     * @param airDateLte Filter and only include TV shows that have a air date (by looking at all
     * episodes) that is less that or equal to the specified value.
     * @param firstAirDateGte Filter and only include TV shows that have a original air date that
     * is greater or equal to the specified value. Can be used in conjunction with the
     * "include_null_first_air_dates" filter if you want to include items with no air date.
     * @param firstAirDateLte Filter and only include TV shows that have a original air date that
     * is less than or equal to the specified value. Can be used in conjunction with the
     * "include_null_first_air_dates" filter if you want to include items with no air date.
     * @param includeNullFirstAirDates Use this filter to include TV shows that don't have an air
     * date while using any of the "first_air_date" filters.
     * @param timezone Comma separated value of network ids that you want to include in the results.
     * @param originalLanguage Specify an ISO 639-1 string to filter results by their original
     * language value.
     * @param runtimeGte Filter and only include movies that have a runtime that is greater or
     * equal to a value.
     * @param runtimeLte Filter and only include movies that have a runtime that is less than or
     * equal to a value.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/tv")
    fun tvShowDiscover(
            @Query("page")          page: Int? = 1,
            @Query("sort_by")       sortBy: Sorting.TvShowSorting = Sorting.Popularity.DESCENDING,
            @Query("include_adult") includeAdults: Boolean? = TmdbApiConfig.includeAdults,
            @Query("language")                      language: String? = TmdbApiConfig.language,
            @Query("screened_theatrically")         screenedTheatrically: Boolean? = null,
            @Query("first_air_date_year")           firstAirDateYear: Int? = null,
            @Query("with_genres")                   withGenres: List<Int>? = null,
            @Query("without_genres")                withoutGenres: List<Int>? = null,
            @Query("with_keywords")                 withKeywords: List<String>? = null,
            @Query("without_keywords")              withoutKeywords: List<String>? = null,
            @Query("with_companies")                withCompanies: List<String>? = null,
            @Query("with_networks")                 withNetworks: List<String>? = null,
            @Query("vote_average_gte")              voteAverageGte: Number? = null,
            @Query("vote_average_lte")              voteAverageLte: Number? = null,
            @Query("vote_count_gte")                voteCountGte: Int? = null,
            @Query("vote_count_lte")                voteCountLte: Int? = null,
            @Query("air_date_gte")                  airDateGte: String? = null,
            @Query("air_date_lte")                  airDateLte: String? = null,
            @Query("first_air_date_gte")            firstAirDateGte: String? = null,
            @Query("first_air_date_lte")            firstAirDateLte: String? = null,
            @Query("include_null_first_air_dates")  includeNullFirstAirDates: Boolean? = null,
            @Query("timezone")                      timezone: String? = null,
            @Query("with_original_language")        originalLanguage: String? = null,
            @Query("with_runtime_gte")              runtimeGte: Int? = null,
            @Query("with_runtime_lte")              runtimeLte: Int? = null
    ): Single<ResultPage<TvShow>>

}