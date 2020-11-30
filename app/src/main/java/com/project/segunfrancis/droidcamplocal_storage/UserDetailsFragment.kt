package com.project.segunfrancis.droidcamplocal_storage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.project.segunfrancis.droidcamplocal_storage.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    /*
     * Handling click events and other user interface logic should be done here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDetails = args.userDetails
        binding.apply {
            nameText.text = userDetails.name
            emailText.text = userDetails.email
            schoolText.text = userDetails.school
        }
        binding.logoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_userDetailsFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Set the binding to null when fragment views are destroyed in order to avoid memory leaks
        _binding = null
    }
}