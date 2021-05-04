package com.example.appcontactv20;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.Serializable;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private EditText name, phone, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                Random generator = new Random();
                String sname = name.getText().toString();
                String sphone = phone.getText().toString();
                String semail = email.getText().toString();
                Contact newContact = new Contact(generator.nextInt(), sname, sphone, semail, 102);
                Intent returnedIntent = new Intent();
                returnedIntent.putExtra ("contact", (Serializable) newContact);
                setResult(RESULT_OK, returnedIntent);
                finish();
                return true;

            case R.id.back:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

