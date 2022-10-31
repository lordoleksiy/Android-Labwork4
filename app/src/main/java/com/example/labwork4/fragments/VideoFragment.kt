package com.example.labwork4.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.ActionBar
import com.example.labwork4.Data
import com.example.labwork4.MainActivity
import com.example.labwork4.R
import com.example.labwork4.goToFragment


class VideoFragment : Fragment() {
    private val data = Data.newInstance()
    private lateinit var videoView: VideoView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onStart() {
        videoView = requireActivity().findViewById(R.id.videoView)
        data.fragment = this
        setOrientation()
        setVideo()
        super.onStart()
    }

    private fun setOrientation(){
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            data.actionBar?.show()
            data.actionBar?.setDisplayHomeAsUpEnabled(true)

            videoView.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            videoView.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        }
        else{
            data.actionBar?.hide()

            videoView.layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
            videoView.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        }
    }

    private fun setVideo(){
        try {
            videoView.setVideoURI(data.uri)
            val mediaController = MediaController(context)
            videoView.setMediaController(mediaController)
            mediaController.setMediaPlayer(videoView)
        }
        catch (e: java.io.FileNotFoundException){
            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
            goToFragment(requireActivity(), MainFragment())
        }
    }
}