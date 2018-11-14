package com.allow.sdk.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allow.sdk.R
import com.allow.sdk.domain.OrderFormat
import kotlinx.android.synthetic.main.view_order_fragment.*

class ViewOrderFragment : Fragment() {

    companion object {
        fun newInstance() = ViewOrderFragment()
    }

    interface ViewOrderFragmentInteractionListener {
//        fun startOrder()
    }

    private var mInteractionListener: ViewOrderFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.view_order_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        start_order_button.setOnClickListener { mInteractionListener?.startOrder() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        viewModel.orderLiveData.observe(this, Observer {
            it?.let { order ->
                val of = OrderFormat.getInstance(context!!, order)
                pizza.text = of.formatPizza()
                cost.text = of.formatCost()
            }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mInteractionListener = if (context is ViewOrderFragmentInteractionListener) context
        else throw RuntimeException("${context.toString()} must implement ViewOrderFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        mInteractionListener = null
    }

}
