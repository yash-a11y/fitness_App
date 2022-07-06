package com.sevenminuteworkout

import android.app.Dialog
import android.app.IntentService
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sevenminuteworkout.databinding.ActivityExerciseBinding
import com.sevenminuteworkout.databinding.BachConformationDialogBinding
import kotlinx.android.synthetic.main.activity_exercise.*
import java.lang.Exception
import java.util.*


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    //
        private var adapter : exerciseAdp  ?= null

    //
    private var binding : ActivityExerciseBinding ?= null
    private var progressbar : ProgressBar ?= null
    private var tvtimer : TextView ?= null
    private var Timer : CountDownTimer ?= null
    private var actff : FrameLayout ?=null

    private var EXtimer : CountDownTimer ?= null
    private var exercise_Timer : Long = 30000
    private var tv_timer_ex : TextView ?= null
    private var Progress_timer : Long = 10000
    private var EXprogressbar : ProgressBar ?= null
    private var exff : FrameLayout ?= null



    //exercise arraylist

    private var exercise_list : ArrayList<exercise_model> ?= null
    private var currpos : Int = 0
    private var totol_len : Int ?= null

    //text to  speech
    private var tts : TextToSpeech ?= null
    //media player
    private var mediaplayer : MediaPlayer ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        //Tvtimer

        tvtimer = binding?.tvTimer
        tv_timer_ex = binding?.tvTimerExe
        //initiallize list of exercise :

        exercise_list = constant.defaultExerciseLists()
        totol_len = exercise_list!!.size


        // recyclerview initiallization :



            //


        //TODO(Step 5 - Setting up the action bar using the toolbar and adding a back arrow button to it.)-->
        //START
        setSupportActionBar(binding?.toolbarExerciseActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Us the backpressed (close the activity) functionality when clicking the toolbar back button
        toolbar_exercise_activity.setNavigationOnClickListener {

            onbackpresses()
        }

        //END


        //Frame_LayOuts :
        actff = binding?.activityFf
        exff = binding?.exerciseFf



        // start progressbar
        progressbar = binding?.tvProgressBar
        EXprogressbar = binding?.tvProgressBarExe

        // setup of txt to speech intialization

        tts = TextToSpeech(this,this)

        setuprestview()

        recyclerview()



    }

    // progressbar functions :

    private fun setuprestview()
    {

//        recyclerview()
        try {

            val sounduri = Uri.parse("android.resource://com.sevenminuteworkout/" + R.raw.back_music)

            mediaplayer = MediaPlayer.create(applicationContext,sounduri)
            mediaplayer?.isLooping = false
            mediaplayer?.start()
        }
        catch (e : Exception)
        {
            e.printStackTrace()
        }


        if(Timer != null)
        {
            Timer?.cancel()
            progressbar!!.progress = 0
        }

        actff?.visibility = View.VISIBLE
        binding?.upcoming?.visibility = View.VISIBLE
        binding?.labelupcoming?.visibility = View.VISIBLE
        binding?.TVCurr?.visibility = View.VISIBLE
        binding?.upcoming?.text = "${exercise_list!!.get(currpos).getName().toString()}"
        speekout("upcoming exercise is ${exercise_list!!.get(currpos).getName().toString()}")


        startprogress()
    }

    private  fun startprogress()
    {

        tvtimer!!.text = "${(Progress_timer/1000).toString()}"
         Timer = object : CountDownTimer(Progress_timer,1000) {
            override fun onTick(untillfinish_time: Long) {

                tvtimer!!.text = (untillfinish_time/1000).toString()
                progressbar!!.progress = (untillfinish_time/1000).toInt()
            }

            override fun onFinish() {

                 binding?.TVCurr?.visibility = View.GONE
                 actff!!.visibility = View.INVISIBLE  //activity framelayout
                 binding?.upcoming?.visibility = View.GONE
                binding?.labelupcoming?.visibility = View.GONE
                //exercise framelayout


                setUpexercise()

            }

        }.start()
    }

    //recyclerview

    private fun recyclerview()
    {
        binding?.rcyl?.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adapter = exerciseAdp(exercise_list!!)
        binding?.rcyl?.adapter = adapter

    }

            //progress for exercise ativity

    private fun setUpexercise()
    {
        if(EXtimer != null)
        {
            EXtimer?.cancel()
            EXprogressbar!!.progress = 0
        }


        start_EX_progress()
    }

    private fun start_EX_progress()
    {
        //
        //view setup
            exff!!.visibility = View.VISIBLE
            binding?.imgviewEx?.visibility = View.VISIBLE
            binding?.tvExName?.visibility = View.VISIBLE
        //
        //set image of exercise

        var currobj : exercise_model = exercise_list!!.get(currpos)

        currobj.setIsCompleted(false)
        currobj.setIsselected(true)
        adapter?.notifyDataSetChanged()

        binding?.imgviewEx?.setImageResource(currobj.getImage())

        binding?.tvExName?.text = currobj.getName().toString()


        tv_timer_ex!!.text = (exercise_Timer/1000).toString()
        EXtimer = object :CountDownTimer(exercise_Timer,1000)
        {

            public override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"done",Toast.LENGTH_SHORT).show()

                //active next activity timer
                exff?.visibility = View.GONE
                binding?.imgviewEx?.visibility = View.GONE
                binding?.tvExName?.visibility = View.GONE

                //notify iscompleted is true

                currobj.setIsselected(false)
                currobj.setIsCompleted(true)
                adapter?.notifyDataSetChanged()

                //
                TV_curr.text = "READY FOR NEXT EXERCISE"
                if(currpos+1 < totol_len!!)
                {
                    currpos++
                    setuprestview()
                }
                else
                {
                            finish()
                            val intent : Intent =  Intent(this@ExerciseActivity,finish::class.java)
                            startActivity(intent)
                }


            }


            public override fun onTick(untilltime: Long) {


                tv_timer_ex!!.text = (untilltime/1000).toString()
                EXprogressbar!!.progress = (untilltime/1000).toInt()

            }
        }.start()
    }


    //
    override fun onDestroy() {
        super.onDestroy()

        binding = null

        if(mediaplayer != null)
        {
            mediaplayer?.stop()
        }
        if(tts != null)
        {
            tts?.stop()
            tts?.shutdown()

        }

    }


    private fun speekout(speak : String)
    {
        tts?.speak(speak,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS)
        {
           val result =  tts?.setLanguage(Locale.US)

            if(result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("txt","Language not supported")
            }
        }
        else
        {
            Log.e("txt","texttospeech not initiallzed")
        }
    }


    private fun onbackpresses()
    {
        val customdialog = Dialog(this)

        val dialogbinding : BachConformationDialogBinding = BachConformationDialogBinding.inflate(layoutInflater)

        customdialog.setContentView(dialogbinding?.root)

        customdialog.setCanceledOnTouchOutside(true)

        dialogbinding?.btnyes.setOnClickListener {

            this@ExerciseActivity.finish()
            customdialog.dismiss()
        }

        dialogbinding?.btnno.setOnClickListener {
            customdialog.dismiss()
        }

        customdialog.show()

    }

    //onback press override

    override fun onBackPressed() {

        onbackpresses()
    }


}