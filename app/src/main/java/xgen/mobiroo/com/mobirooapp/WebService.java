package xgen.mobiroo.com.mobirooapp;

import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by tc on 17/06/16.
  * The central class for web service communication
 *
 */

public class WebService {

    public interface QueryCallback <Y extends ResponseBase> {
        void response(Y response);
    };

    static public <X extends RequestBase<Y>, Y extends ResponseBase>
        void sendRequestAsync(final X request,final QueryCallback query_callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Y response = sendRequest(request);
                    query_callback.response(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

   static public <X extends RequestBase<Y>, Y extends ResponseBase> Y sendRequest(X request) {

       HttpURLConnection connection = null;
       OutputStream os = null;
       InputStream is = null;

       Y response = request.getResponse();

       try {
           URL url = new URL(request.getUri());
           String requestMethod = request.getRequestMethodType();

           connection = (HttpURLConnection) url.openConnection();

           connection.setConnectTimeout((int) (1000F * Constants.WebServiceConnectionTimeout));
           connection.setReadTimeout((int) (1000F * Constants.WebServiceReadTimeout));
           connection.setDoOutput(true);

           connection.setRequestProperty("Accept", "Application/JSON");
           connection.setRequestProperty("Content-Type", "Application/JSON");
           connection.setRequestMethod(requestMethod);

           if (requestMethod.equalsIgnoreCase("GET")) {
               connection.setDoOutput(false);

           } else {
               //POST
           }

           int statusCode = connection.getResponseCode();

           if (statusCode != HttpsURLConnection.HTTP_OK) {
               response.setSuccess(false);
               response.setMessage("Http request error: " + statusCode);
               return response;
           }

           is = connection.getInputStream();
           response.map_data = response.Parse(is);
           response.result=true;
           response.responseMessage="success";

       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ParserConfigurationException e) {
           e.printStackTrace();
       } catch (SAXException e) {
           e.printStackTrace();
       } finally {
           if (connection != null)
               connection.disconnect();

           if (os != null)
               try {
                   os.close();
               } catch (Exception ex) {
                   // ex.printStackTrace();
               }
           if (is != null)
               try {
                   is.close();
               } catch (Exception ex) {
                   // ex.printStackTrace();
               }
       }

       return response;
   }

}



