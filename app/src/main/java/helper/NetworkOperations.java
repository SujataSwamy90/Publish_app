package helper;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by sujata on 6/7/15.
 */
public class NetworkOperations extends AsyncTask<String , Void, String> {


    @Override
    protected String doInBackground(String... strings) {

        try {
          String data =  fetchData(strings);
            System.out.println("data: "+data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String fetchData(String[] myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            URL url = new URL(myurl[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            // Starts the query

            JSONObject jsMain = new JSONObject();

            JSONObject jsInside  = new JSONObject();
            jsInside.put("email",myurl[1]);
            jsInside.put("password",myurl[2]);
            jsMain.put("user",jsInside);
            dos.writeBytes(String.valueOf(jsMain));
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = convertStreamToString(is);
            Log.d("content as string: ", contentAsString);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {

                is.close();
            }
        }
        return null;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
