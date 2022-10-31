package com.example.labwork4

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun goToFragment(activity: FragmentActivity, fragment: Fragment){
    activity.supportFragmentManager
        .beginTransaction()
        .replace(R.id.placeholder, fragment)
        .commit()
}