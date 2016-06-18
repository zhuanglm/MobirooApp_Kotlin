package xgen.mobiroo.com.mobirooapp;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Raymond Zhuang on 2016-06-18.
 */
public class ResponseBase {
    public String responseMessage;
    public boolean result = false;
    Map<String, String> map_data;

    public void setSuccess(boolean b) {
    }

    public void setMessage(String s) {
    }

    public Map<String, String>  Parse(InputStream is)
            throws ParserConfigurationException, SAXException, IOException {

        String theString = convertStreamToString(is);
        map_data = getGsonInstance().fromJson(theString, new TypeToken<Map<String, String>>() {}.getType());
        return map_data;

        //JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(theString.getBytes("UTF-8")), "UTF-8"));
        //return getGsonInstance().fromJson(reader, this.getClass());
    }

    public static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static Gson getGsonInstance(){
        return new GsonBuilder()
                .serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    }
}
