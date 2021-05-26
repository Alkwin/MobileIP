package com.cringe.mobileip.ui.orders.needier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cringe.mobileip.databinding.FragmentNeedierBinding

class NeedierFragment : Fragment() {

    private var _binding: FragmentNeedierBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNeedierBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding) {

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}