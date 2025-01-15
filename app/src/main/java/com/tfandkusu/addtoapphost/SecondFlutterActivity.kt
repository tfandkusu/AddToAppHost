package com.tfandkusu.addtoapphost

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class SecondFlutterActivity : FlutterActivity() {

    override fun getCachedEngineId(): String {
        return MyApplication.FLUTTER_ENGINE_ID_1
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
}