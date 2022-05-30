package com.example.mynotesapp.presentation.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.FragmentListBinding
import com.example.mynotesapp.presentation.itemadapter.ItemAdapterFragment

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mListViewModel: ListViewModel
    private val adapter = ItemAdapterFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter

        mListViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        mListViewModel.listItems.observe(viewLifecycleOwner) { item ->
            adapter.addItem(item)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_newItemFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_logOut -> {
            logOut()
            true
        }
        R.id.menu_deleteAll -> {
            deleteAll()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun logOut() {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setTitle("Do you want to log out?")
            setPositiveButton("Yes") { _, _ ->
                findNavController().navigate(R.id.action_listFragment_to_loginFragment)
            }
            setNegativeButton("No") { _, _ -> }
            create().show()
        }
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setTitle("Are you sure to delete all items?")
            setMessage("Items cannot be restored")
            setPositiveButton("Delete All") { _, _ ->
                mListViewModel.deleteAll()
            }
            setNegativeButton("No") { _, _ -> }
            create().show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}