package com.project.segunfrancis.droidcamplocal_storage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.segunfrancis.droidcamplocal_storage.databinding.FragmentSignUpBinding
import com.project.segunfrancis.droidcamplocal_storage.model.SignUpModel
import com.project.segunfrancis.droidcamplocal_storage.util.*

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val prefUtil: SharedPrefUtil by lazy {
        SharedPrefUtil(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    /*
     * Handling click events and other user interface logic should be done here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            if (verifyEmail(this@SignUpFragment, binding.emailEditText.text.toString()) &&
                verifyPassword(this@SignUpFragment, binding.passwordEditText.text.toString()) &&
                verifyName(this@SignUpFragment, binding.nameEditText.text.toString()) &&
                verifySchool(this@SignUpFragment, binding.schoolEditText.text.toString())
            ) {
                val signUpModel = SignUpModel(
                    name = binding.nameEditText.text.toString(),
                    email = binding.emailEditText.text.toString(),
                    school = binding.schoolEditText.text.toString(),
                    password = binding.passwordEditText.text.toString()
                )
                prefUtil.signUp(signUpModel)

                // Navigate user back to the login screen so user logs in with registered details
                requireActivity().onBackPressed()
                showToast("Sign up successful\nKindly login with your newly registered details")
            }
        }
        binding.loginText.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Set the binding to null when fragment views are destroyed in order to avoid memory leaks
        _binding = null
    }
}