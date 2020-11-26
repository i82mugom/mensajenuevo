package com.manuelmurillo.mensajenuevo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edittextnumero;
    EditText edittextmensajes;
    String textomensaje;
    String numeromensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void funcionaccion(View view) {

        edittextnumero= (EditText)findViewById(R.id.editText);
        edittextmensajes = (EditText)findViewById(R.id.editTextmensaje);
        textomensaje = edittextmensajes.getText().toString();
        numeromensaje = edittextnumero.getText().toString();
        //Toast.makeText(this, "numero"+numeromensaje, Toast.LENGTH_SHORT).show();
        try{
          // en verde, código para añadir
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                //Toast.makeText(this, "No tiene permiso", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},225);
            }
            else{
               // Toast.makeText(this, "Ya tiene permiso", Toast.LENGTH_SHORT).show();
            }
            // en rojo, código de SmsManager, y solicitud de envío
            SmsManager mensaje = SmsManager.getDefault();
            mensaje.sendTextMessage(numeromensaje, null, textomensaje, null, null);
            Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Mensaje no enviado", Toast.LENGTH_SHORT).show();
        }
    }
}
