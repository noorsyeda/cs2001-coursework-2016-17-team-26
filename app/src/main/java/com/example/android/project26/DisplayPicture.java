package com.example.android.project26;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by home on 31/01/2017.
 */

public class DisplayPicture extends Activity {
    private ImageView mImageView;
    private Handler mHandler = new Handler();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);


        mImageView = (ImageView) findViewById(R.id.image_load);
        File image = new File(Environment.getExternalStorageDirectory() + "/project26");
        mImageView.setImageBitmap(BitmapFactory.decodeFile(String.valueOf(lastFileModified(String.valueOf(image)))));

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
}
