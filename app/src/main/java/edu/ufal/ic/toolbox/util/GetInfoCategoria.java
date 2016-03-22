package edu.ufal.ic.toolbox.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jonathas on 21/03/16.
 */
public class GetInfoCategoria {


    private ArrayList<String> listaCategoria;
    private final String URL = "http://192.168.1.107:8080/WebService/rest/categoria/todos";

    //TAG para json
    private final String TAG_TIPO = "tipo";

    public ArrayList getListaTodos() throws JSONException {

        GetJSONFromWebService jParser = new GetJSONFromWebService();
        // Pega o JSON do webservice
        JSONArray json = jParser.getJSONFromUrl(URL);

        listaCategoria = new ArrayList<>(); //inicia a lista das categorias
        for(int i=0; i< json.length();i++) {
            //Pega um objeto do arrayjson passado do webservice
            JSONObject obj = json.getJSONObject(i);
            //pega valor da tag tipo
            listaCategoria.add(obj.getString(TAG_TIPO));
        }

        return listaCategoria;
    }
}
