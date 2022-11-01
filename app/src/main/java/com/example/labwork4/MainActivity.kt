package com.example.labwork4

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.labwork4.fragments.MainFragment


class MainActivity : AppCompatActivity(), MainFragment.OnFragmentChange {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStatusBarOff()
        setFragment(MainFragment())
    }

    override fun onStart() {
        super.onStart()
        val data = Data.newInstance()
        data.actionBar = supportActionBar
        if (data.fragment != null)
            setFragment(data.fragment!!)

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
            setFragment(MainFragment())
        }
        return true
    }

    fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, fragment)
            .commit()
    }

    override fun changeFragment(fragment: Fragment) {
        setFragment(fragment)
    }

}