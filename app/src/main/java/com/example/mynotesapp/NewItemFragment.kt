package com.example.mynotesapp

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mynotesapp.databinding.FragmentNewItemBinding
import kotlinx.android.synthetic.main.fragment_new_item.*

class NewItemFragment : Fragment(R.layout.fragment_new_item) {

    private var _binding: FragmentNewItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewItemBinding.inflate(inflater, container, false)

        binding.selectDate.setOnClickListener {
            showDate()
        }

        binding.addBtn.setOnClickListener {
            addItemToList()
        }

        return binding.root
    }

    private fun addItemToList() {
        val name = addName.text.toString()
        val birthday = selectDate.text.toString()
        if (inputCheck(name, birthday)) {
            //code to add to database
            Toast.makeText(requireContext(), "Item is added", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(requireView()).navigateUp()
        } else {
            Toast.makeText(requireContext(), "Fill out blank fields", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(name:String, birthday:String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(birthday))
    }
    private fun showDate() {
        val picker = DatePickerFragment()
        picker.show(childFragmentManager, "date_picker")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}