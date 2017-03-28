package com.auribises.mediaplayerdemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishantkumar on 28/03/17.
 */

public class FilesAdapter extends ArrayAdapter<FileBean>{

    Context context;
    int resource;
    ArrayList<FileBean> fileList;

    public FilesAdapter(Context context, int resource, ArrayList<FileBean> fileList) {
        super(context, resource, fileList);

        this.context = context;
        this.resource = resource;
        this.fileList = fileList;
    }

    // get view shall be executed n number of time from 0 to n-1, n is size of arraylist
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView image = (ImageView)view.findViewById(R.id.imageView);
        TextView txtTitle = (TextView)view.findViewById(R.id.textView);

        // Read the ArrayList and get the Object
        FileBean fb = fileList.get(position);

        image.setBackgroundResource(fb.getImage());
        txtTitle.setText(fb.getTitle());

        return view;
    }
}
