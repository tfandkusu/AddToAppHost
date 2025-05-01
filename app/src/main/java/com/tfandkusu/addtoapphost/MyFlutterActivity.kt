package com.tfandkusu.addtoapphost

import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine

class MyFlutterActivity : FlutterFragmentActivity() {

    companion object {
        const val DART_ENTRY_POINT_ARGS = "dart_entrypoint_args"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        flutterEngine.platformViewsController.registry.registerViewFactory(
            "my",
            MyPlatformViewFactory()
        )
    }

    override fun createFlutterFragment(): FlutterFragment {
       val flutterFragment = super.createFlutterFragment()
        flutterFragment.arguments?.putStringArrayList(
            DART_ENTRY_POINT_ARGS,
            intent.getStringArrayListExtra(DART_ENTRY_POINT_ARGS)
        )
        return flutterFragment
    }
}