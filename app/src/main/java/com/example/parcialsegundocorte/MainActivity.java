package com.example.parcialsegundocorte;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    EditText usuario;
    EditText contraseña;
    Button iniciarSesion;

    String textUsuario;
    String textContraseña;
    String usuario_defecto = "Jhon85";
    String contraseña_defecto = "Colombia123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.campo_usuario);
        contraseña = findViewById(R.id.campo_contraseña);
        iniciarSesion = findViewById(R.id.button_sesion);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textUsuario = usuario.getText().toString();
                textContraseña = contraseña.getText().toString();
                validarInformación(textUsuario, textContraseña, view);
            }
        });
    }

    public void validarInformación(String textUsuario, String textContraseña, View view){
        if(usuario_defecto.equals(textUsuario) && contraseña_defecto.equals(textContraseña)){
            abrirMapa();
        } else {
            mostrarAlerta(view, textUsuario);
        }
    }

    public void mostrarAlerta(View view, String textUsuario) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Alerta!")
                .setMessage("El usuario y/o contraseña son incorrectos." + textUsuario)
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alerta = builder.create();
        alerta.show();
    }

    public void abrirMapa(){
        Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);

        Bundle args = new Bundle();
        LatLng location = new LatLng(4.653384977826347, -74.14522083254803); // San Francisco
        args.putParcelable("location", location);
        mapIntent.putExtra("bundle", args);

        startActivity(mapIntent);
    }
}