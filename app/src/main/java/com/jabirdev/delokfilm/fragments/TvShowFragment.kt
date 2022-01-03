package com.jabirdev.delokfilm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jabirdev.delokfilm.R
import com.jabirdev.delokfilm.databinding.FragmentTvShowBinding
import java.io.IOException

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private var dataJson: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataJson = getJson()
        binding.tv.text = dataJson
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getJson(): String? {
        val json: String?
        return try {
            val inputStream = requireActivity().assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
            json
        } catch (e: IOException){
            e.printStackTrace()
            null
        }
    }

}