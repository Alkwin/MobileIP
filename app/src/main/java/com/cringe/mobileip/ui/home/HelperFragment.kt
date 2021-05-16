package com.cringe.mobileip.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cringe.mobileip.R
import com.cringe.mobileip.databinding.FragmentHelperBinding

class HelperFragment : Fragment() {

    private lateinit var needierViewModel: HelperViewModel

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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_helper, container, false)
    }
}