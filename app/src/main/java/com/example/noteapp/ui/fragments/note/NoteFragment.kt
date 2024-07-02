package com.example.noteapp.ui.fragments.note

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModels
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.interfaces.OnClickItem
import com.example.noteapp.ui.adapters.NoteAdater
import com.example.noteapp.utils.SharedPreference


class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding

    private val noteAdater = NoteAdater(this, this)

    private var isLinearLayout = true

    private lateinit var sharedPreference :SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        sharedPreference = SharedPreference()
        sharedPreference.unit(requireContext())
        isLinearLayout = sharedPreference.isLinearLayout
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListners()
        initialize()
        detData()
        setupImgShape()
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
        App().getInstenc()?.notesDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdater.submitList(it)
        }

    }

    private fun setupImgShape() {

        binding.imgShape.setOnClickListener {
            val newLayoutState = !isLinearLayout
            setRecyclerViewLayoutManager(newLayoutState)
            updateImgShape(newLayoutState)

            if (newLayoutState != isLinearLayout) {
                // Сохранение в SharedPreferences только если состояние изменилось
                isLinearLayout = newLayoutState
                sharedPreference.isLinearLayout = isLinearLayout
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecyclerViewLayoutManager(isLinearLayout: Boolean) {

        binding.rvNote.layoutManager = if (isLinearLayout) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), 2)
        }
        noteAdater.notifyDataSetChanged()

    }

    private fun updateImgShape(isLinearLayout: Boolean) {

        val iconRes = if (isLinearLayout) {
            R.drawable.shape
        } else {
            R.drawable.baseline_format_line_spacing_24
        }
        binding.imgShape.setImageResource(iconRes)
    }


    override fun onLongClick(noteModel: NoteModels) {

        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить?")

            setPositiveButton("ДА") { dialog, which ->

                App.appDataBase?.notesDao()?.deletNot(noteModel)
            }

            setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModels) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDitailFragment(noteModel.id)
        findNavController().navigate(action)

    }

}