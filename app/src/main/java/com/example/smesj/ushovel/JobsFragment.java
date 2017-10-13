package com.example.smesj.ushovel;

import android.app.DownloadManager;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Smesj on 10/5/2017.
 */

public class JobsFragment extends Fragment {

    private TextView tv;
    ArrayList jobslist;
    ArrayAdapter<String> adapter;
    Button button;
    ListView list;
    String jsonurl = "https://cmpt370-2017.herokuapp.com/jobs";
    String jsonurl2 = "https://cmpt370-2017.herokuapp.com/";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View V = inflater.inflate(R.layout.jobsfrag, container, false);
        jobslist = new ArrayList();
        tv = (TextView) V.findViewById(R.id.textView);
        list = (ListView)V.findViewById(R.id.listv);
        button = (Button)V.findViewById(R.id.bn);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, jobslist);
        list.setAdapter(adapter);



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, jsonurl, (String)null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //do some stuff
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject job = response.getJSONObject(i);
                        String location = job.getString("Location");
                        //System.out.println(location);
                        String date = job.getString("Date");
                        //System.out.println(date);
                        jobslist.add(i,location);
                        //list.setAdapter(adapter);
                    }
                    for (int y=0; y<jobslist.size();y++){
                        System.out.println(jobslist);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "something went wrong",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(getActivity()).addToRequestque(jsonArrayRequest);


















        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, jsonurl, (String) null, new Response.Listener<JSONObject>() {
//                StringRequest jsonstring = new StringRequest(Request.Method.GET,jsonurl2, new Response.Listener<String>(){
//                    @Override
//                    public void onResponse(String response) {
//                        tv.setText(response);
//                    }

//                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, jsonurl, (String)null, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        //do some stuff
//                        try {
//                            for (int i=0; i<response.length(); i++){
//                                JSONObject job = response.getJSONObject(i);
//                                String location = job.getString("Location");
//                                //System.out.println(location);
//                                String date = job.getString("Date");
//                                //System.out.println(date);
//                                jobslist.add(i,location);
//                                //list.setAdapter(adapter);
//                            }
//                            for (int y=0; y<jobslist.size();y++){
//                                System.out.println(jobslist);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(), "something went wrong",Toast.LENGTH_SHORT).show();
//                        error.printStackTrace();
//                    }
//                });

//                MySingleton.getInstance(getActivity()).addToRequestque(jsonObjectRequest);
                //MySingleton.getInstance(getActivity()).addToRequestque(jsonArrayRequest);
                refresh();
            }
        });


        return V;
    }

    public void changeText(String text){
        tv.setText(text);
        return;
    }

    public void refresh(){
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }
}
