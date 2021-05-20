package com.cringe.mobileip.ui.home.needier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.databinding.FragmentNeedierBinding
import com.cringe.mobileip.server.model.utils.Tag
import com.cringe.mobileip.ui.home.needier.adapters.TagsAdapter
import com.cringe.mobileip.ui.home.needier.adapters.TagAndWeight
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class NeedierFragment : Fragment() {

    private lateinit var needierViewModel: NeedierViewModel
    private var _binding: FragmentNeedierBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val tags = listOf(
        "Mancare",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie",
        "Transport",
        "Alimente",
        "Haide",
        "Medicamente",
        "Igiena",
        "Atentie"
    ).map{ TagAndWeight(Tag(it), 0.0) }.toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNeedierBinding.inflate(inflater, container, false)
        val root: View = binding.root

        needierViewModel = ViewModelProvider(this).get(NeedierViewModel::class.java)

        with(binding) {
            recyclerView.layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
                alignItems = AlignItems.CENTER
            }
            recyclerView.adapter = TagsAdapter(tags)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}