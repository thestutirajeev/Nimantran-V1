package com.example.nimantran.ui.main.clientguests

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimantran.R

class AddMyGuestFragment : Fragment() {

    companion object {
        fun newInstance() = AddMyGuestFragment()
    }

    private lateinit var viewModel: AddMyGuestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_my_guest, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddMyGuestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}