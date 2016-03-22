package edu.ufal.ic.toolbox.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jonathas on 21/03/16.
 */
public class GetJSONFromWebService {

    static InputStream is = null;
    static JSONArray jobj = null;
    static String json = "";

    public JSONArray getJSONFromUrl(String url){

        //faz uma requisição HTTP
        try{
            URL myUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) myUrl.openConnection();

            is = new BufferedInputStream(urlConnection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is),8);
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine())!=null){

                sb.append(line+"\n");
            }
            is.close();
            json = sb.toString();

            if(!json.contains("[")){
                json = "["+json;
            }
            if (!json.contains("]")){
                json = json+"]";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{

            jobj = new JSONArray(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jobj;
    }
}
