package com.eltiempo.dir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eltiempo.dir.modelo.Main;
import com.eltiempo.dir.modelo.Tiempo;
import com.eltiempo.dir.retrofit.Interfacetiempo;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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