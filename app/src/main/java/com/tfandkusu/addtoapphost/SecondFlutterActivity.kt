package com.tfandkusu.addtoapphost

import android.os.Bundle
import android.os.PersistableBundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class SecondFlutterActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        FlutterHandler.onCreateFlutterActivity()
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun getCachedEngineId(): String {
        return MyApplication.FLUTTER_ENGINE_ID
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "com.tfandkusu.ga913flutter"
        ).setMethodCallHandler { call, result ->
            if (call.method == "close") {
                finish()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        FlutterHandler.onDestroyFlutterActivity()
    }
}