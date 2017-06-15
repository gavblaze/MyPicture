package com.example.android.mypicture;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gavin on 01-Mar-17.
 */

public class QueryUtils {


    private static final String LOG_TAG = "QueryUtils Class";

    private static URL createURL(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static InputStream openConnection (URL url) {
        InputStream is = null;
        HttpURLConnection urlConnection;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(100);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = urlConnection.getInputStream();
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }


    private static Bitmap inputStreamToBitmap (InputStream inputStream) {
        Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
        return myBitmap;
    }

    private static Drawable bitmapToDrawable (Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(Resources.getSystem(), bitmap);
        return drawable;
    }

    public static Drawable fetchImage(String stringUrl) {
        URL url = createURL(stringUrl);
        InputStream is = openConnection(url);
        Bitmap bt = inputStreamToBitmap(is);
        Drawable dr = bitmapToDrawable(bt);
        return dr;
    }
}
