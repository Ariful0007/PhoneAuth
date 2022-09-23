package com.meass.professionalworks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class MainActivity3 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private Button buttonSpeak;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.button1);
        editText = (EditText) findViewById(R.id.editText1);
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });
    }
    private void speakOut() {
        String text=editText.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
        tts.setSpeechRate(1.2f);
    }
    @Override
    public void onInit(int status) {
        if(status==TextToSpeech.SUCCESS) {
            int language=tts.setLanguage(Locale.US);
            if (language==TextToSpeech.LANG_MISSING_DATA || language==TextToSpeech.LANG_NOT_SUPPORTED) {
                Toasty.error(getApplicationContext(),"Missing",Toasty.LENGTH_SHORT,true).show();
                return;
            }
            else {
                buttonSpeak.setEnabled(true);
                speakOut();
            }
        }

    }


}