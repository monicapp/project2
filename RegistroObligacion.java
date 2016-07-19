package com.monicapp1.monicapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RegistroObligacion extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Button Aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_obligacion);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Aceptar= (Button) findViewById(R.id.aceptar);
        Aceptar.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aceptar:
                //Falta mandar llenar la base de datos
                Intent intent = new Intent(RegistroObligacion.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
