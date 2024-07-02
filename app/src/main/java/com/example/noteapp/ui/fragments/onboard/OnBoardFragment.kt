package com.example.noteapp.ui.fragments.onboard

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapters.OnBoardViewPagerAdapter
import com.example.noteapp.utils.SharedPreference
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListner()
    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            val custumView = LayoutInflater.from(binding.tabLayout.context)
                .inflate(R.layout.tab_item, binding.tabLayout, false)
            val tabIcon = custumView.findViewById<ImageView>(R.id.tab_icon)
            tabIcon.setImageResource(getTabIcon(position))

            tab.customView = custumView
        }.attach()
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            0 -> R.drawable.circle_selected
            1 -> R.drawable.circle_selected
            2 -> R.drawable.circle_selected
            else -> R.id.tab_icon // Замените на вашу иконку по умолчанию или исходную
        }
    }

    private fun setupListner() = with(binding.viewPager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                resetTab()

                val selectedTab = binding.tabLayout.getTabAt(position)
                selectedTab?.customView?.findViewById<ImageView>(R.id.tab_icon)
                    ?.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red))



                if (position == 2) {
                    binding.tvSkip.visibility = View.INVISIBLE
                    binding.tvStart.visibility = View.VISIBLE
                } else {
                    binding.tvSkip.visibility = View.VISIBLE
                    binding.tvStart.visibility = View.INVISIBLE
                }

            }

        })
        binding.tvSkip.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 2, true)
            }
        }
        binding.tvStart.setOnClickListener {
            val sharedPreference = SharedPreference()
            sharedPreference.unit(requireContext())

            sharedPreference.isBoard = true

            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)

        }

    }

    private fun resetTab() {
        // Сброс всех иконок в исходный цвет (если нужно)
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView?.findViewById<ImageView>(R.id.tab_icon)
                ?.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey
                    )
                )// замените R.color.original_color на ваш исходный цвет
        }
    }
}