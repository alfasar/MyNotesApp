package com.example.mynotesapp

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mynotesapp.databinding.FragmentRegBinding
import kotlinx.android.synthetic.main.fragment_reg.*

class RegFragment : Fragment(R.layout.fragment_reg) {

    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegBinding.inflate(inflater, container, false)

        binding.signButton.setOnClickListener {
            addUserToDatabase()
        }

        return binding.root
    }
    private fun addUserToDatabase() {
        val email = newEmail.text.toString()
        val password = newPassword.text.toString()
        if (inputCheck(email, password)) {
            //code to add to database
            Toast.makeText(requireContext(), "You're signed up!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(requireView()).navigateUp()
        } else {
            Toast.makeText(requireContext(), "Fill out blank fields", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(email:String, password: String): Boolean {
        return !(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}