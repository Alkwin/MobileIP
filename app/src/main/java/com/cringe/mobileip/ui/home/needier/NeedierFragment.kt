package com.cringe.mobileip.ui.home.needier

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.databinding.FragmentNeedierBinding
import com.cringe.mobileip.server.model.utils.Result
import com.cringe.mobileip.server.model.utils.tags.Product
import com.cringe.mobileip.server.model.utils.tags.Service
import com.cringe.mobileip.server.model.utils.tags.Tag
import com.cringe.mobileip.ui.home.needier.adapters.TagStatus
import com.cringe.mobileip.ui.home.needier.adapters.TagsAdapter
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

    private val tagsAdapter by lazy { TagsAdapter(NeedierViewModel.tags) }

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
            recyclerView.adapter = tagsAdapter
            sendRequestButton.setOnClickListener {
                needierViewModel.sendRequest(tagsAdapter.tags, infoEditText.text.toString())
            }
        }

        if(NeedierViewModel.tags.size < 2) {
            needierViewModel.requestTags()
        }

        needierViewModel.databaseAnswer.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> {
                    needierViewModel.sendMatchRequest()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
                is Result.Exception -> {
                    Toast.makeText(requireContext(), "Exception", Toast.LENGTH_LONG).show()
                }
            }
        }

        needierViewModel.matchAnswer.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> {
                    needierViewModel.getChoseHelperAlert(requireContext(), it.data.helperResponses).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
                is Result.Exception -> {
                    Toast.makeText(requireContext(), "Exception", Toast.LENGTH_LONG).show()
                }
            }
        }

        needierViewModel.selectHelperAnswer.observe(viewLifecycleOwner){
            when(it) {
                is Result.Success -> {
                    Toast.makeText(requireContext(), "Matched", Toast.LENGTH_LONG).show()
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show()
                }
                is Result.Exception -> {
                    Toast.makeText(requireContext(), "Exception", Toast.LENGTH_LONG).show()
                }
            }
        }

        needierViewModel.tagAnswerLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> {
                    val newMap = it.data.map { tagPair ->
                        TagStatus(Tag(tagPair.key), if(tagPair.value == "service") Service(false) else Product(0.0))
                    }.toMutableList()
                    Handler(Looper.getMainLooper()).post {
                        NeedierViewModel.tags.clear()
                        NeedierViewModel.tags.addAll(newMap)
                        binding.recyclerView.adapter?.notifyDataSetChanged()
                    }
                }
                is Result.Failure -> {
                    Toast.makeText(requireContext(), "Failure when requesting tags", Toast.LENGTH_LONG).show()
                }
                is Result.Exception -> {
                    Toast.makeText(requireContext(), "Exception when requesting tags", Toast.LENGTH_LONG).show()
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}