package com.example.nimantran.ui.admin.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nimantran.databinding.FragmentNotificationListBinding

class NotificationListFragment : Fragment() {

    private lateinit var binding: FragmentNotificationListBinding
    private var _binding: FragmentNotificationListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //to load data to the recycler view
//        binding.recyclerViewNotificationList.adapter = NotificationListAdapter()
        binding.recyclerViewNotificationList.layoutManager = LinearLayoutManager(this.context)
        //to search the data
        binding.searchViewNotificationList.clearFocus()        //To remove focus from search bar
        binding.searchViewNotificationList.setOnQueryTextListener(object :
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