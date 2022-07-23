package com.sevenminuteworkout


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
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

        val dao = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)
    }

    private fun addDateToDatabase(historyDao: HistoryDao) {

        val c = Calendar.getInstance()
        val dateTime = c.time


        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)


        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))

        }
    }

    override fun onDestroy() {


        super.onDestroy()
        bind = null
    }
}