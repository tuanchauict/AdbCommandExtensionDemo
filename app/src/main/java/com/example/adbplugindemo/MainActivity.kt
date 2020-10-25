package com.example.adbplugindemo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.adbplugindemo.EventBusMock.KEY_BACKGROUND_COLOR
import com.example.adbplugindemo.EventBusMock.KEY_RECENT_COMMAND

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val containerView = findViewById<View>(R.id.container_view)
        val recentCommandTextView = findViewById<TextView>(R.id.recent_command)

        val consumer = object : EventBusMock.Consumer {
            override fun run(key: String, value: Any) {
                when (key) {
                    KEY_RECENT_COMMAND -> recentCommandTextView.text = value.toString()
                    KEY_BACKGROUND_COLOR -> containerView.setBackgroundColor(value as Int)
                }
            }
        }
        EventBusMock.registerConsumer(consumer)
    }
}
