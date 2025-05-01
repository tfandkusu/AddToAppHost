package com.tfandkusu.addtoapphost

import android.annotation.SuppressLint
import android.content.Context
import android.content.MutableContextWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import io.flutter.plugin.platform.PlatformView

@SuppressLint("InflateParams")
class MyPlatformView(private val context: Context) : PlatformView {

    private val viewGroup: ViewGroup by lazy {
        val layoutInflater = context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater
        layoutInflater.inflate(
            R.layout.view_fragment_my,
            null
        ) as ViewGroup
    }


    override fun getView(): View {
        return viewGroup
    }

    override fun dispose() {
        if(context is MutableContextWrapper) {
            val baseContext = context.baseContext
            if(baseContext is FragmentActivity) {
                val myFragment =
                    baseContext.supportFragmentManager.findFragmentById(R.id.my_fragment)
                if (myFragment != null) {
                    baseContext.supportFragmentManager.beginTransaction()
                        .remove(myFragment)
                        .commit()
                }
            }
        }
    }
}