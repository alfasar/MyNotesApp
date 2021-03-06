package com.example.mynotesapp.presentation.itemadapter

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mynotesapp.R
import com.example.mynotesapp.appdata.item.Item
import com.example.mynotesapp.databinding.FragmentNewItemBinding
import com.example.mynotesapp.presentation.list.ListViewModel

class NewItemFragment : Fragment(R.layout.fragment_new_item) {

    private var _binding: FragmentNewItemBinding? = null
    private val binding get() = _binding!!

    private lateinit var mListViewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewItemBinding.inflate(inflater, container, false)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.selectDate.setOnClickListener {
            val dataPicker = DatePickerDialog(
                requireContext(), R.style.DatePickerStyle,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    val chosenDate = "$dayOfMonth.${month + 1}.$year"
                    binding.selectDate.text = chosenDate
                },
                year,
                month,
                day
            )
            dataPicker.show()
        }

        binding.addBtn.setOnClickListener {
            addItemToList()
        }

        return binding.root
    }

    private fun addItemToList() {
        val name = binding.addName.text.toString()
        val birthday = binding.selectDate.text.toString()
        mListViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        if (inputCheck(name, birthday)) {
            val item = Item(0, name, birthday)
            mListViewModel.addItemToDatabase(item)
            Toast.makeText(requireContext(), "Item is added", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(requireView()).navigateUp()
        } else {
            Toast.makeText(requireContext(), "Fill out blank fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, birthday: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(birthday))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}