package com.example.remedioidoso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout linearLayout;
    SharedPreferences preferences;
    String[] remediosL;
    String[] horariosL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linearLayout = findViewById(R.id.item);

        preferences = getSharedPreferences("userPreferences", 0);


        remediosL = preferences.getString("remedios", null).split(",");
        horariosL = preferences.getString("horarios", null).split(",");

        linearLayout = findViewById(R.id.item);
        if(remediosL[0]!=""){
            for(int i=0; i<remediosL.length; i++){
                View view = getLayoutInflater().inflate(R.layout.row_add, null, false);
                TextView remedio = (TextView)view.findViewById(R.id.remedio);
                TextView hora = (TextView)view.findViewById(R.id.hora);
                Button button = (Button)view.findViewById(R.id.remove);

                remedio.setText(remediosL[i]);
                hora.setText(horariosL[i]);
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        remove(view);
                    }
                });
                linearLayout.addView(view);
            }
        }
    }
    private void remove(View view){
        TextView remedio = (TextView)view.findViewById(R.id.remedio);
        TextView hora = (TextView)view.findViewById(R.id.hora);

        String newRemedios = "";
        String newHorarios = "";
        for(int i=0; i<remediosL.length; i++){
            if (
                remedio.getText().toString() != remediosL[i] ||
                hora.getText().toString() != horariosL[i]
            ){
                if(newHorarios.equals("")){
                    newRemedios = remediosL[i];
                    newHorarios = horariosL[i];
                }else{
                    newRemedios = newRemedios+","+remediosL[i];
                    newHorarios = newHorarios+","+horariosL[i];
                }
            }
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remedios", newRemedios);
        editor.putString("horarios", newHorarios);
        editor.apply();

        linearLayout.removeView(view);

        Toast.makeText(MainActivity2.this, remedio.getText()+" removido", Toast.LENGTH_LONG).show();
    }
}