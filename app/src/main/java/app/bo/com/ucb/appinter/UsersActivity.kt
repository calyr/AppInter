package app.bo.com.ucb.appinter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UsersActivity : AppCompatActivity() {

    lateinit var recyclerViewUser: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val list = arrayListOf<User>(
            User("name1", "email1"),
            User("name2", "email2"),
            User("name3", "email3"),
            User("name4", "email4"),
            User("name5", "email5"),
            User("name6", "email6"),
            User("name7", "email7"),
            User("name8", "email8"),
            User("name1", "email1"),
            User("name2", "email2"),
            User("name3", "email3"),
            User("name4", "email4"),
            User("name5", "email5"),
            User("name6", "email6"),
            User("name7", "email7"),
            User("name8", "email8"),
            User("name1", "email1"),
            User("name2", "email2"),
            User("name3", "email3"),
            User("name4", "email4"),
            User("name5", "email5"),
            User("name6", "email6"),
            User("name7", "email7"),
            User("name8", "email8"),
            User("name1", "email1"),
            User("name2", "email2"),
            User("name3", "email3"),
            User("name4", "email4"),
            User("name5", "email5"),
            User("name6", "email6"),
            User("name7", "email7"),
            User("name8", "email8"),
            User("name1", "email1"),
            User("name2", "email2"),
            User("name3", "email3"),
            User("name4", "email4"),
            User("name5", "email5"),
            User("name6", "email6"),
            User("name7", "email7"),
            User("name8", "email8"),

        )

        val linearLayoutManager = LinearLayoutManager(this)
//        val gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewUser = findViewById(R.id.users_rv)
//        recyclerViewUser.layoutManager = gridLayoutManager
        recyclerViewUser.layoutManager = linearLayoutManager

        recyclerViewUser.adapter = UserListAdapter(list, this)


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