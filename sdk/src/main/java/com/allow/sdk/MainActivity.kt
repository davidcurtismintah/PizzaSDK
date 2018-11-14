package com.allow.sdk

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.allow.sdk.ui.main.*

class MainActivity : AppCompatActivity(), StartOrderFragment.MainFragmentInteractionListener,
    FirstFlavorSelectionFragment.FlavorSelectionFragmentInteractionListener,
    SecondFlavorSelectionFragment.AddFlavorFragmentInteractionListener,
    ViewOrderFragment.ViewOrderFragmentInteractionListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StartOrderFragment.newInstance())
                .commitNow()
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.orderLiveData.observe(this, Observer {_ ->
            replaceFragment(R.id.container, ViewOrderFragment.newInstance())
        })
    }

    override fun startOrder() {
        replaceFragment(R.id.container, FirstFlavorSelectionFragment.newInstance())
    }

    override fun addFlavor(firstFlavor: Int) {
        replaceFragment(R.id.container, SecondFlavorSelectionFragment.newInstance(firstFlavor))
    }

    override fun order(firstFlavor: Int, secondFlavor: Int) {
        viewModel.order(firstFlavor, secondFlavor)
    }

    override fun order(flavor: Int) {
        viewModel.order(flavor)
    }

    companion object {
        /**
         * Creates an intent to be used with [startActivity][android.app.Activity.startActivity]
         * */
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
