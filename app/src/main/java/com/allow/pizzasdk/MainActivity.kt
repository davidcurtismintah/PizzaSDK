package com.allow.pizzasdk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.allow.sdk.PizzaSdkActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(PizzaSdkActivity.getStartIntent(this))
        }
    }

}
