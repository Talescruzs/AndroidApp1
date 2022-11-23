package com.example.remedioidoso;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Button btAdd;
    private Button btConfere;
    private EditText campoRemedio;
    private TimePicker hora;
    private String remedios;
    private String novoRemedio;
    private String horarios;
    private String novoHorario;
    private SharedPreferences preferences;
//    private SharedPreferences preferencesTeste;

//    public static final String PREF = "com.example.grafico.PREF";


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        preferences = getSharedPreferences("userPreferences", 0);
//        preferencesTeste = getSharedPreferences("userPreferencesTeste", 0);

//        SharedPreferences.Editor editorTeste = preferencesTeste.edit();
//        editorTeste.put

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoRemedio = findViewById(R.id.NomeRemedio);
        hora = findViewById(R.id.Relogio);

        btAdd = findViewById(R.id.BtAdd);
        btConfere = findViewById(R.id.BtConfere);
        btAdd.setOnClickListener(this::salva);
        btConfere.setOnClickListener(this::lista);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void salva(View view)
    {
        remedios = preferences.getString("remedios", null);
        if(remedios == null) {
            novoRemedio = campoRemedio.getText().toString();
        }else{
            novoRemedio = remedios + "," + campoRemedio.getText().toString();
        }

        horarios = preferences.getString("horarios", null);
        int h = hora.getHour();
        int m = hora.getMinute();
        String hs;
        String ms;
        if (h >= 10) {
            hs = String.valueOf(h);
        }else{
            hs = "0"+String.valueOf(h);
        }
        if (m >= 10) {
            ms = String.valueOf(m);
        }else{
            ms = "0"+String.valueOf(m);
        }
        if(horarios == null) {
            novoHorario = hs+":"+ms;
        }
        else{
            novoHorario = horarios + "," + hs+":"+ms;
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remedios", novoRemedio);
        editor.putString("horarios", novoHorario);

        editor.apply();

        Toast.makeText(MainActivity.this, campoRemedio.getText().toString()+" inserido", Toast.LENGTH_LONG).show();
    }

    public void lista(View view) {
        Intent insertEqua = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(insertEqua);
    }
}