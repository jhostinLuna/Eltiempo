package com.eltiempo.dir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText editxtCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editxtCiudad = findViewById(R.id.edittxt_ciudad);


    }
    public void intentToDetalle(View view){
        String txtciudad = editxtCiudad.getText().toString();
        if (!txtciudad.isEmpty()){

            Intent miIntent = new Intent(this,DetalleTiempo.class);
            miIntent.putExtra("ciudad",txtciudad);
            startActivity(miIntent);
        }else{
            Toast.makeText(this, "¡Introduce una ciudadela válida!", Toast.LENGTH_SHORT).show();
        }

    }

}