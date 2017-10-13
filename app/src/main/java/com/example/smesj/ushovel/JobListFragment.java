package com.example.smesj.ushovel;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Smesj on 10/12/2017.
 */

public class JobListFragment extends ListFragment {

    String jsonurl = "https://cmpt370-2017.herokuapp.com/jobs";
    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        String loading = "Loading...";
        names.add(loading);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, jsonurl, (String)null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                names.clear();
                for (int i=0; i<response.length(); i++){
                    try  {
                        JSONObject job = response.getJSONObject(i);
                        String location = job.getString("Location");
                        names.add(location);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getActivity(), "something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(getActivity()).addToRequestque(jsonArrayRequest);

    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, names);
        setListAdapter(adapter);
    }

    public void onResume(){
        super.onResume();
        names.clear();
        String loading = "Loading...";
        names.add(loading);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, jsonurl, (String)null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                names.clear();
                for (int i=0; i<response.length(); i++){
                    try  {
                        JSONObject job = response.getJSONObject(i);
                        String location = job.getString("Location");
                        names.add(location);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getActivity(), "something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(getActivity()).addToRequestque(jsonArrayRequest);

    }

}
