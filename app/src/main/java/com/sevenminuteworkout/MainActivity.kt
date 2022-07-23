package com.sevenminuteworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sevenminuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    private var bindding : ActivityMainBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor


        super.onCreate(savedInstanceState)
        bindding = ActivityMainBinding.inflate(layoutInflater) //alternative way for bind the xml with the relevant activity reduce the memory leak.

        setContentView(bindding?.root)


        bindding?.flyout?.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)

        }



        bindding?.bmily?.setOnClickListener{
            val intent = Intent(this,bmiActivity::class.java)
            startActivity(intent)
        }

        bindding?.historyly?.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)


        }
    }

    override fun onDestroy() {
        super.onDestroy()

        bindding = null
    }
}