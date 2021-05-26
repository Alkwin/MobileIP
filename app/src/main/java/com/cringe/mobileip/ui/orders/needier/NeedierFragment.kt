package com.cringe.mobileip.ui.orders.needier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cringe.mobileip.databinding.FragmentNeedierBinding
import com.cringe.mobileip.server.model.utils.HelperData

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

        currentOrderHelperData?.let {
            with(binding.data) {
                username.text = it.username
                address.text = it.adress
                distance.text = it.distance.toString()
                score.text = it.score.toString()
                commonResources.text = it.commonResources
                    .map { "${it.key}${if(it.value >= 0) ": ${it.value}" else ""}"  }
                    .joinToString(separator = "\n")
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var currentOrderHelperData: HelperData? = null
    }
}