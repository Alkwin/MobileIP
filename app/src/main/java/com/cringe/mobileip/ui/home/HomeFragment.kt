package com.cringe.mobileip.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.databinding.FragmentHomeBinding
import com.cringe.mobileip.ui.data.Category
import com.cringe.mobileip.ui.home.adapters.CategoriesAdapter
import com.cringe.mobileip.ui.home.adapters.CategoryAndStatus
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val categories = listOf(
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
    ).map{ CategoryAndStatus(Category(it), false) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding) {
            recyclerView.layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
                alignItems = AlignItems.CENTER
            }
            recyclerView.adapter = CategoriesAdapter(categories)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}