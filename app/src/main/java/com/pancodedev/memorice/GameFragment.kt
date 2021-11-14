package com.pancodedev.memorice

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pancodedev.memorice.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private var cardList = mutableListOf<Pair<CardView, ImageView>>()

    private var inGameText = MutableLiveData<String>()

    private var firstCard: CardView? = null
    private var firstIcon: ImageView? = null
    private var secondCard: CardView? = null
    private var secondIcon: ImageView? = null

    private var matchedPairs = MutableLiveData(0)
    private val totalPairs by lazy { cardList.size / 2 }

    private var turnsPlayed = MutableLiveData(0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(R.string.game_back_dialog_text)
                .setPositiveButton(R.string.game_back_dialog_positive_button_text) { _, _ ->
                    parentFragmentManager.popBackStack()
                }
                .setNegativeButton(R.string.game_back_dialog_negative_button_text) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
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

        fillCardList()
        fillCardsWithIcons(getIconsList())
        setupCardsOnClickListeners()
        refreshTurnCounter(turnsPlayed.value!!)
        refreshMatchCounter(matchedPairs.value!!)

        binding.btnContinue.setOnClickListener {
            resetBoard()
            enableCards()
            binding.btnContinue.visibility = Button.GONE
        }

        binding.btnNewGame.setOnClickListener {
            val fragmentManager = parentFragmentManager

            fragmentManager.popBackStack()

            fragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, GameFragment.newInstance(), GameFragment.TAG)
                .addToBackStack(null)
                .commit()

            binding.btnNewGame.visibility = Button.GONE
        }

        inGameText.observe(viewLifecycleOwner, {
            binding.tvInGameText.text = it
        })

        turnsPlayed.observe(viewLifecycleOwner, {
            refreshTurnCounter(it)
        })

        matchedPairs.observe(viewLifecycleOwner, {
            refreshMatchCounter(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    companion object {
        const val TAG = "FRAGMENT_GAME"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment GameFragment.
         */
        @JvmStatic
        fun newInstance() = GameFragment()

    }

    private fun refreshTurnCounter(turnsPlayed: Int) {
        binding.tvTurnCounter.text = String.format(getString(R.string.turn_counter_text), turnsPlayed)
    }

    private fun refreshMatchCounter(matchedPairs: Int) {
        binding.tvMatchCounter.text = String.format(getString(R.string.match_counter_text), matchedPairs, totalPairs)
    }

    private fun setupCardsOnClickListeners() {
        cardList.forEach { pair ->
            pair.first.setOnClickListener {
                turnoverCard(pair.first, pair.second)
                playGame(pair.first, pair.second)
            }
        }
    }

    /**
     * Fills [GameFragment.cardList] with CardView/ImageView Pairs.
     */
    private fun fillCardList() {
        cardList.add(Pair(binding.cardImage01, binding.imgIcon01))
        cardList.add(Pair(binding.cardImage02, binding.imgIcon02))
        cardList.add(Pair(binding.cardImage03, binding.imgIcon03))
        cardList.add(Pair(binding.cardImage04, binding.imgIcon04))
        cardList.add(Pair(binding.cardImage05, binding.imgIcon05))
        cardList.add(Pair(binding.cardImage06, binding.imgIcon06))
        cardList.add(Pair(binding.cardImage07, binding.imgIcon07))
        cardList.add(Pair(binding.cardImage08, binding.imgIcon08))
        cardList.add(Pair(binding.cardImage09, binding.imgIcon09))
        cardList.add(Pair(binding.cardImage10, binding.imgIcon10))
        cardList.add(Pair(binding.cardImage11, binding.imgIcon11))
        cardList.add(Pair(binding.cardImage12, binding.imgIcon12))
        cardList.add(Pair(binding.cardImage13, binding.imgIcon13))
        cardList.add(Pair(binding.cardImage14, binding.imgIcon14))
        cardList.add(Pair(binding.cardImage15, binding.imgIcon15))
        cardList.add(Pair(binding.cardImage16, binding.imgIcon16))
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
     * Randomly fills [GameFragment.cardList] with icons.
     */
    private fun fillCardsWithIcons(icons: MutableList<Int>) {
        icons.shuffle()
        cardList.shuffle()

        for(i in 0 until cardList.size) {
            val iconIndex = i.floorDiv(2)

            cardList[i].second.apply {
                setImageResource(icons[iconIndex])
                tag = iconIndex
            }
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

    /**
     * plays the next step in the game whenever called.
     */
    private fun playGame(card: CardView, icon: ImageView) {
        turnsPlayed.value = turnsPlayed.value!! + 1
        if (firstCard == null) {
            firstCard = card
            firstIcon = icon
            inGameText.value = getString(R.string.game_second_step_text)
        } else {
            secondCard = card
            secondIcon = icon

            if(firstIcon!!.tag == secondIcon!!.tag) {
                matchedPairs.value = matchedPairs.value!! + 1
                firstCard!!.tag = Constants.CARD_MATCHED
                secondCard!!.tag = Constants.CARD_MATCHED
                firstCard!!.isClickable = false
                secondCard!!.isClickable = false
                firstCard = null
                firstIcon = null
                secondCard = null
                secondIcon = null

                if(matchedPairs.value!! >= totalPairs) {
                    inGameText.value = getString(R.string.game_finished_text)
                    binding.btnNewGame.visibility = Button.VISIBLE
                } else
                    inGameText.value = getString(R.string.game_pair_matches_text)
            } else {
                inGameText.value = getString(R.string.game_pair_does_not_match_text)
                disableCards()
                binding.btnContinue.visibility = Button.VISIBLE
            }

        }
    }

    /**
     * resets variables when pair doesn't match.
     */
    private fun resetBoard() {
        turnoverCard(firstCard!!, firstIcon!!)
        turnoverCard(secondCard!!, secondIcon!!)
        firstCard = null
        firstIcon = null
        secondCard = null
        secondIcon = null
        binding.btnContinue.visibility = Button.GONE
        inGameText.value = getString(R.string.game_first_step_text)
    }

    /**
     * Disables cards from being clicked.
     */
    private fun disableCards() {
        cardList.forEach {
            it.first.isClickable = false
        }
    }

    /**
     * enables unmatched cards to be clicked.
     */
    private fun enableCards() {
        cardList.forEach {
            if(it.first.tag != Constants.CARD_MATCHED)
                it.first.isClickable = true
        }
    }
}