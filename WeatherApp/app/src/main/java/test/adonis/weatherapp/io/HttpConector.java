package test.adonis.weatherapp.io;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import test.adonis.weatherapp.constants.ServerConstants;
import test.adonis.weatherapp.constants.WeatherConstants;

import static test.adonis.weatherapp.constants.WeatherConstants.TAG;


/**
 * Created by Paola on 09/06/2017.
 */

public class HttpConector {


    public static String getData(String what, String  ... params){
        String jsonText = "";
        URL url = null;
        URLConnection urlConnection = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;

        try{
            String urlToRequest = buildUrl(what, params);
            if(urlToRequest == null || urlToRequest.equals("")){
                return null;
            }

            url = new URL(urlToRequest);

            urlConnection = url.openConnection();
            inputReader = new InputStreamReader( urlConnection.getInputStream() , "UTF8");
            bufReader = new BufferedReader(inputReader);

            String tempString;

            while(true){
                tempString = bufReader.readLine();

                if(tempString !=  null){
                    jsonText += tempString;
                }else
                    break;
            }

            Log.d(WeatherConstants.TAG, jsonText );
            return jsonText;
        } catch (MalformedURLException e) {
            Log.d(TAG, "MalformedURLException " + e.getMessage());
        } catch(IOException e){
            Log.d(TAG, "IOException "+ e.getMessage());
        } catch(Exception e){
            Log.d(TAG, "Exception " + e.getMessage());
        }
        finally {
            url = null;
            urlConnection = null;

            try {
                if(inputReader != null){
                    inputReader.close();
                    inputReader = null;
                }
                if(bufReader != null){
                    bufReader.close();
                    bufReader = null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.gc();
        }
        return null;
    }

    private static String buildUrl(String what, String ... params) {
        String url = null;

        String urlParams = "";

        if(params.length > 1){
            for (int i = 0; i < params.length; i = i + 2) {
                urlParams = urlParams.concat((params[i] + "=" + params[i + 1]) +
                        (((i + 2) < params.length) ? "&" : ""));
            }

            url = ServerConstants.REQUEST_URL + what + urlParams;
        } else {
            /*if(!what.equals(ServerConstants.STANDALONE))
                url = ServerConstants.REQUEST_URL + what;
            else
                url = params[0];*/
            if(what.equals(ServerConstants.STANDALONE) || what.equals(ServerConstants.DAILY)){
                url = params[0];
            } else {
                url = ServerConstants.REQUEST_URL + what;
            }
        }

        return url;
    }

}
