package com.cringe.mobileip.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.R
import com.cringe.mobileip.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private lateinit var profileList: LinearLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        profileList=binding.profileList

        val profileImage: ImageView = binding.profileImage
        profileImage.setImageResource(R.drawable.profile_example)

        val profileName: TextView = binding.profileName
        profileName.text = binding.profileName.text

        val profileAddress: TextView = binding.profileAddress
        profileAddress.text = binding.profileAddress.text

        val profileNumber: TextView = binding.profileNumber
        profileNumber.text = binding.profileNumber.text
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}