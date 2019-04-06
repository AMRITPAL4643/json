package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.json.Modal.EmployeeModal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Employeelistadapter extends BaseAdapter  {
    private Context context;
    private ArrayList<EmployeeModal> arrayList;
    public Employeelistadapter(Context context, ArrayList arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_employdata, null);
        TextView id = view.findViewById(R.id.txteid);
        TextView name = view.findViewById(R.id.txtename);
        TextView uname = view.findViewById(R.id.txteusername);
        TextView email = view.findViewById(R.id.txtemail);
        TextView addres = view.findViewById(R.id.txteaddress);
        TextView p = view.findViewById(R.id.txtphone);
        TextView web = view.findViewById(R.id.txtwebsite);
        TextView compan = view.findViewById(R.id.txtcompany);

        EmployeeModal employeeModal = arrayList.get(position);
        id.setText(String.valueOf(employeeModal.getId()));
        name.setText(employeeModal.getName());
        uname.setText(employeeModal.getUsername());
        email.setText(employeeModal.getEmail());
        addres.setText(employeeModal.getAddress().getCity());
        p.setText(employeeModal.getPhone());
        return  view;
    }
}
