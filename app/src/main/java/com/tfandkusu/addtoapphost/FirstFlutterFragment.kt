package com.tfandkusu.addtoapphost

import android.content.Intent
import android.util.Log
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class FirstFlutterFragment  : FlutterFragment() {

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
                    Log.d("Takada","navigateToDetail")
                    val intent = Intent(requireContext(), NativeDetailActivity::class.java)
                    intent.putExtra(NativeDetailActivity.EXTRA_ID, it)
                    startActivity(intent)
                    result.success(null)
                }
            }
        }
    }
}