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
public abstract class ResponseBase {
    public String responseMessage, responseData;
    public boolean result = false;

    public void setSuccess(boolean b) {
    }

    public void setMessage(String s) {
    }


}
