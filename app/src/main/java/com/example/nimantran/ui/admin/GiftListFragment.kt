package com.example.nimantran.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nimantran.R
import com.example.nimantran.databinding.FragmentGiftListBinding

class GiftListFragment : Fragment() {
    private lateinit var binding: FragmentGiftListBinding
    private var _binding: FragmentGiftListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGiftListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //to load data to the recycler view
//        binding.recyclerViewGiftList.adapter = GiftListAdapter()
        binding.recyclerViewGiftList.layoutManager = GridLayoutManager(this.context, 2)
        //to search the data
        binding.searchViewGiftList.clearFocus()        //To remove focus from search bar
        binding.searchViewGiftList.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return false
            }
        })
    }

    private fun filterList(newText: String?) {
        //to filter the data
    }

    companion object {
    }
}