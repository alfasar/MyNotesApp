package com.example.mynotesapp.presentation.registration

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mynotesapp.R
import com.example.mynotesapp.appdata.user.User
import com.example.mynotesapp.databinding.FragmentRegBinding

class RegFragment : Fragment(R.layout.fragment_reg) {

    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!

    private lateinit var mRegViewModel: RegViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegBinding.inflate(inflater, container, false)

        mRegViewModel = ViewModelProvider(this)[RegViewModel::class.java]

        binding.signButton.setOnClickListener {
            addUserToDatabase()
        }

        return binding.root
    }

    private fun addUserToDatabase() {
        val email = binding.newEmail.text.toString()
        val password = binding.newPassword.text.toString()
        if (inputCheck(email, password)) {
            //Adding to Database
            val user = User(0, email, password)
            mRegViewModel.addUserToDatabase(user)
            Toast.makeText(requireContext(), "You're signed up!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(requireView()).navigateUp()
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