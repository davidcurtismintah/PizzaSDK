package com.allow.sdk.ui.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allow.sdk.R
import kotlinx.android.synthetic.main.second_flavor_selection_fragment.*

class SecondFlavorSelectionFragment : Fragment(),
    ItemPickerDialog.ItemPickerDialogInteractionListener {

    companion object {
        const val ARG_FIRST_FLAVOR = "ARG_FIRST_FLAVOR"

        fun newInstance(firstFlavor: Int): SecondFlavorSelectionFragment {
            return SecondFlavorSelectionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_FIRST_FLAVOR, firstFlavor)
                }
            }
        }
    }

    interface AddFlavorFragmentInteractionListener {
        fun order(firstFlavor: Int, secondFlavor: Int)
    }

    private var mInteractionListener: AddFlavorFragmentInteractionListener? = null

    private var firstFlavor = -1
    private var secondFlavor = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_flavor_selection_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstFlavor = arguments?.getInt(ARG_FIRST_FLAVOR, -1) ?: -1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            secondFlavor = savedInstanceState.getInt(ItemPickerDialog.ARG_SELECTED_INDEX, -1)
        }

        rl_pick_flavor.setOnClickListener {
            val pickerItems = MainViewModel.FLAVORS.mapIndexed { index, flavor ->
                ItemPickerDialog.Item(flavor, index)
            }

            val dialog = ItemPickerDialog.newInstance(
                activity!!.getString(R.string.select_flavor_dialog_title),
                pickerItems,
                secondFlavor
            )
            dialog.show(childFragmentManager, "ItemPicker")
        }

        order_button.setOnClickListener {
            mInteractionListener?.order(firstFlavor, secondFlavor)
        }

        first_flavor.text = MainViewModel.FLAVORS[firstFlavor]
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mInteractionListener = if (context is AddFlavorFragmentInteractionListener) context
        else throw RuntimeException("${context.toString()} must implement AddFlavorFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        mInteractionListener = null
    }

    override fun onItemSelected(fragment: ItemPickerDialog, item: ItemPickerDialog.Item, index: Int) {
        secondFlavor = index

        selected_flavor.text = item.title
        selected_flavor.visibility = View.VISIBLE
        empty_flavor.text = ""
        empty_flavor.visibility = View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ItemPickerDialog.ARG_SELECTED_INDEX, secondFlavor)
    }
}

