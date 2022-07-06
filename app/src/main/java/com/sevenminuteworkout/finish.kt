package com.sevenminuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sevenminuteworkout.databinding.ActivityExerciseBinding
import com.sevenminuteworkout.databinding.ActivityFinishBinding

class finish : AppCompatActivity() {

    private var bind : ActivityFinishBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(bind?.root)

        setSupportActionBar(bind?.actionbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bind?.actionbar?.setNavigationOnClickListener {

            onBackPressed()
        }

        bind?.onfinish?.setOnClickListener{

           finish()
        }
    }

    override fun onDestroy() {


        super.onDestroy()
        bind = null
    }
}