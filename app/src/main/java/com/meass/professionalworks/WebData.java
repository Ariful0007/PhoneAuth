package com.meass.professionalworks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebData extends AppCompatActivity {
    ListView listView;
    private static final String JSON_URL = "http://192.168.1.35:8080/jsondata/";
    //the tutorial list where we will store all the tutorial objects after parsing json
    List<Model_Rest> tutorialList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_data);
        listView = (ListView) findViewById(R.id.listView);
        tutorialList = new ArrayList<>();
        //this method will fetch and parse the data
        loadTutorialList();
    }

    private void loadTutorialList() {
        ProgressDialog progressDialog=new ProgressDialog(WebData.this);
        progressDialog.setMessage("Loading data");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("tutorials");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject getData=jsonArray.getJSONObject(i);
                        Model_Rest model_rest=new Model_Rest(getData.getString("name"),getData.getString("imageurl")
                        ,getData.getString("description"));
                        tutorialList.add(model_rest);

                    }
                    RestAdapter restAdapter=new RestAdapter(tutorialList,getApplicationContext());
                    listView.setAdapter(restAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(WebData.this);
        requestQueue.add(stringRequest);

    }
}