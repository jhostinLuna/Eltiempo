package com.eltiempo.dir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleTiempo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tiempo);
        TextView txtV_temperatura = findViewById(R.id.txt_temperatura_ldt);
        TextView txtV_ciudad = findViewById(R.id.txt_ciudad_ldt);

        String nombreCiudad = "Temperatura en ";
                nombreCiudad += getIntent().getSerializableExtra("ciudad").toString()+":";
        txtV_ciudad.setText(nombreCiudad);
        int temperatura = getNumAleatorio();
        txtV_temperatura.setText(String.valueOf(temperatura)+"ºC");
        ImageView iconoTiempo = findViewById(R.id.imgV_tiempo);


        iconoTiempo.setImageResource(getIdIcono(temperatura));


    }

    public int getNumAleatorio(){
        //Random r = new Random();
        //int aux = r.nextInt(3);
        int temperatura = (int)Math.floor(Math.random()*(35-(-5)+1)+(-5));
        return temperatura;

    }
    public int getIdIcono(int temperatura){
        int idIcono;
        if (temperatura < 5){
            idIcono = R.drawable.frio;
        }
        else if (temperatura < 15){
            idIcono = R.drawable.lluvia;
        }else {
            idIcono = R.drawable.dom;
        }
        return idIcono;
    }
}