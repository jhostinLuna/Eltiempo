package com.eltiempo.dir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.eltiempo.dir.modelo.ObjPrediccion;
import com.eltiempo.dir.recycleview.TiempoAdapter;
import com.eltiempo.dir.retrofit.Interfacetiempo;
import com.eltiempo.dir.retrofit.MyApiAdapter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleTiempo extends AppCompatActivity {
    private static final String API_KEY = "4e719a84e03ef9f6ef6f38418f24e0bb";
    TextView txtV_temperatura;
    TextView txtV_ciudad;
    TextView txt_description;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tiempo);
        txtV_temperatura = findViewById(R.id.txt_temperatura_ldt);
        txtV_ciudad = findViewById(R.id.txt_ciudad_ldt);
        recycler = findViewById(R.id.recyclerviewId);
        txt_description = findViewById(R.id.txt_description);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        String nombreCiudad;
        nombreCiudad = getIntent().getSerializableExtra("ciudad").toString();
        getTiempo();
        txtV_ciudad.setText(nombreCiudad);




    }


    public void getTiempo(){

        Interfacetiempo interfacetiempo = MyApiAdapter.getApiService();
        String ciudad = getIntent().getSerializableExtra("ciudad").toString();

        //LLAmo a la implementacion de la interface Interfacetiempo

        Call<ObjPrediccion> call = interfacetiempo.getListTiempo("madrid","es","metric",API_KEY);


        //Pongo en cola la peticion de call  "Asincrona"

        call.enqueue(new Callback<ObjPrediccion>() {
            @Override
            public void onResponse(Call<ObjPrediccion> call, Response<ObjPrediccion> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(DetalleTiempo.this, "¡Introduce una ciudad válida!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ObjPrediccion prediccion =response.body();
                txtV_temperatura.setText(Math.round(prediccion.getList().get(0).getMain().getTemp())+"º");
                txt_description.setText(prediccion.getList().get(0).getWeather().get(0).getDescription());
                TiempoAdapter adapterTiempo = new TiempoAdapter(prediccion.getList());
                recycler.setAdapter(adapterTiempo);

            }

            @Override
            public void onFailure(Call<ObjPrediccion> call, Throwable t) {
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