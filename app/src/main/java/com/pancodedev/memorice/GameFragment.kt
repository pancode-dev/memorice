package com.pancodedev.memorice

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
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

        fillCards(getIconsList())
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
     * returns a list of icon resource IDs.
     */
    private fun getIconsList(): MutableList<Int> {
        var list = mutableListOf<Int>()

        list.add(R.drawable.ic_bike_24)
        list.add(R.drawable.ic_cake_24)
        list.add(R.drawable.ic_car_24)
        list.add(R.drawable.ic_football_24)
        list.add(R.drawable.ic_gamepad_24)
        list.add(R.drawable.ic_lightbulb_24)
        list.add(R.drawable.ic_moon_24)
        list.add(R.drawable.ic_sun_24)
        list.shuffle()

        return list
    }

    /**
     * Randomly fills ImageViews inside cards with a list of icons.
     */
    private fun fillCards(icons: MutableList<Int>) {
        var imageViews = mutableListOf<ImageView>()

        imageViews.add(binding.imgIcon01)
        imageViews.add(binding.imgIcon02)
        imageViews.add(binding.imgIcon03)
        imageViews.add(binding.imgIcon04)
        imageViews.add(binding.imgIcon05)
        imageViews.add(binding.imgIcon06)
        imageViews.add(binding.imgIcon07)
        imageViews.add(binding.imgIcon08)
        imageViews.add(binding.imgIcon09)
        imageViews.add(binding.imgIcon10)
        imageViews.add(binding.imgIcon11)
        imageViews.add(binding.imgIcon12)
        imageViews.add(binding.imgIcon13)
        imageViews.add(binding.imgIcon14)
        imageViews.add(binding.imgIcon15)
        imageViews.add(binding.imgIcon16)

        imageViews.shuffle()
        icons.shuffle()

        for(i in 0..15) {
            imageViews[i].setImageResource(icons[i.floorDiv(2)])
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