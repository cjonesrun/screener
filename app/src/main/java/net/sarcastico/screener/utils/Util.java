package net.sarcastico.screener.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Utils
 */

public class Util {

    public static JSONObject build(String[] keys, String[] vals) {
        try {
            JSONObject o = new JSONObject();

            for (int i=0; i<keys.length; i++)
            {
                o.put(keys[i], vals[i]);
            }
            return o;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static URL build()
    {
        try {
            //return new URL( "http://192.168.1.128:8080/camel-rest-jpa/book/" + (new Random().nextInt(3)+1) );
            return new URL( "http://10.0.2.2:8080/camel-rest-jpa/book/" );
            //return new URL("http://google.com/");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject post(URL url, String path, HashMap<String, String> params) {
        return request("POST", url, path, params);
    }


    public static JSONObject get(URL url, String path, HashMap<String, String> params) {
        return request("GET", url, path, params);
    }

    private static JSONObject request(String method, URL myURL, String path, HashMap<String, String> params) {
        if (myURL == null) {
            Log.e(Util.class.getCanonicalName(), "URL cannot be null");
            return null;
        }
        String urlParams = "";
        if (params != null && !params.isEmpty()) {
            urlParams += "?";
            for (String key : params.keySet()) {
                String paramSet = "%s=%s";
                String value = params.get(key);
                paramSet = String.format(paramSet, key, value);
                if (urlParams.length() > 1) {
                    urlParams += "&";
                }
                urlParams += paramSet;
            }
        }
        URL url;
        try {
            if (path != null && !path.trim().isEmpty()) {
                if (myURL.toString().endsWith("/") && path.startsWith("/")) {
                    path = path.substring(1, path.length()-1);
                } else if (!myURL.toString().endsWith("/") && !path.startsWith("/")) {
                    path = String.format("/%s", path);
                }
                path = myURL.toString() + path;
            } else {
                path = myURL.toString();
            }
            url = new URL(path + urlParams);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        Log.i(Util.class.getCanonicalName(),"Connecting to: " + method + " " + url.toString());

        JSONObject result;
        try {
            URLConnection connection = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) connection;

            http.setRequestMethod(method);
            //http.setDoOutput("POST".equals(method));
            http.connect();

            InputStream res = http.getInputStream();
            String response = Util.read(res);

            Object json = new JSONTokener(response).nextValue();
            if (json instanceof JSONObject)
                result = (JSONObject) json;
            else if (json instanceof JSONArray) {
                result = new JSONObject().put("array", (JSONArray) json);
            } else {
                result = new JSONObject();
            }

            Log.i(Util.class.getCanonicalName(), result.toString());
        } catch (Exception e) {
            Log.e(Util.class.getCanonicalName(), e.getMessage(), e);
            result = null;
        }

        return result;
    }

    public static String read(InputStream stream) {
        if (stream == null) {
            return null;
        }
        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(isr);
        try {
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

