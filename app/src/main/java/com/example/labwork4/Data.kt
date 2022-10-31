package com.example.labwork4

import android.net.Uri
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment

class Data (){
    var uri: Uri? = null
    var fragment: Fragment? = null
    var actionBar: ActionBar? = null


    companion object {
        private var data: Data? = null
        @JvmStatic
        fun newInstance(): Data{
            if (data === null){
                data = Data()
            }
            return data as Data
        }
    }
}