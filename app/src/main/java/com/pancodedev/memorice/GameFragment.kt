package com.pancodedev.memorice

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.pancodedev.memorice.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment GameFragment.
         */
        @JvmStatic
        fun newInstance() = GameFragment()
    }

    private fun setupOnClickListeners() {
        binding.cardImage01.setOnClickListener {
            turnoverCard(binding.cardImage01, binding.imgIcon01)
        }

        binding.cardImage02.setOnClickListener {
            turnoverCard(binding.cardImage02, binding.imgIcon02)
        }

        binding.cardImage03.setOnClickListener {
            turnoverCard(binding.cardImage03, binding.imgIcon03)
        }

        binding.cardImage04.setOnClickListener {
            turnoverCard(binding.cardImage04, binding.imgIcon04)
        }

        binding.cardImage05.setOnClickListener {
            turnoverCard(binding.cardImage05, binding.imgIcon05)
        }

        binding.cardImage06.setOnClickListener {
            turnoverCard(binding.cardImage06, binding.imgIcon06)
        }

        binding.cardImage07.setOnClickListener {
            turnoverCard(binding.cardImage07, binding.imgIcon07)
        }

        binding.cardImage08.setOnClickListener {
            turnoverCard(binding.cardImage08, binding.imgIcon08)
        }

        binding.cardImage09.setOnClickListener {
            turnoverCard(binding.cardImage09, binding.imgIcon09)
        }

        binding.cardImage10.setOnClickListener {
            turnoverCard(binding.cardImage10, binding.imgIcon10)
        }

        binding.cardImage11.setOnClickListener {
            turnoverCard(binding.cardImage11, binding.imgIcon11)
        }

        binding.cardImage12.setOnClickListener {
            turnoverCard(binding.cardImage12, binding.imgIcon12)
        }

        binding.cardImage13.setOnClickListener {
            turnoverCard(binding.cardImage13, binding.imgIcon13)
        }

        binding.cardImage14.setOnClickListener {
            turnoverCard(binding.cardImage14, binding.imgIcon14)
        }

        binding.cardImage15.setOnClickListener {
            turnoverCard(binding.cardImage15, binding.imgIcon15)
        }

        binding.cardImage16.setOnClickListener {
            turnoverCard(binding.cardImage16, binding.imgIcon16)
        }
    }

    /**
     * Changes Card and Image states as if it's turned over.
     */
    private fun turnoverCard(card: CardView, icon: ImageView) {
        val cardColor = TypedValue()

        if(icon.visibility == ImageView.GONE) {
            icon.visibility = ImageView.VISIBLE
            requireContext().theme.resolveAttribute(R.attr.colorSecondary, cardColor, true)
            card.setCardBackgroundColor(cardColor.data)
        }
        else {
            icon.visibility = ImageView.GONE
            requireContext().theme.resolveAttribute(R.attr.colorPrimary, cardColor, true)
            card.setCardBackgroundColor(cardColor.data)
        }
    }
}