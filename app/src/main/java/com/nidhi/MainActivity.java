package com.nidhi;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    private LinearLayout linearLayout;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout)findViewById(R.id.linearlayout);

        getWebserviceData();
    }

    private void getWebserviceData(){

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait....");
        progressDialog.show();


        // Tag used to cancel the request
        String tag_json_arry = "json_array_req";

        String url = "https://wise.strose.edu/rest/public/AllDirectoryEntries/json";

        JsonArrayRequest req = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("response", response.toString());
                        parseJson(response);
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error ", "Error: " + error.getMessage());
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }

    private void parseJson(JSONArray jsonArray){
        try {

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                TextView tvId = new TextView(context);
                tvId.setText("ID : "+jsonObject.getInt("ID"));
                linearLayout.addView(tvId);

                TextView tvFname = new TextView(context);
                tvFname.setText("FIRSTNAME : "+jsonObject.getString("FIRSTNAME"));
                linearLayout.addView(tvFname);

                TextView tvLname = new TextView(context);
                tvLname.setText("LASTNAME : "+jsonObject.getString("LASTNAME"));
                linearLayout.addView(tvLname);

                TextView tvbuilding = new TextView(context);
                tvbuilding.setText("BUILDING : "+jsonObject.getString("BUILDING"));
                linearLayout.addView(tvbuilding);

                TextView tvphone = new TextView(context);
                tvphone.setText("PHONE : "+jsonObject.getString("PHONE"));
                linearLayout.addView(tvphone);

                TextView tvemail = new TextView(context);
                tvemail.setText("EMAIL : "+jsonObject.getString("EMAIL"));
                linearLayout.addView(tvemail);

                TextView tvdept = new TextView(context);
                tvdept.setText("DEPT : "+jsonObject.getString("DEPT"));
                linearLayout.addView(tvdept);


                TextView tvposition = new TextView(context);
                tvposition.setText("POSITION : "+jsonObject.getString("POSITION"));
                linearLayout.addView(tvposition);


                TextView tvurl = new TextView(context);
                tvurl.setText("URL : "+jsonObject.getString("URL"));
                linearLayout.addView(tvurl);

                View view = new View(context);
                view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,3));
                view.setBackgroundColor(Color.BLACK);
                linearLayout.addView(view);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
































































































































































































