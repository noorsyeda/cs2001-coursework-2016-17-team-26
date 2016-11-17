# group26

At this point the master branch contains the starting stage of our android app

On our to do list:
1) Integrate camera api
2) Develop button press actions

// this is for main class window code to press window
package com.example.dawoodmehar.newwindowprc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fir extends AppCompatActivity {

public Button but1;


public void  init(){
    but1=(Button)findViewById(R.id.jj);


    but1.setOnClickListener(
            new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toy= new Intent(fir.this,Second.class);
            startActivity(toy);
        }
    });



}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
// the xml for first activity is this:
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.dawoodmehar.newwindowprc.MainActivity">
/>

    <TextView
        android:text="first window"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_marginLeft="26dp"
        android:layout_marginStart="26dp"
        android:layout_marginTop="15dp"
        android:id="@+id/textView" />

    <Button
        android:text="push me"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/textView"
        android:layout_toRightOf="@+id/textView"
        android:layout_toEndOf="@+id/textView"
        android:layout_marginTop="144dp"
        android:id="@+id/jj" />
</RelativeLayout>




// this is second activity code which will open if u press push me 
package com.example.dawoodmehar.newwindowprc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
// the xml for second acitivity is this:
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_second"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.dawoodmehar.newwindowprc.Second">

    <TextView
        android:text="second screen"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_marginLeft="49dp"
        android:layout_marginStart="49dp"
        android:layout_marginTop="83dp"
        android:id="@+id/textView2" />
</RelativeLayout>


