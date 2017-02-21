package com.example.android.project26;

/**
 * Created by home on 14/12/2016.
 */

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class GalleryActivity extends Fragment{

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_gallery, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //Create intent
//                Intent intent = new Intent(getActivity(), .class);
//                intent.putExtra("title", item.getTitle());
//                intent.putExtra("image", item.getImage());
                //Start details activity
//                startActivity(intent);
                Intent openImage = new Intent(getActivity(),ImageDetails.class);
                getActivity().startActivity(openImage);
            }
        });
        return rootView;
    }

    // Prepare some dummy data for gridview
    public ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs =  getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, "Image#" + i));
        }
        return imageItems;
    }


}

