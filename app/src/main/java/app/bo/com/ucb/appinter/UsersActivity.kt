package app.bo.com.ucb.appinter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.bo.com.ucb.appinter.modeldb.Book
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersActivity : AppCompatActivity() {

    lateinit var recyclerViewUser: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        //
        val bookDao = AppRoomDatabase.getDatabase(applicationContext)
            .bookDao()
        val repository = BookRepository(bookDao)

        GlobalScope.launch {
            repository.insert(Book("Android I"))
            repository.insert(Book("Android II"))
            repository.insert(Book("Android III"))

            val listDb = repository.getListBook()

            listDb.forEach {
                Log.d("DB", it.title)
            }

        }




        //


        val list = arrayListOf<User>() //recyclerView 1

        val restApiAdapter = RestApiAdapter()
        val endPointApi = restApiAdapter.connectionApi()
        val bookResponse = endPointApi.getAllPost()

        bookResponse.enqueue( object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts = response.body()
                posts?.forEach {
                    //Log.d("Resp", Gson().toJson(it) )
                    Log.d("RESP ID", it.id.toString())
                    val resp = Gson().toJson(it.id)
                    //list.add(User(it.title, it.body))                                  ////recyclerView 2
                }
                //recyclerViewUser.adapter = UserListAdapter(list, applicationContext) //recyclerView 3
            }
        })

Toast.makeText(applicationContext, "TEST", Toast.LENGTH_LONG).show()
        val userResponse = endPointApi.getUsers()
        userResponse.enqueue( object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val userList = response.body()

                userList?.forEach {
                    Log.d("USER", it.email)
                    list.add(it)
                }
                recyclerViewUser.adapter = UserListAdapter(list, applicationContext)

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t?.printStackTrace()
            }

        })





        val linearLayoutManager = LinearLayoutManager(this)
//        val gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewUser = findViewById(R.id.users_rv)
//        recyclerViewUser.layoutManager = gridLayoutManager
        recyclerViewUser.layoutManager = linearLayoutManager

        //recyclerViewUser.adapter = UserListAdapter(list, this)


        val sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val CHANNEL_ID = "com.calyr.appnotif"
        if ( Build.VERSION.SDK_INT >= 26) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_bg)
                .setContentTitle("ucb")
                .setContentText("Notificaciones Internas")
                .setSound(sonido)
                .setAutoCancel(true)
        notificationManager.notify(0, notification.build())


    }
}