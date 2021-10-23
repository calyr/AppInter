package app.bo.com.ucb.appinter

class ConstantsRestApi {
    companion object {
        const val URL_BASE = "https://jsonplaceholder.typicode.com"
        const val POSTS ="/posts"
        const val USERS = "/users"
    }
}


data class Post(val userId: Int, val id: Int, val title: String, val body: String)