package com.example.smesj.ushovel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Smesj on 10/5/2017.
 */

public class UserFragment extends Fragment {

    private EditText eUserName, eLocation, eDate;
    private TextView output;
    private Button postJobButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View V = inflater.inflate(R.layout.userfrag, container, false);

        output = (TextView) V.findViewById(R.id.output_tv);


        eUserName = (EditText) V.findViewById(R.id.input_username);
        eLocation = (EditText) V.findViewById(R.id.input_location);
        eDate = (EditText) V.findViewById(R.id.input_date);

        postJobButton = (Button) V.findViewById(R.id.btn_post);

        postJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call method to send input to the server
                sendDataToServer();
                //output.setText(formatDataAsJSON());


                // check for null/incorrect values here
            }
        });

        return V;

    }

    private void sendDataToServer() {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://cmpt370-2017.herokuapp.com/jobs";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                output.setText(response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                output.setText("error");
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> jobEntities = new HashMap<String, String>();
                jobEntities.put("User_ID", eUserName.getText().toString());
                jobEntities.put("Location", eLocation.getText().toString());
                jobEntities.put("JobDate", eDate.getText().toString());

                return jobEntities;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }


    /**
     * method that will format the users user_ID, location and Date into proper JSON format
     * @return the string of the json if it worked, nothing if not
     */
    private String formatDataAsJSON() {
        final JSONObject root = new JSONObject();
        try {
            root.put("User_ID", eUserName.getText().toString());
            root.put("Location", eLocation.getText().toString());
            root.put("Date", eDate.getText().toString());

            return root.toString(1);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
