package com.sevenminuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sevenminuteworkout.databinding.ActivityExerciseBinding
import com.sevenminuteworkout.databinding.BachConformationDialogBinding
import java.util.*

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var adapter: exerciseAdp? = null
    private var binding: ActivityExerciseBinding? = null
    private var progressbar: ProgressBar? = null
    private var tvtimer: TextView? = null
    private var Timer: CountDownTimer? = null
    private var actff: FrameLayout? = null
    private var EXtimer: CountDownTimer? = null
    private var exercise_Timer: Long = 30000
    private var tv_timer_ex: TextView? = null
    private var Progress_timer: Long = 10000
    private var EXprogressbar: ProgressBar? = null
    private var exff: FrameLayout? = null
    private var exercise_list: ArrayList<exercise_model>? = null
    private var currpos: Int = 0
    private var totol_len: Int? = null
    private var tts: TextToSpeech? = null
    private var mediaplayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tvtimer = binding?.tvTimer
        tv_timer_ex = binding?.tvTimerExe
        exercise_list = constant.defaultExerciseLists()
        totol_len = exercise_list!!.size

        setSupportActionBar(binding?.toolbarExerciseActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarExerciseActivity?.setNavigationOnClickListener {
            onbackpresses()
        }

        actff = binding?.activityFf
        exff = binding?.exerciseFf
        progressbar = binding?.tvProgressBar
        EXprogressbar = binding?.tvProgressBarExe

        tts = TextToSpeech(this, this)
        setuprestview()
        recyclerview()
    }

    private fun setuprestview() {
        try {
            val sounduri = Uri.parse("android.resource://com.sevenminuteworkout/" + R.raw.back_music)
            mediaplayer = MediaPlayer.create(applicationContext, sounduri)
            mediaplayer?.isLooping = false
            mediaplayer?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Timer?.cancel()
        progressbar?.progress = 0

        actff?.visibility = View.VISIBLE
        binding?.upcoming?.visibility = View.VISIBLE
        binding?.labelupcoming?.visibility = View.VISIBLE
        binding?.TVCurr?.visibility = View.VISIBLE
        binding?.upcoming?.text = exercise_list!![currpos].getName().toString()
        speekout("Upcoming exercise is ${exercise_list!![currpos].getName().toString()}")

        startprogress()
    }

    private fun startprogress() {
        tvtimer?.text = (Progress_timer / 1000).toString()
        Timer = object : CountDownTimer(Progress_timer, 1000) {
            override fun onTick(untillfinish_time: Long) {
                tvtimer?.text = (untillfinish_time / 1000).toString()
                progressbar?.progress = (untillfinish_time / 1000).toInt()
            }

            override fun onFinish() {
                binding?.TVCurr?.visibility = View.GONE
                actff?.visibility = View.INVISIBLE
                binding?.upcoming?.visibility = View.GONE
                binding?.labelupcoming?.visibility = View.GONE
                setUpexercise()
            }
        }.start()
    }

    private fun recyclerview() {
        binding?.rcyl?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = exerciseAdp(exercise_list!!)
        binding?.rcyl?.adapter = adapter
    }

    private fun setUpexercise() {
        EXtimer?.cancel()
        EXprogressbar?.progress = 0
        start_EX_progress()
    }

    private fun start_EX_progress() {
        exff?.visibility = View.VISIBLE
        binding?.imgviewEx?.visibility = View.VISIBLE
        binding?.tvExName?.visibility = View.VISIBLE

        val currobj = exercise_list!![currpos]
        currobj.setIsCompleted(false)
        currobj.setIsselected(true)
        adapter?.notifyDataSetChanged()

        binding?.imgviewEx?.setImageResource(currobj.getImage())
        binding?.tvExName?.text = currobj.getName().toString()

        tv_timer_ex?.text = (exercise_Timer / 1000).toString()
        EXtimer = object : CountDownTimer(exercise_Timer, 1000) {
            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "Done", Toast.LENGTH_SHORT).show()
                exff?.visibility = View.GONE
                binding?.imgviewEx?.visibility = View.GONE
                binding?.tvExName?.visibility = View.GONE

                currobj.setIsselected(false)
                currobj.setIsCompleted(true)
                adapter?.notifyDataSetChanged()

                binding?.TVCurr?.text = "READY FOR NEXT EXERCISE"
                if (currpos + 1 < totol_len!!) {
                    currpos++
                    setuprestview()
                } else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, finish::class.java)
                    startActivity(intent)
                }
            }

            override fun onTick(untilltime: Long) {
                tv_timer_ex?.text = (untilltime / 1000).toString()
                EXprogressbar?.progress = (untilltime / 1000).toInt()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        mediaplayer?.stop()
        tts?.stop()
        tts?.shutdown()
    }

    private fun speekout(speak: String) {
        tts?.speak(speak, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("txt", "Language not supported")
            }
        } else {
            Log.e("txt", "TextToSpeech not initialized")
        }
    }

    private fun onbackpresses() {
        val customdialog = Dialog(this)
        val dialogbinding = BachConformationDialogBinding.inflate(layoutInflater)
        customdialog.setContentView(dialogbinding.root)
        customdialog.setCanceledOnTouchOutside(true)

        dialogbinding.btnyes.setOnClickListener {
            this@ExerciseActivity.finish()
            customdialog.dismiss()
        }

        dialogbinding.btnno.setOnClickListener {
            customdialog.dismiss()
        }

        customdialog.show()
    }

    override fun onBackPressed() {
        onbackpresses()
        onDestroy()
    }
}
