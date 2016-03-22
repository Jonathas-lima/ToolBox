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

import edu.ufal.ic.toolbox.util.GetInfoCategoria;

/**
 * Created by jonathas on 21/03/16.
 */
public class ListCategActivity  extends ListActivity {


    //adaptador para popular a lista
    private ArrayAdapter<String> mAdaptador;
    private ArrayList listaCategoria = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inicia background para buscar do webservice
        new JSONParse().execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){

        super.onListItemClick(l, v, position, id);
        String profissao = this.getListAdapter().getItem(position).toString();
        //Toast.makeText(this, "Usuário selecionou a profissão: " + profissao, Toast.LENGTH_SHORT).show();

        // ****adiciona um extra no itent para proxima activity****
        Intent i = new Intent(this, ListPersonCategActivity.class);
        i.putExtra("profissao", profissao);
        startActivity(i);
    }



    private class JSONParse extends AsyncTask<String, String, ArrayList<String>> {

        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(ListCategActivity.this);
            pDialog.setMessage("Baixando categorias ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

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
            mAdaptador = new ArrayAdapter(ListCategActivity.this,android.R.layout.simple_list_item_1, listaCategoria);
            setListAdapter(mAdaptador);

        }
    }
}
