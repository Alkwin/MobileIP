package com.cringe.mobileip.ui.orders.helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cringe.mobileip.R
import com.cringe.mobileip.databinding.FragmentHelperBinding
import com.cringe.mobileip.server.model.needier.database.RequestNeedierRequest
import com.cringe.mobileip.server.model.utils.Result

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

        helperViewModel.currentOrder.observe(viewLifecycleOwner) {
            if (it is Result.Success)
                updateUI(it.data)
        }

        binding.finishOrderButton.setOnClickListener { helperViewModel.finishOrder() }

        helperViewModel.finishOrderAnswer.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    Toast.makeText(requireContext(), "Finished order", Toast.LENGTH_LONG).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
                is Result.Exception ->
                    Toast.makeText(requireContext(), "Exception", Toast.LENGTH_LONG).show()
            }
        }

        helperViewModel.startRequesting()

        return binding.root
    }

    private fun updateUI(order: RequestNeedierRequest) {
        with(binding) {
            if (order.username.isNotEmpty()) {
                with(order) {
                    usernameTextView.text = username
                    tagsTextView.text = tags .map { "${it.key}${if(it.value >= 0) ": ${it.value}" else ""}"  }
                        .joinToString(separator = "\n")
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