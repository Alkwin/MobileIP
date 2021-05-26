package com.cringe.mobileip.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.data.managers.AuthenticationManager
import com.cringe.mobileip.databinding.FragmentProfileBinding
import com.cringe.mobileip.data.managers.ProfileManager
import com.cringe.mobileip.server.model.profileinfo.InfoResponse.Companion.savedCountHelper
import com.cringe.mobileip.server.model.profileinfo.InfoResponse.Companion.savedCountNeeder
import com.cringe.mobileip.server.model.profileinfo.InfoResponse.Companion.serverInfoSaved
import com.cringe.mobileip.ui.login.LoginActivity


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private lateinit var profileList: LinearLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    companion object{

    }
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

        profileList = binding.profileList
        val test = ProfileManager();
        val pageInfo = test.getInfo();

        /* For getting the file from server
        val profileImage: ImageView = binding.profileImage
        Picasso.get().load(pageInfo.avatar).into(profileImage)
        */
        if(serverInfoSaved){
            val profileName: TextView = binding.profileName
            profileName.text = AuthenticationManager.userName

            val profileAddress: TextView = binding.profileAddress
            profileAddress.text = "Count Helper: " + savedCountHelper

            val profileNumber: TextView = binding.profileNumber
            profileNumber.text = "Count Needer: " + savedCountNeeder
        }
        else {
            if (pageInfo.success == false) {

                val profileName: TextView = binding.profileName
                profileName.text = ""

                val profileAddress: TextView = binding.profileAddress
                profileAddress.text = "Eroare de la server"

                val profileNumber: TextView = binding.profileNumber
                profileNumber.text = ""
            } else {
                val profileName: TextView = binding.profileName
                profileName.text = AuthenticationManager.userName

                val profileAddress: TextView = binding.profileAddress
                profileAddress.text = "Count Helper: " + pageInfo.countHelper

                val profileNumber: TextView = binding.profileNumber
                profileNumber.text = "Count Needer: " + pageInfo.countNeeder

                serverInfoSaved = true;
                savedCountNeeder = pageInfo.countNeeder;
                savedCountHelper = pageInfo.countHelper
            }
        }
        binding.logOutButton.setOnClickListener() {
            AuthenticationManager.logout()
            val newIntent = Intent(activity, LoginActivity::class.java)
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(newIntent)
            activity?.finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}