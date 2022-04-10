package com.udacity.shoestore.navigation

import android.os.Bundle
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
            loadWelcome(false)
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
}
