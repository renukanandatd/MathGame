package com.example.mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
    var userLife=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        textViewScore=findViewById(R.id.textViewScore)
        textViewLife=findViewById(R.id.textViewLife)
        textViewTime=findViewById(R.id.textViewTime)
        editTextQuestion=findViewById(R.id.editTextQuestion)
        editTextAnswer=findViewById(R.id.editTextAnswer)
        buttonOk=findViewById(R.id.buttonOK)
        buttonNext=findViewById(R.id.buttonNext)

        buttonOk.setOnClickListener {

            val input= editTextAnswer.text.toString()
            if(input==""){
                Toast.makeText(this,"Please enter the answer",Toast.LENGTH_SHORT).show()
            }
            else{
                val userAnswer=input.toInt()
            }
        }
        buttonNext.setOnClickListener {

        }
    }
    fun gameContinue(){
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)

        editTextQuestion.text="$number1 + $number2"
    }
}