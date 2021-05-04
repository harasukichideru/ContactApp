package com.example.appcontactv20;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts;
    private RecyclerView rvContacts;
    private MyAdapter myAdapter;
    private SearchView searchView;

    private ContactDatabase contactDatabase;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList<Contact>();
        contacts.add(new Contact(1, "Trần Nguyễn Thiên Anh", "0948293342", "thienanh_dut@gmail.com", 18));
        contacts.add(new Contact(2, "Harasuki Chideru", "0943534554", "abccdef12345@gmail.com", 12));
        contacts.add(new Contact(3, "Yusuke Hakase", "08472933434", "opmmangaka@yahoo.com", 13));
        contacts.add(new Contact(4, "Han EunHae", "01221212456", "102180090@sv.dut.edu.vn", 14));
        contacts.add(new Contact(5, "Yusuke Murata", "01278972194", "lth_vinhdinh@gmail.com", 15));
        contacts.add(new Contact(6, "Gina Pericolo", "0122264656", "103190120@sv.dut.edu.vn", 16));
        contacts.add(new Contact(7, "Harasuki Pandora", "0127361194", "nguyenlethiennhupokemon@gmail.com", 15));
        contacts.add(new Contact(8, "Trần Văn Lâm", "0914745492", "VanLamDev01@gmail.com", 16));
        contacts.add(new Contact(9, "Harasuki Maiko", "01278913264", "HarasukiMai@gmail.com", 12));
        contacts.add(new Contact(10, "Huy lớp trưởng", "01215764826", "18t2bkdn@gmail.com", 12));

        searchView = findViewById(R.id.search_bar);
        rvContacts = findViewById(R.id.rv_contacts);
        rvContacts.setLayoutManager(new GridLayoutManager(this, 1));

        myAdapter = new MyAdapter(contacts, this);
        rvContacts.setAdapter(myAdapter);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                contactDatabase = ContactDatabase.getInstance(getApplicationContext());
                contactDao = contactDatabase.contactDao();

                List<Contact> dbContacts = contactDao.getAllContacts();
                for(Contact contact: dbContacts){
                    contacts.add(contact);
                }
                myAdapter.notifyDataSetChanged();
            }
        });
        setListener();
    }

    public void clickOnAddContact(View view){
        Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
        startActivityForResult(intent,133);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 133) {
            final Contact newContact = new Contact();
            contacts.add(newContact);
            myAdapter.notifyDataSetChanged();

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                   contactDao.insertContact(newContact);
                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void setListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.isEmpty()) {
                    ArrayList<Contact> filteredList = new ArrayList<>();
                    for (Contact item : contacts) {
                        if (item.getName().toLowerCase().contains(s.toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                    myAdapter = new MyAdapter(filteredList, MainActivity.this);
                    rvContacts.setAdapter(myAdapter);
                } else {
                    myAdapter = new MyAdapter(contacts, MainActivity.this);
                    rvContacts.setAdapter(myAdapter);
                }
                return false;
            }
        });
    }

}




