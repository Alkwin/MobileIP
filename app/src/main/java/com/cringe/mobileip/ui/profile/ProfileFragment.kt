package com.cringe.mobileip.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.databinding.FragmentProfileBinding
import com.cringe.mobileip.data.managers.ProfileManager
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private lateinit var profileList: LinearLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
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
        val test = ProfileManager();
        val pageInfo = test.getInfo().data;

        val profileImage: ImageView = binding.profileImage
        Picasso.get().load(pageInfo.avatar).into(profileImage)


        val profileName: TextView = binding.profileName
        profileName.text = pageInfo.first_name + " " + pageInfo.last_name

        val profileAddress: TextView = binding.profileAddress
        profileAddress.text = pageInfo.email

        val profileNumber: TextView = binding.profileNumber
        profileNumber.text = pageInfo.id.toString()



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}