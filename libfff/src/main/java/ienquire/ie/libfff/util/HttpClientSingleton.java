package ienquire.ie.libfff.util;

import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.params.*;

import java.io.*;
import java.net.*;


public class HttpClientSingleton {

    private static final int JSON_CONNECTION_TIMEOUT = 100000;
    private static final int JSON_SOCKET_TIMEOUT = 140000;
    private static HttpClientSingleton instance;
    private HttpParams httpParameters;
    private DefaultHttpClient httpclient;


    private HttpClientSingleton() {
        httpParameters = new BasicHttpParams();
        setTimeOut(httpParameters);
        httpclient = new DefaultHttpClient(httpParameters);
    }

    public static DefaultHttpClient getHttpClientInstace() {
        if (instance == null)
            instance = new HttpClientSingleton();
        return instance.httpclient;
    }

    public static final String[] get(String url) {

        String[] result = new String[2];
        HttpGet httpget = new HttpGet(url);
        HttpResponse response;

        try {
            response = HttpClientSingleton.getHttpClientInstace().execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result[0] = String.valueOf(response.getStatusLine().getStatusCode());
                InputStream instream = entity.getContent();
                result[1] = toString(instream);
                instream.close();
            }
        } catch (Exception e) {
            result[0] = "0";
            result[1] = e.getMessage();
        }
        return result;
    }


    //WEB SERVICE METHODS------------------------------------------------------------------------------------

    public static final String[] post(String url, String json) {
        String[] result = new String[2];
        try {

            HttpPost httpPost = new HttpPost(new URI(url));
            httpPost.setHeader("Content-type", "application/json");
            StringEntity sEntity = new StringEntity(json, "UTF-8");
            httpPost.setEntity(sEntity);

            HttpResponse response;
            response = HttpClientSingleton.getHttpClientInstace().execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result[0] = String.valueOf(response.getStatusLine().getStatusCode());
                InputStream instream = entity.getContent();
                result[1] = toString(instream);
                instream.close();
            }

        } catch (Exception e) {
            result[0] = "0";
            result[1] = e.getMessage();
        }
        return result;
    }

    public static String toString(InputStream is) throws IOException {

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }

    private void setTimeOut(HttpParams params) {
        HttpConnectionParams.setConnectionTimeout(params, JSON_CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, JSON_SOCKET_TIMEOUT);
    }

    //WEB SERVICE METHODS------------------------------------------------------------------------------------


}