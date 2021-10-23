package app.bo.com.ucb.appinter

import retrofit2.Call
import retrofit2.http.GET


interface EndPointApi {
    @GET(ConstantsRestApi.POSTS)
    fun getAllPost(): Call<List<Post>>

    @GET(ConstantsRestApi.USERS)
    fun getUsers(): Call<List<User>>
}
