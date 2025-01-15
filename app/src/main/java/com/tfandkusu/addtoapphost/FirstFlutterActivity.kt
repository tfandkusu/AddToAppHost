package com.tfandkusu.addtoapphost

import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class FirstFlutterActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getCachedEngineId(): String {
        return MyApplication.FLUTTER_ENGINE_ID_1
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "com.tfandkusu.ga913flutter"
        ).setMethodCallHandler { call, result ->
            if (call.method == "navigateToDetail") {
                call.argument<Int>("id")?.let {
                    val intent = Intent(this, NativeDetailActivity::class.java)
                    intent.putExtra(NativeDetailActivity.EXTRA_ID, it)
                    startActivity(intent)
                    result.success(null)
                }
            }
        }
    }
}