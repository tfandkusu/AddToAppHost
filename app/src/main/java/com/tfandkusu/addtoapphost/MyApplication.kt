package com.tfandkusu.addtoapphost

import android.app.Application
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val flutterEngineGroup = FlutterEngineGroup(this)
        val flutterEngine1 = flutterEngineGroup.createAndRunDefaultEngine(this)
        FlutterEngineCache
            .getInstance()
            .put(FLUTTER_ENGINE_ID_1, flutterEngine1)
        val flutterEngine2 = flutterEngineGroup.createAndRunDefaultEngine(this)
        FlutterEngineCache
            .getInstance()
            .put(FLUTTER_ENGINE_ID_2, flutterEngine2)
    }

    companion object {
        const val FLUTTER_ENGINE_ID_1 = "my_engine_id_1"
        const val FLUTTER_ENGINE_ID_2 = "my_engine_id_2"
    }
}
