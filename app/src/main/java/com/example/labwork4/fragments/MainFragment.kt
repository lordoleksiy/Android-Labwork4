package com.example.labwork4.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.labwork4.R
import com.example.labwork4.goToFragment


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getVideoUri = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it == null){
                Toast.makeText(context, "No file is choosed!", Toast.LENGTH_SHORT).show()
                return@registerForActivityResult
            }
            goToFragment(requireActivity(), VideoFragment(it))
        }


        view.findViewById<Button>(R.id.videoFileButton).setOnClickListener{
            getVideoUri.launch("video/mp4")
        }
        view.findViewById<Button>(R.id.exampleVideoButton).setOnClickListener{
            val myVideoUri: Uri = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.testvideo)
            goToFragment(requireActivity(), VideoFragment(myVideoUri))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}