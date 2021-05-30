package com.cringe.mobileip.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.R
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.databinding.FragmentHomeBinding
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.server.model.utils.tags.Product
import com.cringe.mobileip.server.model.utils.tags.Service
import com.cringe.mobileip.server.model.utils.tags.Tag
import com.cringe.mobileip.ui.home.adapters.TagStatus
import com.cringe.mobileip.ui.home.adapters.TagsAdapter
import com.cringe.mobileip.ui.orders.needier.NeedierFragment
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val tagsAdapter by lazy { TagsAdapter(HomeViewModel.tags) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        with(binding) {
            recyclerView.layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
                alignItems = AlignItems.CENTER
            }
            recyclerView.adapter = tagsAdapter
            sendRequestButton.setOnClickListener {
                pressedSendButton()
            }
            binding.titleMain.text = if(AuthenticationManager.isHelper == true) "Ofera ajutor" else "Cere ajutor"
        }

        if(HomeViewModel.tags.size < 2) {
            homeViewModel.requestTags()
        }

        if (AuthenticationManager.isHelper == false) {
            registerNeederListeners()
        } else {
            registerHelperListeners()
        }

        homeViewModel.tagAnswerLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    val newMap = it.data.map { tagPair ->
                        TagStatus(
                            Tag(tagPair.key),
                            if (tagPair.value == "service") Service(false) else Product(0.0)
                        )
                    }.toMutableList()
                    Handler(Looper.getMainLooper()).post {
                        HomeViewModel.tags.clear()
                        HomeViewModel.tags.addAll(newMap)
                        binding.recyclerView.adapter?.notifyDataSetChanged()
                    }
                }
                is Result.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Failure when requesting tags",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Result.Exception -> {
                    Toast.makeText(
                        requireContext(),
                        "Exception when requesting tags",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return root
    }

    private fun pressedSendButton() {
        if (AuthenticationManager.isHelper == false) {
            homeViewModel.sendNeederRequest(tagsAdapter.tags, binding.infoEditText.text.toString())
        } else {
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.select_distance_alert, null)

            AlertDialog.Builder(requireContext())
                .setView(view)
                .setPositiveButton("Trimitere") { dialog, _ ->
                    homeViewModel.sendHelperRequest(
                        tagsAdapter.tags,
                        binding.infoEditText.text.toString(),
                        view.findViewById<EditText>(R.id.editText).text.toString()
                    )
                    dialog.dismiss()
                }
                .setNegativeButton("Anulare") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun registerHelperListeners() {
        homeViewModel.sendHelperAnswer.observe(viewLifecycleOwner) {
            logger.info { "Sending selected helper: $it" }
            when (it) {
                is Result.Success -> {
                    Toast.makeText(requireContext(), "Sent", Toast.LENGTH_LONG).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
                is Result.Exception ->
                    Toast.makeText(requireContext(), "Exception", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun registerNeederListeners() {
        homeViewModel.databaseAnswer.observe(viewLifecycleOwner) {
            logger.info { "Received confirmation: $it; Sending a match request" }
            when (it) {
                is Result.Success -> {
                    homeViewModel.sendMatchRequest()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
                is Result.Exception ->
                    Toast.makeText(requireContext(), "Exception", Toast.LENGTH_LONG).show()

            }
        }

        homeViewModel.matchAnswer.observe(viewLifecycleOwner) {
            logger.info { "Received a list of helpers to choose from: $it" }
            when (it) {
                is Result.Success -> {
                    homeViewModel.getChoseHelperAlert(requireContext(), it.data.helperResponses)
                        .show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
                is Result.Exception -> {
                    Toast.makeText(requireContext(), "Exception", Toast.LENGTH_LONG).show()
                }
            }
        }

        homeViewModel.selectHelperAnswer.observe(viewLifecycleOwner) {
            logger.info { "Matched with: $it" }
            when (it) {
                is Result.Success -> {
                    Toast.makeText(requireContext(), "Matched", Toast.LENGTH_LONG).show()
                    NeedierFragment.currentOrderHelperData = homeViewModel.selectedHelper
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
                is Result.Exception -> {
                    Toast.makeText(requireContext(), "Exception", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}