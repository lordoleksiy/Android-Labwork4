package com.example.labwork4.fragments

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import com.example.labwork4.Data
import com.example.labwork4.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class AudioFragment : Fragment() {
    private lateinit var mp: MediaPlayer
    private lateinit var timeBar: SeekBar
    private val data = Data.newInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_audio, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        data.fragment = this
        data.actionBar?.show()
        data.actionBar?.setDisplayHomeAsUpEnabled(true)

        requireActivity().findViewById<ImageButton>(R.id.playButton).setOnClickListener{
            startAudio()
        }

        timeBar = requireActivity().findViewById(R.id.timeBar)
        val textView = requireActivity().findViewById<TextView>(R.id.textStartTime)
        mp = MediaPlayer.create(context, data.uri)
        mp.isLooping = true
        mp.setVolume(1f, 1f)
        requireActivity().findViewById<TextView>(R.id.textEndTime).text = "${mp.duration/1000/60}.${mp.duration/1000%60}"
        timeBar.max = mp.duration

        timeBar.setOnSeekBarChangeListener(
            object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser){
                        mp.seekTo(progress)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}

                override fun onStopTrackingTouch(p0: SeekBar?) {}
            } )
        Thread(Runnable{
            while (mp.isLooping){
                timeBar.progress =  mp.currentPosition

                activity?.runOnUiThread(Runnable {
                    textView.text = "${mp.currentPosition/1000/60}.${mp.currentPosition/1000%60}"
                })
                Thread.sleep(1000)
            }
        }).start()
    }

    override fun onStop() {
        super.onStop()
        mp.reset()
    }

    fun startAudio(){
        val imageButton: ImageButton = requireActivity().findViewById(R.id.playButton)
        if (mp.isPlaying){
            mp.pause()
            imageButton.setImageResource(R.drawable.play)
        }
        else{
            mp.start()
            imageButton.setImageResource(R.drawable.stop)
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = AudioFragment()
    }
}