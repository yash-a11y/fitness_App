package com.sevenminuteworkout

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.sevenminuteworkout.databinding.ActivityExerciseBinding
import com.sevenminuteworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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

        val dao  = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)
    }

    private fun addDateToDatabase(historyDao: HistoryDao) {

        val c = Calendar.getInstance() // Calendars Current Instance
        val dateTime = c.time // Current Date and Time of the system.
        Log.e("Date : ", "" + dateTime) // Printed in the log.


        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault()) // Date Formatter
        val date = sdf.format(dateTime) // dateTime is formatted in the given format.
        Log.e("Formatted Date : ", "" + date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date)) // Add date function is called.
            Log.e(
                "Date : ",
                "Added..."
            )
        }
    }
    override fun onDestroy() {


        super.onDestroy()
        bind = null
    }
}