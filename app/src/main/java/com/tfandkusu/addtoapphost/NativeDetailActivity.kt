package com.tfandkusu.addtoapphost

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class NativeDetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_native_detail)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val textView = findViewById<android.widget.TextView>(R.id.text)
        textView.text = "Detail $id"
    }

    companion object {
        const val EXTRA_ID = "id"
    }
}