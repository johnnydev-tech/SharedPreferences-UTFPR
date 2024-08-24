package tech.johnnydev.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
private  const val  PREFERENCE_NAME = "PREFS_STAR"
private  const val FIELD = "on"
class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private var on = false

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)

        prefs = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

        on = prefs.getBoolean(FIELD, false)
        setOn(on)

    }


    private fun setOn(on: Boolean) {
        this.on = on
        if (on) {
            imageView.setImageResource(android.R.drawable.star_big_on)
            button.text = getString(R.string.turn_off)
        } else {
            imageView.setImageResource(android.R.drawable.star_big_off)
            button.text = getString(R.string.turn_on)
        }
    }

    fun buttonOnOffClick(view: View) {
        if (on) {
            imageView.setImageResource(android.R.drawable.star_big_off)
            button.text = getString(R.string.turn_on)
        } else {
            imageView.setImageResource(android.R.drawable.star_big_on)
            button.text = getString(R.string.turn_off)
        }
        on = !on
        prefs.edit().putBoolean(FIELD, on).apply()


    }
}