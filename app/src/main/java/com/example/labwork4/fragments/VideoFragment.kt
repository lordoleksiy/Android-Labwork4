package com.example.labwork4.fragments

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.VideoView
import com.example.labwork4.R


class VideoFragment(val uri: Uri) : Fragment() {
    private lateinit var videoView: VideoView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoView = view.findViewById(R.id.videoView)

        setOrientation()
        setVideo()
    }

    private fun setOrientation(){
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            videoView.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            videoView.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        }
        else{
            videoView.layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
            videoView.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        }
    }

    private fun setVideo(){
        videoView.setVideoURI(uri)
        val mediaController = MediaController(context)
        videoView.setMediaController(mediaController)
        mediaController.setMediaPlayer(videoView)
    }

    companion object {
        @JvmStatic
        fun newInstance(uri: Uri) = VideoFragment(uri)
    }
}