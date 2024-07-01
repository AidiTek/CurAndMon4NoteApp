package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = FragmentOnBoardPagingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

    }
    private fun initialize() = with(binding) {


        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){

            0->{
                tvTxt.text="Очень удобный функционал"
                lottiAnimationView.setAnimation(R.raw.firstanimation)

            }
            1->{
                tvTxt.text = "Быстрый и качественный продукт"
                lottiAnimationView.setAnimation(R.raw.second_animation)
            }
            2->{
                tvTxt.text = "Куча функций и интересных фишек"
                lottiAnimationView.setAnimation(R.raw.third_animation)

            }

        }

    }
    companion object{

        const val ARG_ONBOARD_POSITION="onBoard"

    }
}