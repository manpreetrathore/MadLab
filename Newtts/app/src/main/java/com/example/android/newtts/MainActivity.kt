package com.example.android.newtts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.EditText
import java.util.*
import android.widget.Button

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var textToSpeech: TextToSpeech?=null
    private lateinit var textToSpeechButton: Button
    private lateinit var textInput: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textToSpeechButton=findViewById(R.id.textToSpeechButton)
        textInput=findViewById(R.id.textInput)
        textToSpeechButton!!.isEnabled=false
        textToSpeech= TextToSpeech(this,this)
        textToSpeechButton!!.setOnClickListener { convertToSpeech() }
    }
    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result=textToSpeech!!.setLanguage(Locale.ENGLISH)
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language Specified Not Supported")
            }
            else{
                textToSpeechButton!!.isEnabled=true
            }
        }
        else{
            Log.e("TTS","Initialization Failed")
        }
    }

    private fun convertToSpeech() {
        val text=textInput!!.text.toString()
        textToSpeech!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    public override fun onDestroy(){
        if (textToSpeech != null){
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onDestroy()
    }
}