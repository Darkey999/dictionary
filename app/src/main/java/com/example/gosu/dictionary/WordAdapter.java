package com.example.gosu.dictionary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gosu on 2017-02-05.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    public int color;

    public WordAdapter(Context context, ArrayList<Word> objects, int color) {
        super(context, 0, objects);
        this.color = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.my_list, null);
        //finding elements of the layout
        TextView myListFirst = (TextView) convertView.findViewById(R.id.myListFirst);
        TextView myListSecond = (TextView) convertView.findViewById(R.id.myListSecond);
        ImageView myListImage = (ImageView) convertView.findViewById(R.id.myListImage);
        LinearLayout wordsLinear = (LinearLayout) convertView.findViewById(R.id.wordsLinear);

        //setting resources
        Word getWord = getItem(position);
        myListFirst.setText(getWord.getEnglishWord());
        myListSecond.setText(getWord.getOtherWord());
        myListImage.setImageResource(getWord.getImageId());
        myListImage.setBackgroundColor(Color.parseColor("#FFF7DA"));
        wordsLinear.setBackgroundResource(color);

        return convertView;
    }
}

