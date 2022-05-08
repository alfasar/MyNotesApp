package com.example.mynotesapp

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mynotesapp.appdata.LoginViewModel
import com.example.mynotesapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener {
            logIn()
        }
        binding.regButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_regFragment)
        }
        return binding.root
    }

    private fun logIn() {
        val email = binding.editEmailAddress.text.toString()
        val password = binding.editPassword.text.toString()
        if (inputCheck(email, password)) {
            mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
            lifecycleScope.launch {
                val emailList = mLoginViewModel.getUserEmail(email)
                if (emailList != null) {
                    if (emailList.password == password) {
                        Toast.makeText(requireContext(), "Logged in as $email", Toast.LENGTH_LONG)
                            .show()
                        findNavController().navigate(R.id.action_loginFragment_to_listFragment)
                    } else {
                        Toast.makeText(requireContext(), "Invalid password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Invalid email", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Fill out blank fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(email: String, password: String): Boolean {
        return !(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}