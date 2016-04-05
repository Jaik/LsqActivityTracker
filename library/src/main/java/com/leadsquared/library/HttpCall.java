package com.leadsquared.library;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jainendra Kumar on 4/5/2016.
 */
class HttpCall {

    static String post(String urlString, String data) {
        String text = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            text = sb.toString();
        } catch (Exception ex) {
            return null;
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (Exception ignored) {
            }
        }
        return text;
    }
}
