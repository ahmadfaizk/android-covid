package com.faiz.covid19.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.faiz.covid19.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardStatistic.setOnClickListener(this)

        Glide.with(this)
            .load(R.drawable.bg_corona)
            .into(imageView)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.cardStatistic -> startActivity(Intent(this, ChartActivity::class.java))
        }
    }
}
