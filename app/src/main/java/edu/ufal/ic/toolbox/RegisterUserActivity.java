package edu.ufal.ic.toolbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import edu.ufal.ic.toolbox.util.GetInfoCategoria;

/**
 * Created by jonathas on 21/03/16.
 */
public class RegisterUserActivity extends Activity {

    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;

    private ArrayList listaCategoria = null;
    private ArrayList<String> selecionados = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstancesState){

        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_register);

        new GetLista().execute();

        inputFullName = (EditText) findViewById(R.id.full_name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        Button btnSave = (Button) findViewById(R.id.btnSaveScreen);
        final Button btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);


        btnSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //implementar rotina de salvar e por aqui
                Toast.makeText(getApplicationContext(),
                        "FALTA IMPLEMENTAR FUNCIONALIDADE", Toast.LENGTH_SHORT)
                        .show();
                //se retornar ok, tornar visivel o botao de voltar
                btnLinkToLogin.setVisibility(View.VISIBLE);
            }
        });


        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private class GetLista extends AsyncTask<String, String, ArrayList<String>> {

        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(RegisterUserActivity.this);
            pDialog.setMessage("Baixando categorias ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
        /*TESTE*/

        @Override
        protected ArrayList<String> doInBackground(String... args) {

            try {
                listaCategoria = new GetInfoCategoria().getListaTodos();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listaCategoria;
        }
        @Override
        protected void onPostExecute(ArrayList<String> listaCategoria) {

            pDialog.dismiss();


            Spinner spinner = (Spinner) findViewById(R.id.categoria_spinner);

            ArrayAdapter mAdaptador = new ArrayAdapter(RegisterUserActivity.this,
                    android.R.layout.simple_spinner_item, listaCategoria);

            mAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(mAdaptador);
        }
    }
}
