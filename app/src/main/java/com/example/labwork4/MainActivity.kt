package com.example.labwork4

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.labwork4.fragments.MainFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStatusBarOff()
        goToFragment(this, MainFragment())
    }

    override fun onStart() {
        super.onStart()
        val data = Data.newInstance()
        data.actionBar = supportActionBar
        if (data.fragment != null)
            goToFragment(this, data.fragment!!)

    }

    private fun setStatusBarOff(){
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            Log.i("test", "test")
            goToFragment(this, MainFragment())
        }
        return true
    }

}