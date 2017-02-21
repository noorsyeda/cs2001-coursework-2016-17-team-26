package com.example.android.project26;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by home on 31/01/2017.
 */

public class DisplayPictureForGallery extends Activity {
    private ImageView mImageView;
    private Handler mHandler = new Handler();
    private Button btn_upload;
    static final int BUFFER_SIZE = 4096;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String path = extras.getString("key");
            mImageView = (ImageView) findViewById(R.id.image_load);
            Bitmap b = (BitmapFactory.decodeFile(path));
            mImageView.setImageBitmap(b);
        }



        btn_upload = (Button) findViewById(R.id.btnUpload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new sendFile().execute(new String[] {"http://134.83.83.25:47326/Hello"});
            }
        });
    }



    public static File lastFileModified(String dir) {
        File fl = new File(dir);
        File[] files = fl.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
            }
        }
        return choice;
    }

    private class sendFile extends AsyncTask<String, Void, String> {
        public String success = "Success";
        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection httpConn = null;
            try {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    String file = extras.getString("key");
                    File uploadFile = new File(file);
                    FileInputStream inputStream = new FileInputStream(uploadFile);

                    System.out.println("File to upload: " + file);

                    // creates a HTTP connection
                    URL url1 = new URL(urls[0]);
                    httpConn = (HttpURLConnection) url1.openConnection();
                    httpConn.setUseCaches(false);
                    httpConn.setDoOutput(true);
                    httpConn.setRequestMethod("POST");
                    httpConn.setRequestProperty("fileName", uploadFile.getName());
                    httpConn.connect();

                    // sets file name as a HTTP header
                    Log.i("fileName", uploadFile.getName());

                    // opens output stream of the HTTP connection for writing data
                    OutputStream outputStream = httpConn.getOutputStream();

                    // Opens input stream of the file for reading data
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead = -1;

                    System.out.println("Start writing data...");

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Data was written.");
                    outputStream.close();
                    inputStream.close();
                }
            } catch (SocketTimeoutException e) {
                Log.e("Debug", "error: " + e.getMessage(), e);
            } catch (MalformedURLException ex) {
                Log.e("Debug", "error: " + ex.getMessage(), ex);
            } catch (IOException ioe) {
                Log.e("Debug", "error: " + ioe.getMessage(), ioe);
            }

            try {
                // always check HTTP response code from server
                int responseCode = httpConn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // reads server's response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    String response = reader.readLine();
                    System.out.println("Server's response: " + response);
                    success = null;
                }
                else {
                    System.out.println("Server returned non-OK code: " + responseCode);
                    success = "";
                }
            } catch (IOException ioex) {
                Log.e("Debug", "error: " + ioex.getMessage(), ioex);
            }
            return success;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null){
                showToast("Upload Successful");

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://134.83.83.25:47326/Hello")
                        .build();

                Response response = null;

                    response = client.newCall(request).execute();

                TextView textElement = (TextView) findViewById(R.id.output);
                    textElement.setVisibility(View.VISIBLE);
                    textElement.setText(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                showToast("Upload Failed");
            }
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Uploading...",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        private void showToast(final String text)
        {
            DisplayPictureForGallery.this.runOnUiThread(new Runnable()
            {
                public void run()
                {
                    Toast.makeText(DisplayPictureForGallery.this, text, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
