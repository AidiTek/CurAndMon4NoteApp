package com.example.noteapp.ui.fragments.note

import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModels
import com.example.noteapp.databinding.FragmentNoteDitailBinding
import java.text.SimpleDateFormat
import java.util.Locale

class NoteDitailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDitailBinding
    private var selectedColor: Int = Color.BLACK

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDitailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        updateColorIndicators()
        updateVisibility()
        updateTextViewVisibility()

    }


    private fun setupListeners() {

        val calendar = Calendar.getInstance()
        val dateFormatMonth = SimpleDateFormat("dd MMMM", Locale.getDefault())
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val curentTimeMonth = dateFormatMonth.format(calendar.time)
        val curentTimeHours = dateFormat.format(calendar.time)

        binding.tvTimeMonth.text = curentTimeMonth
        binding.tvTimeHourth.text = curentTimeHours

        binding.imgArrowBack.setOnClickListener {

            findNavController().navigate(R.id.noteFragment)

        }

        binding.blackContainer.setOnClickListener {
            selectColor(Color.BLACK)
        }

        binding.whiteContainer.setOnClickListener {
            selectColor(Color.WHITE)
        }

        val customRedColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.redContainer.setOnClickListener {
            selectColor(customRedColor)
        }

        binding.tvSaved.setOnClickListener {
            saveNote()
        }
    }

    private fun selectColor(color: Int) {

        selectedColor = color
        updateColorIndicators()
    }

    private fun updateColorIndicators() {

        binding.blackIndicator.visibility =
            if (selectedColor == Color.BLACK) View.VISIBLE else View.GONE
        binding.whiteIndicator.visibility =
            if (selectedColor == Color.WHITE) View.VISIBLE else View.GONE
        binding.redIndicator.visibility =
            if (selectedColor == ContextCompat.getColor(requireContext(), R.color.red)) View.VISIBLE else View.GONE
    }

    private fun updateVisibility()= with(binding) {

        etTitle.addTextChangedListener{
            updateTextViewVisibility()
        }
        etDescription.addTextChangedListener {
            updateTextViewVisibility()
        }

    }

    private fun updateTextViewVisibility() {
        val isTitleEmpty = binding.etTitle.text.isNullOrEmpty()
        val isDescriptionEmpty = binding.etDescription.text.isNullOrEmpty()

        binding.tvSaved.visibility = if (isTitleEmpty || isDescriptionEmpty) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun saveNote() {

        val tvTimeMonth = binding.tvTimeMonth.text.toString()

        val tvTimeHours = binding.tvTimeHourth.text.toString()
        val etTitle = binding.etTitle.text.toString()
        val etDescription = binding.etDescription.text.toString()


        App().getInstenc()?.notesDao()
            ?.noteInsert(
                NoteModels(
                    etTitle,
                    etDescription,
                    selectedColor,
                    tvTimeMonth,
                    tvTimeHours
                )
            )

        findNavController().navigateUp()
    }
}