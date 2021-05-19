package com.eltiempo.dir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eltiempo.dir.modelo.Main;
import com.eltiempo.dir.modelo.Tiempo;
import com.eltiempo.dir.retrofit.Interfacetiempo;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleTiempo extends AppCompatActivity {
    private static final Double CERO_ABS = 273.15;
    private static final String BASE_URL = "https://api.openweathermap.org/";
    private static final String API_KEY = "4e719a84e03ef9f6ef6f38418f24e0bb";
    Tiempo tiempo;
    TextView txtV_temperatura;
    TextView txtV_ciudad;
    ImageView iconoTiempo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tiempo);
        txtV_temperatura = findViewById(R.id.txt_temperatura_ldt);
        txtV_ciudad = findViewById(R.id.txt_ciudad_ldt);

        String nombreCiudad = "Temperatura en ";
        nombreCiudad += getIntent().getSerializableExtra("ciudad").toString()+":";
        getTiempo();

        iconoTiempo = findViewById(R.id.imgV_tiempo);





    }
    //Converte grados Kelvin a Grados Centigrado o Celsius
    public Double KelvinToCelcius(Double kelvin) {
        return kelvin-CERO_ABS;
    }

    public int getNumAleatorio(){
        //Random r = new Random();
        //int aux = r.nextInt(3);
        int temperatura = (int)Math.floor(Math.random()*(35-(-5)+1)+(-5));
        return temperatura;

    }
    public int getIdIcono(Long temperatura){
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
    public void getTiempo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Interfacetiempo interfacetiempo = retrofit.create(Interfacetiempo.class);
        String ciudad = getIntent().getSerializableExtra("ciudad").toString();

        //LLAmo a la implementacion de la interface Interfacetiempo
        Call<Tiempo> call = interfacetiempo.getTiempo(ciudad,API_KEY);
        //Pongo en cola la peticion de call  "Asincrona"
        /*
        try {
            Response<Tiempo> response = call.execute();
            tiempo = response.body();
            Log.i("Retrofit","success");
        } catch (IOException e) {
            Log.i("retrofit","fouler");
        }
        */


        call.enqueue(new Callback<Tiempo>() {
            @Override
            public void onResponse(Call<Tiempo> call, Response<Tiempo> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(DetalleTiempo.this, "¡Introduce una ciudad válida!", Toast.LENGTH_SHORT).show();
                    return;
                }
                tiempo =response.body();
                Toast.makeText(DetalleTiempo.this, "Exito Response tiempo", Toast.LENGTH_SHORT).show();
                Main main = tiempo.getMain();

                //int temperatura = getNumAleatorio();

                Long temperatura =  Math.round(KelvinToCelcius(main.getTemp()));
                txtV_temperatura.setText(String.valueOf(temperatura)+"ºC");
                iconoTiempo.setImageResource(getIdIcono(temperatura));
            }

            @Override
            public void onFailure(Call<Tiempo> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(DetalleTiempo.this, "Fallo en la conexión", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(DetalleTiempo.this, "Problemas en la conversion :(", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}