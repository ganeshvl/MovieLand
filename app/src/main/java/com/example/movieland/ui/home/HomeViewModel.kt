package com.example.movieland.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.remote.models.requests.AddToWatchListRequest
import com.example.datasource.remote.models.responses.MovieListResponse
import com.example.datasource.remote.models.responses.MovieResult
import com.example.movieland.BaseViewModel
import com.example.movieland.data.repositories.MoviesRepo
import com.example.movieland.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepo: MoviesRepo
) : BaseViewModel(movieRepo) {

    private var _movieListNowPlaying: MutableLiveData<Resource<MovieListResponse>> =
        MutableLiveData()
    var movieListNowPlaying: LiveData<Resource<MovieListResponse>> = _movieListNowPlaying

    private var _movieListUpcoming: MutableLiveData<Resource<MovieListResponse>> = MutableLiveData()
    var movieListUpcoming: LiveData<Resource<MovieListResponse>> = _movieListUpcoming

    private var _movieListTopRated: MutableLiveData<Resource<MovieListResponse>> = MutableLiveData()
    var movieListTopRated: LiveData<Resource<MovieListResponse>> = _movieListTopRated

    private var _movieListPopular: MutableLiveData<Resource<MovieListResponse>> = MutableLiveData()
    var movieListPopular: LiveData<Resource<MovieListResponse>> = _movieListPopular

    private var _tvListPopular: MutableLiveData<Resource<MovieListResponse>> = MutableLiveData()
    var tvListPopular: LiveData<Resource<MovieListResponse>> = _tvListPopular

    private var _movieListTrending: MutableLiveData<Resource<MovieListResponse>> = MutableLiveData()
    var movieListTrending: LiveData<Resource<MovieListResponse>> = _movieListTrending

    private var _tvListTrending: MutableLiveData<Resource<MovieListResponse>> = MutableLiveData()
    var tvListTrending: LiveData<Resource<MovieListResponse>> = _tvListTrending


    fun getNowPlayingMovies() = viewModelScope.launch {
        _movieListNowPlaying.postValue(movieRepo.fetchNowPlayingMovies())
    }

    fun getTopRatedMovies() = viewModelScope.launch {
        _movieListTopRated.postValue(movieRepo.fetchTopRatedMovies())
    }

    suspend fun getAnimationMovies(genresIds: String): Resource<MovieListResponse> {
//        _movieListUpcoming.postValue(Resource.Loading())
//        _movieListUpcoming.postValue(movieRepo.fetchUpcomingMovies())
        return movieRepo.fetchMoviesByGenres(genresIds = genresIds)
    }



    suspend fun getAnime(genresIds: String): Resource<MovieListResponse> {
//        _movieListUpcoming.postValue(Resource.Loading())
//        _movieListUpcoming.postValue(movieRepo.fetchUpcomingMovies())
        return movieRepo.fetchTvShowsByGenres(genresIds = genresIds)
    }

    fun getPopularMovies() = viewModelScope.launch {
        _movieListPopular.postValue(movieRepo.fetchPopularMovies())
    }

    fun getPopularTvShows() = viewModelScope.launch {
        _tvListPopular.postValue(movieRepo.fetchPopularTvShows())
    }

    fun getTrendingMovies() = viewModelScope.launch {
        _movieListTrending.postValue(Resource.Loading())
        _movieListTrending.postValue(movieRepo.fetchTrendingMovies())
    }

    fun getTrendingTvShows() = viewModelScope.launch {
        _tvListTrending.postValue(Resource.Loading())
        _tvListTrending.postValue(movieRepo.fetchTrendingTvShows())
    }

    suspend fun addToWatchList(
        accountId: Int,
        sessionId: String,
        addToWatchListRequest: AddToWatchListRequest
    ): Resource<ResponseBody> {
        return movieRepo.addToWatchList(accountId, sessionId, addToWatchListRequest)
    }

}

//
//
//suspend fun getNowPlayingMovies(): Resource<MovieListResponse> {
//    return movieRepo.fetchNowPlayingMovies()
//}
//
//suspend fun getTopRatedMovies(): Resource<MovieListResponse> {
//    return movieRepo.fetchTopRatedMovies()
//}
//
//suspend fun getUpcomingMovies(): Resource<MovieListResponse> {
//    return movieRepo.fetchUpcomingMovies()
//}
//
//suspend fun getPopularMovies(): Resource<MovieListResponse> {
//    return movieRepo.fetchPopularMovies()
//}
//
//suspend fun getPopularTvShows(): Resource<MovieListResponse> {
//    return movieRepo.fetchPopularTvShows()
//}
//
//suspend fun getTrendingMovies(): Resource<MovieListResponse> {
//    return movieRepo.fetchTrendingMovies()
//}
//
//suspend fun getTrendingTvShows(): Resource<MovieListResponse> {
//    return movieRepo.fetchTrendingTvShows()
//}