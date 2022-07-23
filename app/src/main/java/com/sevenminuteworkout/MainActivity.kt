package com.sevenminuteworkout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sevenminuteworkout.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     * This method is auto created by Android when the Activity Class is created.
     */

    private var bindding : ActivityMainBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor


        super.onCreate(savedInstanceState)
        bindding = ActivityMainBinding.inflate(layoutInflater) //alternative way for bind the xml with the relevant activity reduce the memory leak.
        // This is used to align the xml view to this class
        setContentView(bindding?.root)

        // Click event for start Button which we have created in XML.
        bindding?.flyout?.setOnClickListener {

            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
            //END
        }

        // click event for the BMI calculator

        bindding?.bmily?.setOnClickListener{
            val intent = Intent(this,bmiActivity::class.java)
            startActivity(intent)
        }

        bindding?.historyly?.setOnClickListener{
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }
        //
    }

    override fun onDestroy() {
        super.onDestroy()

        bindding = null
    }
}