package com.application.achangelanguage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLanguage()
        setContentView(R.layout.activity_main)

        supportActionBar?.title = resources.getString(R.string.app_name)

        buChangeLang.setOnClickListener {
            showChangeLanguage()
        }
    }
    fun showChangeLanguage(){
        var list = arrayOf("Arabic", "English")
        var build = AlertDialog.Builder(this)
        build.setTitle("Choose Language")
        build.setSingleChoiceItems(list, -1) {dialog, choose ->
            if (choose == 0){
                Languages("ar")
                recreate()
            }
            if (choose == 1){
                Languages("en")
                recreate()
            }
            dialog.dismiss()
        }
        var mBuild = build.create()
        mBuild.show()
    }

    @SuppressLint("CommitPrefEdits")
    fun Languages(L:String){
        var locale = Locale(L)
        Locale.setDefault(locale)
        var config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config,
            baseContext.resources.displayMetrics)

        var editor = getSharedPreferences("Language", Activity.MODE_PRIVATE).edit()
        editor.putString("My_Language", L)
        editor.apply()
    }

    private fun loadLanguage(){
        var sp = getSharedPreferences("Language", Activity.MODE_PRIVATE)
        var language = sp.getString("My_Language", "")
        Languages(language.toString())
    }
}