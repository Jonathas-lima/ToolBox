package edu.ufal.ic.toolbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.ufal.ic.toolbox.util.GetInfoPessoa;

/**
 * Created by jonathas on 21/03/16.
 */
public class DetailPersonActivity extends Activity {

    private JSONObject infoPessoa = null;
    private String nomePessoa = null;

    private TextView outputFullName;
    private TextView outputEmail;
    private TextView outputPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_person);

        Bundle b = getIntent().getExtras();
        nomePessoa = b.getString("NomePessoa");

        outputFullName = (TextView) findViewById(R.id.textViewName);
        outputEmail = (TextView) findViewById(R.id.textViewEmail);
        outputPhone = (TextView) findViewById(R.id.textViewFone);

        new JSONParse().execute();

        Button btnEnviaEmail = (Button) findViewById(R.id.btnEnviarEmail);
        btnEnviaEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //implementar rotina de salvar e por aqui
                Toast.makeText(getApplicationContext(),
                        "FALTA IMPLEMENTAR FUNCIONALIDADE", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(DetailPersonActivity.this);
            pDialog.setMessage("Baixando informações ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {


                infoPessoa = new GetInfoPessoa().getJsonPessoa(nomePessoa);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return infoPessoa;
        }
        @Override
        protected void onPostExecute(JSONObject infoPessoa) {

            pDialog.dismiss();
            try {
                outputFullName.setText(infoPessoa.getString("nome"));
                outputEmail.setText(infoPessoa.getString("email"));
                outputPhone.setText(infoPessoa.getString("telefone"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
