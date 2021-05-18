package com.eltiempo.dir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void intentToDetalle(View view){
        TextView tViewCiudad = findViewById(R.id.edittxt_ciudad);
        String txtciudad = tViewCiudad.getText().toString();
        if (!txtciudad.isEmpty()){
            Intent miIntent = new Intent(getApplicationContext(),DetalleTiempo.class);
            miIntent.putExtra("ciudad",txtciudad);
            startActivity(miIntent);
        }else{
            Toast.makeText(this, "¡Introduce una ciudad válida!", Toast.LENGTH_SHORT).show();
        }

    }
}