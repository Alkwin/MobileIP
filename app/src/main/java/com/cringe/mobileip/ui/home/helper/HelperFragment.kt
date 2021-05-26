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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelperBinding.inflate(inflater, )

        helperViewModel = ViewModelProvider(this).get(HelperViewModel::class.java)

        with(binding) {

        }

        updateUI()

        return binding.root
    }

    private fun updateUI() {
        with(binding) {
            val order = helperViewModel.currentOrder
            if (order != null) {
                with(order) {
                    usernameTextView.text = userName
                    tagsTextView.text = tags.toString()
                    detailsTextView.text = details
                }
                finishOrderButton.isEnabled = true
                Glide.with(this@HelperFragment).load(R.drawable.running_stick_man).into(gifView)
            } else {
                Glide.with(this@HelperFragment).load(R.drawable.sitting_stick_man).into(gifView)
                usernameTextView.text = "N/A"
                tagsTextView.text = "N/A"
                detailsTextView.text = "N/A"
                finishOrderButton.isEnabled = false
            }
        }
    }
}