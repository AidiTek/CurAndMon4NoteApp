package com.example.noteapp.ui.fragments.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.util.query
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentChatBinding
import com.example.noteapp.ui.adapters.ChatAdapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val chatAdapter = ChatAdapter()
    private val db = Firebase.firestore
    private lateinit var quarry: Query
    private lateinit var listener: ListenerRegistration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListner()
        ubserverMessage()
    }

    private fun initialize() {
        binding.rvChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    private fun setupListner() {
        binding.btnSend.setOnClickListener {

            val user = hashMapOf(
                "name" to binding.etChat.text.toString()
            )
            db.collection("user").add(user).addOnCompleteListener { }

            binding.etChat.text.clear()

        }
    }

    private fun ubserverMessage() {

        quarry = db.collection("user")
        listener = quarry.addSnapshotListener { value, error ->

            if (error != null) {
                return@addSnapshotListener
            }

            value?.let { querySnapshot ->
                val messages = mutableListOf<String>()
                for (doc in querySnapshot.documents) {
                    val message = doc.getString("name")
                    message?.let {
                        messages.add(it)
                    }
                }

                chatAdapter.submitList(messages)

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        listener.remove()
    }
}
