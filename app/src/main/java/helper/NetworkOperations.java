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

import view.UILoginScreen;
import viewmodel.UILoginViewModel;

/**
 * Created by sujata on 6/7/15.
 */
public class NetworkOperations extends AsyncTask<String , Void, String> {


    private DataOutputStream printout;
    private InputStream is;
    public  UILoginScreen.AsyncResponse delegate;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
          String data =  fetchData(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

            delegate.processFinish(result);

    }

    public String fetchData(String[] data) throws IOException {

        try {
            URL url = new URL(data[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
            conn.connect();
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("email", ""+data[1]);
            jsonParam.put("password",data[2]);
            JSONObject jsonMain = new JSONObject();
            jsonMain.put("user", jsonParam);
            printout = new DataOutputStream(conn.getOutputStream ());
            String str = jsonMain.toString();
            byte[] dataBytes=str.getBytes("UTF-8");
            printout.write(dataBytes);
            printout.flush();
            printout.close();
            is = conn.getInputStream();
            String contentAsString =   convertStreamToString(is);
            return contentAsString;
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
