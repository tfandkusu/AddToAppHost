package com.tfandkusu.addtoapphost

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class FirstFlutterActivity : FlutterActivity() {

    override fun getCachedEngineId(): String {
        return MyApplication.FLUTTER_ENGINE_ID
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "com.tfandkusu.ga913flutter"
        ).setMethodCallHandler { call, result ->
            if (call.method == "navigateToDetail") {
                call.argument<Int>("id")?.let {
                    FlutterHandler.onCallFlutterActivity()
                    val intent = Intent(this, NativeDetailActivity::class.java)
                    intent.putExtra(NativeDetailActivity.EXTRA_ID, it)
                    startActivityForResult(intent, 0)
                    result.success(null)
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        FlutterHandler.onCloseFlutterActivity()
    }
}