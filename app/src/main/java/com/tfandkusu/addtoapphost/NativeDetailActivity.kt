package com.tfandkusu.addtoapphost

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

class NativeDetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_native_detail)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val textView = findViewById<android.widget.TextView>(R.id.text)
        textView.text = "Detail $id"

        val button = findViewById<android.widget.Button>(R.id.startFlutter)
        button.setOnClickListener {
            val flutterEngine = FlutterEngineCache
                .getInstance().get(MyApplication.FLUTTER_ENGINE_ID_1)
            if (flutterEngine != null) {
                val methodChannel = MethodChannel(
                    flutterEngine.dartExecutor.binaryMessenger,
                    "com.tfandkusu.ga913flutter"
                )
                methodChannel.invokeMethod("navigateToDetail", mapOf("id" to id))
            }
            val intent = Intent(this, SecondFlutterActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_ID = "id"
    }
}