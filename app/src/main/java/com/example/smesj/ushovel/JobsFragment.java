package com.example.smesj.ushovel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Smesj on 10/5/2017.
 */

public class JobsFragment extends Fragment {

    private TextView tv;
    Button button;
    String jsonurl = "https://cmpt370-2017.herokuapp.com/";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View V = inflater.inflate(R.layout.jobsfrag, container, false);

        button = (Button)V.findViewById(R.id.bn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, jsonurl, (String) null, new Response.Listener<JSONObject>() {
                StringRequest jsonstring = new StringRequest(Request.Method.GET,jsonurl, new Response.Listener<String>(){
                    //@Override
                    public void onResponse(String response){
                        tv.setText(response);
                    }
//                    public void onResponse(JSONObject response) {
//                        try {
//                            tv.setText(response.getString("Name"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "something went wrong",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

//                MySingleton.getInstance(getActivity()).addToRequestque(jsonObjectRequest);
                MySingleton.getInstance(getActivity()).addToRequestque(jsonstring);
            }
        });

        tv = (TextView) V.findViewById(R.id.textView);
        return V;
    }

    public void changeText(String text){
        tv.setText(text);
        return;
    }
}
