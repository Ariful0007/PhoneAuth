package com.meass.professionalworks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ConstaintsAcitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constaints_acitiy);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Transfer to Balance");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        Toasty.success(getApplicationContext(),"Adding",Toasty.LENGTH_SHORT,true).show();
        DatabaseHandler databaseHandler=new DatabaseHandler(ConstaintsAcitiy.this);
        databaseHandler.addContact(new Contact(1,"AbcD","10th"));
        databaseHandler.addContact(new Contact(1,"AbcD","10th"));
        databaseHandler.addContact(new Contact(1,"AbcD","10th"));
        databaseHandler.addContact(new Contact(1,"AbcD","10th"));

        List<Contact> gett=databaseHandler.getAlldata();
        for (Contact contact : gett) {
            String data="ID : "+contact.getId()+"\n Name : "+contact.getName()+" \n Batch : "+contact.getBatch();
        }



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        return super.onNavigateUp();
    }
}