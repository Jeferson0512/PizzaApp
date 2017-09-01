package com.jeferson.pizzaapp;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class ServicioActivity extends AppCompatActivity {

    private Spinner spinner_tipo;
    private RadioGroup radio_Group;
    private RadioButton btn_gruesa,btn_delgada,btn_artesanal;
    private Variables v1 = new Variables();

    private int extra1, extra2;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);

        radio_Group = (RadioGroup)findViewById(R.id.radio_Group);
        btn_gruesa = (RadioButton) findViewById(R.id.radio_button_gruesa);
        btn_delgada = (RadioButton) findViewById(R.id.radio_button_delgada);
        btn_artesanal = (RadioButton) findViewById(R.id.radio_button_artesanal);
        spinner_tipo = (Spinner)findViewById(R.id.spinner_tipo);

        spinner_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int post, long id) {

                String tipo_pizza = parent.getItemAtPosition(post).toString();
                switch (tipo_pizza){
                    case "Americana":
                        v1.setTipo_pizza("Americana");
                        v1.setPrecio(40);
                        Total();
                    break;
                    case "Meet Lover":
                        v1.setTipo_pizza("Meet Lover");
                        v1.setPrecio(45);
                        Total();
                    break;
                    case "Hawaiana":
                        v1.setTipo_pizza("Hawaiana");
                        v1.setPrecio(65);
                        Total();
                    break;
                    case "Super Suprema":
                        v1.setTipo_pizza("Super Suprema");
                        v1.setPrecio(60);
                        Total();
                    break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void RadioClicked(View view){
        boolean chec = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.radio_button_gruesa:
                v1.setTipo_maza("Maza Gruesa");
                if(chec)
                    break;
            case R.id.radio_button_delgada:
                v1.setTipo_maza("Maza Delgada");
                if(chec)
                    break;
            case R.id.radio_button_artesanal:
                v1.setTipo_maza("Maza Artesana");
                if(chec)
                    break;
        }
    }

    public void complementos(View view){

        CheckBox checkBox = (CheckBox)view;
        switch (view.getId()){
            case R.id.checkbox_queso:
                if (checkBox.isChecked())
                    extra1 = 8;
                    Total();
            break;
            case R.id.checkbox_jamon:
                if (checkBox.isChecked())
                    extra2 = 12;
                Total();
            break;

        }

    }

    public void Total(){
        v1.setPrecio_extra(extra1 + extra2);
        total = v1.getPrecio()+v1.getPrecio_extra();
    }

    public void Ordenar(View view){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.servicio_entrega);
        dialog.setTitle("Informacion de su Cuenta");

        Button button_cancel = (Button)findViewById(R.id.Dialog_Cancel);
        Button button_ok = (Button)findViewById(R.id.Dialog_Ok);
        TextView txt_informacion = (TextView)findViewById(R.id.informacion_entrega);

        txt_informacion.setText("Su pizza "+v1.getTipo_pizza()+" con "+v1.getTipo_maza()+" a S/."+total+" + IGV esta en proceso de envio cuando le da ''OK''");
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                pedido_notificacion(view);

            }
        });
        dialog.show();
    }

    public void pedido_notificacion(View view){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ServicioActivity.this, PrincipalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingIntent = PendingIntent.getActivity(ServicioActivity.this, 100, intent, PendingIntent.FLAG_ONE_SHOT);
                Notification notification = new NotificationCompat.Builder(ServicioActivity.this)
                        .setContentTitle("PIZZA GO")
                        .setContentText("Su pizza "+v1.getTipo_pizza()+" esta en proceso de envio")
                        .setSmallIcon(R.mipmap.pizza)
                        .setColor(ContextCompat.getColor(ServicioActivity.this, R.color.colorPrimary))
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();

                NotificationManager notificationManager = (NotificationManager) ServicioActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(100, notification);

            }
        }, 10000);
    }

}
