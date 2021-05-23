package com.cringe.mobileip.ui.home.helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cringe.mobileip.R
import com.cringe.mobileip.databinding.FragmentHelperBinding

class HelperFragment : Fragment() {

    private lateinit var helperViewModel: HelperViewModel

    private var _binding: FragmentHelperBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelperBinding.inflate(inflater, )

        helperViewModel = ViewModelProvider(this).get(HelperViewModel::class.java)

        with(binding) {
            Glide.with(this@HelperFragment).load(R.drawable.running_stick_man).into(gifView)
        }

        return binding.root
    }
}