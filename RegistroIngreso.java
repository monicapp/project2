package com.monicapp1.monicapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroIngreso extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Spinner spinner1, spinner2, spinner3;
    private ArrayAdapter<String> formaPago;
    private String [] formaPago2=new String[]{"Semanal","Quincenal","Mensual"};
    private Button Aceptar;
    private EditText sueldo,comision,otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ingreso);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sueldo=(EditText)findViewById(R.id.sueldo);
        comision=(EditText)findViewById(R.id.comision);
        otros=(EditText)findViewById(R.id.otros);

        formaPago=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,formaPago2);

        spinner1= (Spinner) findViewById(R.id.spinner);
        spinner1.setAdapter(formaPago);
        spinner2= (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(formaPago);
        spinner3= (Spinner) findViewById(R.id.spinner3);
        spinner3.setAdapter(formaPago);

        Aceptar= (Button) findViewById(R.id.aceptar);
        Aceptar.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        String sueldo2=sueldo.getText().toString();
        String comision2=comision.getText().toString();
        String otros2=otros.getText().toString();

        if(sueldo2.isEmpty()&&comision2.isEmpty()&&otros2.isEmpty()){
            Toast.makeText(getApplicationContext(), "Debe llenar alguno de los campos" , Toast.LENGTH_SHORT).show();
        }else{
            switch (v.getId()){
                case R.id.aceptar:
                    //Falta mandar llenar la base de datos
                    Intent intent = new Intent(RegistroIngreso.this, RegistroObligacion.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
