package com.example.mathgame

import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class Addition : AppCompatActivity() {

    lateinit var textViewScore:TextView
    lateinit var textViewLife:TextView
    lateinit var textViewTime:TextView
    lateinit var editTextQuestion:TextView
    lateinit var editTextAnswer: EditText
    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var correctAnswer=0
    var userScore=0
    var userLife=3

    lateinit var timer: CountDownTimer
    private val startTimerInMilliSeconds:Long = 20000
    var timeLeftinMilliSeconds:Long=startTimerInMilliSeconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        supportActionBar!!.title="Addition"

        textViewScore=findViewById(R.id.textViewScore)
        textViewLife=findViewById(R.id.textViewLife)
        textViewTime=findViewById(R.id.textViewTime)
        editTextQuestion=findViewById(R.id.editTextQuestion)
        editTextAnswer=findViewById(R.id.editTextAnswer)
        buttonOk=findViewById(R.id.buttonOK)
        buttonNext=findViewById(R.id.buttonNext)

        gameContinue()

        buttonOk.setOnClickListener {

            val input= editTextAnswer.text.toString()
            if(input==""){
                Toast.makeText(this,"Please enter the answer",Toast.LENGTH_SHORT).show()
            }
            else{
                pauseTimer()

                val userAnswer=input.toInt()
                if(userAnswer==correctAnswer){
                    userScore+=10
                    editTextQuestion.text="Congrats, your answer is correct!!!"
                    textViewScore.text=userScore.toString()
                }
                else{
                    userLife--
                    editTextQuestion.text="Sorry, your answer is wrong!!!"
                    textViewLife.text=userLife.toString()
                }
            }
        }
        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()

            gameContinue()
            editTextAnswer.setText("")
        }
    }
    fun gameContinue(){
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)

        editTextQuestion.text="$number1 + $number2"
        correctAnswer=number1+number2

        setTimer()
    }

    fun setTimer(){
        timer=object : CountDownTimer(startTimerInMilliSeconds,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftinMilliSeconds=millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                textViewLife.text=userLife.toString()
                editTextQuestion.text="Sorry, your time is up!!!"
            }

        }.start()
    }

    fun updateText(){
        val remainingTime:Int=(timeLeftinMilliSeconds/1000).toInt()
        textViewTime.text=String.format(Locale.getDefault(),"%02d", remainingTime)
    }
    fun resetTimer(){
        timeLeftinMilliSeconds=startTimerInMilliSeconds
        updateText()
    }
    fun pauseTimer(){
        timer.cancel()
    }
}