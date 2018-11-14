package com.allow.sdk.ui.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allow.sdk.R
import kotlinx.android.synthetic.main.first_flavor_selection_fragment.*

class FirstFlavorSelectionFragment : Fragment(),
    ItemPickerDialog.ItemPickerDialogInteractionListener {

    companion object {
        fun newInstance() = FirstFlavorSelectionFragment()
    }

    interface FlavorSelectionFragmentInteractionListener {
        fun addFlavor(firstFlavor: Int)
        fun order(flavor: Int)
    }

    private var mInteractionListener: FlavorSelectionFragmentInteractionListener? = null

    private var selectedItem = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.first_flavor_selection_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            selectedItem = savedInstanceState.getInt(ItemPickerDialog.ARG_SELECTED_INDEX, -1)
        }

        rl_pick_flavor.setOnClickListener {
            val pickerItems = MainViewModel.FLAVORS.mapIndexed { index, flavor ->
                ItemPickerDialog.Item(flavor, index)
            }

            val dialog = ItemPickerDialog.newInstance(
                    activity!!.getString(R.string.select_flavor_dialog_title),
                    pickerItems,
                    selectedItem
            )
            dialog.show(childFragmentManager, "ItemPicker")
        }

        add_flavor_button.setOnClickListener {
            mInteractionListener?.addFlavor(selectedItem)
        }

        order_button.setOnClickListener {
            mInteractionListener?.order(selectedItem)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mInteractionListener = if (context is FlavorSelectionFragmentInteractionListener) context
        else throw RuntimeException("${context.toString()} must implement FlavorSelectionFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        mInteractionListener = null
    }

    override fun onItemSelected(fragment: ItemPickerDialog, item: ItemPickerDialog.Item, index: Int) {
        selectedItem = index

        selected_flavor.text = item.title
        selected_flavor.visibility = View.VISIBLE
        empty_flavor.text = ""
        empty_flavor.visibility = View.GONE

        add_flavor_button.isEnabled = true
        order_button.isEnabled = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ItemPickerDialog.ARG_SELECTED_INDEX, selectedItem)
    }
}

