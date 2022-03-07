package com.example.appincatsessions.data;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appincatsessions.R;
import com.example.appincatsessions.adapter.ContactAdapter;
import com.example.appincatsessions.database.ContactDatabase;
import com.example.appincatsessions.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private RecyclerView contactsRecyclerView;
    private ContactDatabase contactDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDB();
        setUpRV();
        setUpClicks();

        contacts.addAll(contactDatabase.contactDao().getAllContacts());

    }

    private void setUpDB() {
        contactDatabase = Room.databaseBuilder(getApplicationContext(), ContactDatabase.class, "contact_db")
                //have to remove.
                .allowMainThreadQueries()
                .build();
    }

    private void setUpRV() {
        contactsRecyclerView = findViewById(R.id.rv_contacts);
        contactAdapter = new ContactAdapter(contacts);
        contactsRecyclerView.setAdapter(contactAdapter);
    }

    private void setUpClicks() {
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add_contact);
        floatingActionButton.setOnClickListener(view -> {
            contactDatabase.contactDao().addContact(new Contact("Mahmoud Elsayed " +
                      new Random().nextInt(1000)
                    , "01012122135", R.drawable.img));

            contacts.clear();
            contacts.addAll(contactDatabase.contactDao().getAllContacts());
            contactAdapter.notifyDataSetChanged();
        });
    }
}