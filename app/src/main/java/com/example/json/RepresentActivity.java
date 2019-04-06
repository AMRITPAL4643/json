package com.example.json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.json.Modal.EmployeeModal;

public class RepresentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_represent);
        Bundle bundle = getIntent().getExtras();
        Intent i = getIntent();
        EmployeeModal   employeeModal = (EmployeeModal) i.getSerializableExtra("sampleObject");
        Log.d("Check", "onCreate: "+employeeModal.getName());
//        setText(String.valueOf(employeeModal.getId()));
//        name.setText(employeeModal.getName());
//        uname.setText(employeeModal.getUsername());
//        email.setText(employeeModal.getEmail());
//        addres.setText(employeeModal.getAddress().getCity());
//        p.setText(employeeModal.getPhone());

    }
}
