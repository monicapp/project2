package com.monicapp1.monicapp1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegistroUsuario extends AppCompatActivity implements View.OnClickListener{

    Button registro;
    EditText nombre,email,contraseña,confircontraseña;

    // IP de mi Url

    String IP = "http://pruebabdandroid.esy.es";
    // Rutas de los Web Services
    String INSERT = IP + "/insertar_alumno.php";

    ObtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        registro=(Button) findViewById(R.id.registro);
        registro.setOnClickListener(this);

        nombre=(EditText) findViewById(R.id.editText);

        email=(EditText) findViewById(R.id.editText2);

        contraseña=(EditText) findViewById(R.id.editText3);
        confircontraseña=(EditText) findViewById(R.id.editText4);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registro:

                String nombre2=nombre.getText().toString();
                String email2=email.getText().toString();
                String contraseña1=contraseña.getText().toString();
                String contraseña2=confircontraseña.getText().toString();

                if(nombre2.isEmpty()||email2.isEmpty()||contraseña1.isEmpty()||contraseña2.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Faltan datos" , Toast.LENGTH_SHORT).show();
                }else{
                    if(contraseña1.equals(contraseña2)){
                        hiloconexion = new ObtenerWebService();
                        hiloconexion.execute(INSERT,"3",nombre2,email2,contraseña1);   // Parámetros que recibe doInBackground
                        Intent intent=new Intent(RegistroUsuario.this,RegistroIngreso.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden" , Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";

            if(params[1]=="3"){    // insert

                try {
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();

                    //Creo el Objeto JSON
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("nombre", params[2]);
                    jsonParam.put("email", params[3]);
                    jsonParam.put("contraseña", params[4]);

                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();


                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {

                        String line;
                        BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null) {
                            result.append(line);
                            //response+=line;
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {      // hay un alumno que mostrar
                            devuelve = "Usuario Registrado correctamente";

                        } else if (resultJSON == "2") {
                            devuelve = "El usuario no pudo registrarse";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return devuelve;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), s , Toast.LENGTH_SHORT).show();
            //super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


}

