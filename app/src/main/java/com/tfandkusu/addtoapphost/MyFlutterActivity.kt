package com.tfandkusu.addtoapphost

import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.FlutterFragmentActivity

class MyFlutterActivity : FlutterFragmentActivity() {

    companion object {
        const val DART_ENTRY_POINT_ARGS = "dart_entrypoint_args"
    }

    override fun createFlutterFragment(): FlutterFragment {
       val flutterFragment = super.createFlutterFragment()
        flutterFragment.arguments?.putStringArrayList(
            "dart_entrypoint_args",
            intent.getStringArrayListExtra(DART_ENTRY_POINT_ARGS)
        )
        return flutterFragment
    }
}