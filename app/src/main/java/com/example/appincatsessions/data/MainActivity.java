package com.example.appincatsessions.data;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appincatsessions.R;
import com.example.appincatsessions.adapter.ContactAdapter;
import com.example.appincatsessions.database.ContactDatabase;
import com.example.appincatsessions.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private RecyclerView contactsRecyclerView;
    private ContactDatabase contactDatabase;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDB();
        setUpRV();
        setUpClicks();
        setUPData();

    }

    private void setUPData() {
        contactDatabase.contactDao().getAllContacts().observe(this, mContacts -> {
            contacts.clear();
            contacts.addAll(mContacts);
            contactAdapter.notifyDataSetChanged();
        });
    }

    private void setUpDB() {
        contactDatabase = ContactDatabase.getINSTANCE(this);
    }

    private void setUpRV() {
        contactsRecyclerView = findViewById(R.id.rv_contacts);
        contactAdapter = new ContactAdapter(contacts);
        contactsRecyclerView.setAdapter(contactAdapter);
    }

    private void setUpClicks() {
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add_contact);
        floatingActionButton.setOnClickListener(view -> {

            executorService.execute(() -> {
                contactDatabase.contactDao().addContact(new Contact("Mahmoud Elsayed " +
                        new Random().nextInt(1000)
                        , "01012122135", R.drawable.img));
            });

        });
    }
}
