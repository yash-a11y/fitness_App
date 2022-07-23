package com.sevenminuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sevenminuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class bmiActivity : AppCompatActivity() {

    private var bind : ActivityBmiBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityBmiBinding.inflate(layoutInflater)

        setContentView(bind?.root)


        // setup actionbar
        setSupportActionBar(bind?.bmitoolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bind?.bmitoolbar?.setNavigationOnClickListener {
            onBackPressed()
        }


        //
        // set onclick event for btn



        //



        bind?.radiogrp?.setOnCheckedChangeListener { radioGroup, i ->

            when(i)
            {
                R.id.metriv_unit ->
                {
                   // setup metric unit layout
                    bind?.metricUnitLay?.visibility = View.VISIBLE
                    bind?.unitUsLay?.visibility = View.INVISIBLE
                    bind?.bmibtn?.setOnClickListener {
                        metric_unit()
                    }

                }

                R.id.us_unit ->
                {
                    bind?.metricUnitLay?.visibility = View.INVISIBLE
                    bind?.unitUsLay?.visibility = View.VISIBLE
                    bind?.bmibtn?.setOnClickListener {
                        US_unit()
                    }
                }

            }
        }

    }

    private fun US_unit() {

        if(validateinput_unit())
        {

            val W  = bind?.tvWeightPound?.text.toString().toFloat()
            val H = (bind?.tvFeet?.text.toString().toFloat() * 12) + bind?.tvHeightIn?.text.toString().toFloat()

            val bmi :Float = 730 * (W / (H*H))

            Display_BMI(bmi)

        }
        else
        {
            Toast.makeText(this,"ENTER VALID INPUT",Toast.LENGTH_SHORT).show()
        }

    }

    /// metric unit function setup
    private fun metric_unit() {

    if(validateinput())
    {

        val W  = bind?.tvWeight?.text.toString().toFloat()
        val H = bind?.tvHeight?.text.toString().toFloat() / 100

        val bmi :Float = (W / (H*H))

        Display_BMI(bmi)

    }
    else
    {
        Toast.makeText(this,"ENTER VALID INPUT",Toast.LENGTH_SHORT).show()
    }


    }



    //
    //validate input types are right or wrong
    private fun validateinput() : Boolean
    {
        var check = true

        if(bind?.tvHeight?.text.toString().isEmpty())
        {
            check =false
        }
        if(bind?.tvWeight?.text.toString().isEmpty())
        {
            check =false
        }



        return check
    }

    private fun validateinput_unit() : Boolean
    {
        var check = true

        if(bind?.tvWeightPound?.text.toString().isEmpty())
        {
            check =false
        }
        if(bind?.tvFeet?.text.toString().isEmpty())
        {
            check =false
        }
        if(bind?.tvHeightIn?.text.toString().isEmpty())
        {
            check =false
        }


        return check
    }

    // display BMI result and logical operation part

    private  fun Display_BMI(bmi : Float)
    {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        //Use to set the result layout visible
        bind?.txtl?.visibility = View.VISIBLE

        // This is used to round the result value to 2 decimal values after "." )

        val bmivalue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        bind?.tvBmi?.text = bmivalue // Value is set to TextView
        bind?.tvBmiTxt?.text = bmiLabel // Label is set to TextView
        bind?.tvBmiMsg?.text = bmiDescription // Description is set to TextView
    }

    override fun onDestroy() {
        super.onDestroy()
        bind = null
    }
    }

