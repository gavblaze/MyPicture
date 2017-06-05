package com.example.android.mypicture;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "https://s-media-cache-ak0.pinimg.com/564x/e8/15/a7/e815a787fb770107c34238b202c40a1c.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainAsyncTask task = new MainAsyncTask();
        task.execute(IMAGE_URL);
    }

    private class MainAsyncTask extends AsyncTask<String, Void, Drawable> {
        @Override
        protected Drawable doInBackground(String... args) {
            String url = args[0];
            Drawable result = QueryUtils.fetchImage(url);
            return result;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(Drawable drawable) {
            RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.activity_main);
            mainLayout.setBackground(drawable);
        }
    }
}
