package app.bo.com.ucb.appinter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var welcomeText = getString(R.string.welcome_text)

        val swipe = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
        }
    }
}