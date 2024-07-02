package com.example.noteapp.ui.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.data.db.daos.NoteDao
import com.example.noteapp.data.models.NoteModels
import com.example.noteapp.databinding.ItemNoteBinding
import com.example.noteapp.interfaces.OnClickItem

class NoteAdater(private val onLongClick: OnClickItem,private val onclick :OnClickItem) :
    ListAdapter<NoteModels, NoteAdater.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteModels?) {
            binding.tvItemTitle.text = note?.title
            binding.tvItemDescription.text = note?.description
            binding.tvHoursTime.text = note?.timeHours
            binding.tvMonthTime.text = note?.timeMonth
            note?.color?.let {
                binding.root.setBackgroundColor(it)

                val backgroundDrawable =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.item_shape)
                backgroundDrawable?.let {
                    it.setColorFilter(note?.color ?: Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                    binding.root.background = it
                }

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener{

            onLongClick.onLongClick(getItem(position))
            true

        }

        holder.itemView.setOnClickListener{

            onclick.onClick(getItem(position))

        }

    }

    class DiffCallback : DiffUtil.ItemCallback<NoteModels>() {
        override fun areItemsTheSame(oldItem: NoteModels, newItem: NoteModels): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteModels, newItem: NoteModels): Boolean {
            return oldItem.id == newItem.id
        }

    }
}