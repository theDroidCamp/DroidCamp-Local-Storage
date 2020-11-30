package com.project.segunfrancis.droidcamplocal_storage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.project.segunfrancis.droidcamplocal_storage.databinding.FragmentLoginBinding
import com.project.segunfrancis.droidcamplocal_storage.model.SignInModel
import com.project.segunfrancis.droidcamplocal_storage.util.SharedPrefUtil
import com.project.segunfrancis.droidcamplocal_storage.util.SharedPrefUtil.LoginState.*
import com.project.segunfrancis.droidcamplocal_storage.util.showToast
import com.project.segunfrancis.droidcamplocal_storage.util.verifyEmail
import com.project.segunfrancis.droidcamplocal_storage.util.verifyPassword

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val prefUtil: SharedPrefUtil by lazy {
        SharedPrefUtil(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    /*
     * Handling click events and other user interface logic should be done here
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpText.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
        binding.loginButton.setOnClickListener {
            if (verifyEmail(this@LoginFragment, binding.emailEditText.text.toString()) &&
                verifyPassword(this@LoginFragment, binding.passwordEditText.text.toString())
            ) {
                val signInModel = SignInModel(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
                when (prefUtil.login(signInModel)) {
                    SUCCESS -> {
                        val signUpModel = prefUtil.getUserDetails(signInModel)
                        // Send the arguments to the UserDetails fragments using Nav Actions
                        val directions =
                            LoginFragmentDirections.actionLoginFragmentToUserDetailsFragment(
                                signUpModel
                            )
                        findNavController().navigate(directions)
                    }
                    NOT_REGISTERED -> {
                        showToast("This email address has not been registered\nSign up to register")
                    }
                    INCORRECT_PASSWORD -> {
                        showToast("Password is incorrect. Try again")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Set the binding to null when fragment views are destroyed in order to avoid memory leaks
        _binding = null
    }
}