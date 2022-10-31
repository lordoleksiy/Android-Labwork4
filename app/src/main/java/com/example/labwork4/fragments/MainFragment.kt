package com.example.labwork4.fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.labwork4.Data
import com.example.labwork4.R
import com.example.labwork4.goToFragment


class MainFragment : Fragment() {
    private val data = Data.newInstance()
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
                Toast.makeText(context, "No file is chosen!", Toast.LENGTH_SHORT).show()
                return@registerForActivityResult
            }
            data.uri = it
            goToFragment(requireActivity(), VideoFragment())
        }

        val editText: EditText = view.findViewById(R.id.editText)


        view.findViewById<Button>(R.id.videoFileButton).setOnClickListener{
            getVideoUri.launch("video/mp4")
        }

        view.findViewById<Button>(R.id.exampleVideoButton).setOnClickListener{
            data.uri = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.testvideo)
            goToFragment(requireActivity(), VideoFragment())
        }

        view.findViewById<Button>(R.id.internetVideoButton).setOnClickListener{
            val link = editText.text.toString()
            if (link.isEmpty()) return@setOnClickListener
            data.uri = Uri.parse(link)
            goToFragment(requireActivity(), VideoFragment())
        }
    }
}