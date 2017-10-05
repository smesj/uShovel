package com.example.smesj.ushovel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Smesj on 10/5/2017.
 */

public class JobsFragment extends Fragment {

    private TextView tv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View V = inflater.inflate(R.layout.jobsfrag, container, false);
        tv = (TextView) V.findViewById(R.id.textView);
        return V;
    }

    public void changeText(String text){
        tv.setText(text);
        return;
    }
}
