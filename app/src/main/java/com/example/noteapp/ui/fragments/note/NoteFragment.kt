package com.example.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.ui.adapters.NoteAdater


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    private val noteAdater = NoteAdater()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListners()
        initialize()
        detData()
    }


    private fun setupListners() = with(binding) {

        btnAdd.setOnClickListener {

            findNavController().navigate(R.id.action_noteFragment_to_noteDitailFragment)

        }

    }

    private fun initialize() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdater

        }
    }

    private fun detData() {
        App().getInstenc()?.notesDao()?.getAll()?.observe(viewLifecycleOwner){
    noteAdater.submitList(it)
}

    }

}