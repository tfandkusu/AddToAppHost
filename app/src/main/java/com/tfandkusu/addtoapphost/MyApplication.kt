package com.tfandkusu.addtoapphost

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.FlutterEngineGroupCache
import io.flutter.embedding.engine.dart.DartExecutor

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val flutterEngineGroup = FlutterEngineGroup(this)
        FlutterEngineGroupCache.getInstance().put(FLUTTER_ENGINE_GROUP_ID, flutterEngineGroup)
    }

    companion object {
        const val FLUTTER_ENGINE_GROUP_ID = "my_engine_group_id"
    }
}
