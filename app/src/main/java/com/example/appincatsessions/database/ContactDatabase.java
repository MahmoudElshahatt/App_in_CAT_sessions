package com.example.appincatsessions.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appincatsessions.model.Contact;

@Database(entities = {Contact.class},version = 2)
public abstract class ContactDatabase extends RoomDatabase {

    abstract public ContactDao contactDao();
}
