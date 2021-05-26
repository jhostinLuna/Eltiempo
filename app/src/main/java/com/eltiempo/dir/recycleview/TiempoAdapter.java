package com.eltiempo.dir.recycleview;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eltiempo.dir.R;
import com.eltiempo.dir.modelo.Lista;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;


import java.io.IOException;
import java.util.ArrayList;

public class TiempoAdapter extends RecyclerView.Adapter<TiempoAdapter.ViewHolder> {
    ArrayList<Lista> listDatos;

    public TiempoAdapter(ArrayList<Lista> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dia,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.asignarDatos(listDatos.get(position));

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        String urlImg  = "https://openweathermap.org/img/wn/";//10d@4x.png
        TextView dia,fecha,temp_max,temp_min,precipitacion, humedad,viento,hora;
        ImageView iconoTiempo;
        TextView ciudad;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dia = itemView.findViewById(R.id.txt_dia_item);
            fecha = itemView.findViewById(R.id.txt_fecha_item);
            temp_max = itemView.findViewById(R.id.txt_tempmax_item);
            temp_min =(TextView) itemView.findViewById(R.id.txt_tempmin);
            precipitacion = itemView.findViewById(R.id.txt_precipitacion_item);
            humedad = itemView.findViewById(R.id.txt_humity_item);
            viento = itemView.findViewById(R.id.txt_viento_item);
            iconoTiempo = itemView.findViewById(R.id.img_dia_item);
            hora = itemView.findViewById(R.id.txt_hora_item);
            ciudad = itemView.findViewById(R.id.txt_ciudad_ldt);
        }
        public void asignarDatos(Lista lista) {

            Instant instant = Instant.ofEpochSecond(lista.getDt());
            LocalDateTime vfecha = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

            String textFecha = vfecha.format(formatter);
            formatter = DateTimeFormatter.ofPattern("HH:mm");
            String textHora = vfecha.format(formatter);
            dia.setText(vfecha.getDayOfWeek().toString());
            fecha.setText(textFecha);
            hora.setText(textHora);
            temp_max.setText(String.valueOf(Math.round(lista.getMain().getTemp_max()))+"º");
            temp_min.setText(String.valueOf(Math.round(lista.getMain().getTemp_min()))+"º");
            precipitacion.setText(String.valueOf(Math.round(lista.getMain().getPressure()))+"hPa");
            humedad.setText(String.valueOf(Math.round(lista.getMain().getHumidity()))+"%");
            viento.setText(String.valueOf(Math.round(lista.getWind().getSpeed()))+"km/h");
            String url = urlImg+lista.getWeather().get(0).getIcon()+"@4x.png";
            myRequestIcono(url);


        }

        //Carga el icono de tiempo de https://openweathermap.org/img/wn/
        public void myRequestIcono(String url) {
            Picasso.get().load(url).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                    iconoTiempo.setImageBitmap(bitmap);

                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }

    }
}
