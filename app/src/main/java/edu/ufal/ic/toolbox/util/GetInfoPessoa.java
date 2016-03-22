package edu.ufal.ic.toolbox.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jonathas on 21/03/16.
 */
public class GetInfoPessoa {
    //URL do webservice
    private String URL = "http://192.168.1.107:8080/WebService/rest/pessoa/";

    //TAG para json
    private final String TAG_TIPO = "nome";
    private ArrayList<String> pessoas = null;

    public ArrayList<String> getListaTodos() throws JSONException {

        GetJSONFromWebService jParser = new GetJSONFromWebService();
        // Pega o JSON do webservice
        URL += "todos";
        JSONArray json = jParser.getJSONFromUrl(URL);

        pessoas = new ArrayList<>();
        for(int i=0; i< json.length();i++) {
            //Pega um objeto do arrayjson passado do webservice
            JSONObject obj = json.getJSONObject(i);
            //pega valor da tag tipo
            pessoas.add(obj.getString(TAG_TIPO));
        }
        return pessoas;
    }

    public JSONObject getJsonPessoa(String nome) throws JSONException {

        GetJSONFromWebService jParser = new GetJSONFromWebService();

        nome = nome.replace(" ","%20");

        URL += nome;

        JSONArray json= jParser.getJSONFromUrl(URL);

        return json.getJSONObject(0);
    }
}
