package edu.ufal.ic.toolbox;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

import edu.ufal.ic.toolbox.util.GetInfoPessoa;

/**
 * Created by jonathas on 21/03/16.
 */
public class ListPersonCategActivity extends ListActivity {

    //adaptador para popular a lista
    private ArrayAdapter<String> mAdaptador;

    private ArrayList<String> listaPessoas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inicia background para buscar do webservice
        new JSONParse().execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){

        super.onListItemClick(l, v, position, id);

        // ****Para pegar parametro passado na activity anterior***
        Bundle b = getIntent().getExtras();
        String profissao = b.getString("profissao");


        String pessoa = this.getListAdapter().getItem(position).toString();
        //Toast.makeText(this, "Usuário selecionou a pessoa: " + pessoa+" da profissão "+profissao, Toast.LENGTH_SHORT).show();


        //****adiciona um extra no itent para proxima activity****

        Intent i = new Intent(this, DetailPersonActivity.class);
        i.putExtra("NomePessoa", pessoa);
        startActivity(i);
    }

    private class JSONParse extends AsyncTask<String, String, ArrayList<String>> {

        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(ListPersonCategActivity.this);
            pDialog.setMessage("Baixando lista ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected ArrayList<String> doInBackground(String... args) {

            try {
                listaPessoas = new GetInfoPessoa().getListaTodos();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listaPessoas;
        }
        @Override
        protected void onPostExecute(ArrayList<String> listaPessoas) {

            pDialog.dismiss();

            //insere os elementos na lista
            mAdaptador = new ArrayAdapter(ListPersonCategActivity.this,android.R.layout.simple_list_item_1, listaPessoas);
            setListAdapter(mAdaptador);
        }
    }
}
