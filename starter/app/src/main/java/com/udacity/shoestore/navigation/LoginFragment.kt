package com.udacity.shoestore.navigation

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener {
            if (validateInput()) {
                loadWelcome(false)
            }
        }

        binding.createAccountButton.setOnClickListener {
            loadWelcome(true)
        }

        return binding.root
    }

    private fun loadWelcome(newAccount: Boolean) {
        val action = LoginFragmentDirections.loadWelcome("email", "authToken")
        action.newAccount = newAccount
        findNavController().navigate(action)
    }

    private fun validateInput(): Boolean {
        val email = binding.emailEditText.text
        val validEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val validPassword = binding.passwordEditText.text.isNotEmpty()
        if (validEmail) {
            binding.emailEditText.error = null
        } else {
            binding.emailEditText.error = getString(R.string.invalid_email)
        }
        if (validPassword) {
            binding.passwordEditText.error = null
        } else {
            binding.passwordEditText.error = getString(R.string.invalid_password)
        }
        return validEmail && validPassword
    }
}
