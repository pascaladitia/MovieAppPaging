package com.pascal.moviepagging.network

import com.pascal.moviepagging.model.ResponseGetData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    //kendalanya apa mas ?
    //data tidak tampil mas, error recyclerview no skipping layout gtu

    @GET("3/movie/5/lists")
    fun getMovie(@Query("language") language : String,
                 @Query("api_key") api : String,
                @Query("page") page : Long,
                @Query("total_result") totalResult : Int) : Flowable<ResponseGetData>
}
//coba running ya mas ?
//maaf kalo lemot hhe speknya rendah
//biasnya pake device ya ? running ya ? iya mas device
//yaudah pake device aja mas
//hehe
//udah connect mas
//tampilannya blank mas