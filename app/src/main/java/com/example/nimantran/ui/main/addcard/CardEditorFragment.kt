package com.example.nimantran.ui.main.addcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.nimantran.databinding.FragmentCardEditorBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ja.burhanrashid52.photoeditor.PhotoEditor


class CardEditorFragment : Fragment() {
    private var _binding: FragmentCardEditorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TemplateCardViewModel by activityViewModels()
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardEditorBinding.inflate(inflater, container, false)
        binding.templateCardViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedTemplate.value?.let {
            val editor = PhotoEditor.Builder(requireContext(), binding.photoEditorView)
                .setPinchTextScalable(true)
                .build()
            editor.setBrushDrawingMode(true)
            binding.photoEditorView.source.load(it.url)
        }

    }


}