package com.example.json;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.json.Modal.Address;
import com.example.json.Modal.EmployeeModal;
import com.example.json.Modal.Geo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView e;
    ArrayList<EmployeeModal> employeeModals;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e  = findViewById(R.id.lstemployees);
        employeeModals = new ArrayList<>();
        processJSON();
        setupAdapter();

    }

    private void setupAdapter() {
        Employeelistadapter arrayAdapter = new Employeelistadapter(this,employeeModals);
        e.setAdapter(arrayAdapter);
        e.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,RepresentActivity.class);
                employeeModals.get(position).getName();
                i.putExtra("sampleObject",employeeModals.get(position));
                startActivity(i);


            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void processJSON() {
        String jsonString = loadJSONFromAsset();
        if (jsonString != null) {
            try {
                JSONArray mJSONArray = new JSONArray(jsonString);

                for (int i = 0; i < mJSONArray.length(); i++) {
                    JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                    EmployeeModal employeeModal = new EmployeeModal();
                    if (mJSONObject.has("id")) {
                        int id = mJSONObject.getInt("id");
                        employeeModal.setId(id);

                    }

                    String name = mJSONObject.getString("name");
                    employeeModal.setName(name);
                    Log.d("-- JSON -- ", name);

                    //Read Address JSON Object
                    JSONObject mAddress = mJSONObject.getJSONObject("address");
                    String city = mAddress.getString("city");
                    String suite = mAddress.getString("suite");
                    String street = mAddress.getString("city");
                    String zipCode =mAddress.getString("zipcode");

                    JSONObject mGeo = mAddress.getJSONObject("geo");
                    String lat = mGeo.getString("lat");
                    String lng = mGeo.getString("lng");
// create object to set it
                    Geo ageo  = new Geo();
                    ageo.setLat(lat);
                    ageo.setLat(lng);
//create object to set it
                    Address address = new Address();
                    address.setCity(city);
                    address.setStreet(street);
                    address.setSuite(suite);
                    address.setZipcode(zipCode);
                    address.setGeo(ageo);
                    employeeModal.setAddress(address);
                    employeeModals.add(employeeModal);

                    Log.d("-- JSON -- ", name + " : " + city);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("UserList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
