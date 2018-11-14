package com.allow.sdk.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allow.sdk.R
import kotlinx.android.synthetic.main.start_order_fragment.*

class StartOrderFragment : Fragment() {

    companion object {
        fun newInstance() = StartOrderFragment()
    }
    interface MainFragmentInteractionListener {
        fun startOrder()
    }

    private var mInteractionListener: MainFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.start_order_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start_order_button.setOnClickListener { mInteractionListener?.startOrder() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mInteractionListener = if (context is MainFragmentInteractionListener) context
        else throw RuntimeException("${context.toString()} must implement MainFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        mInteractionListener = null
    }

}
