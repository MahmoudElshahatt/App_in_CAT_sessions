package com.example.appincatsessions.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appincatsessions.model.Contact;

@Database(entities = {Contact.class}, version = 2)
public abstract class ContactDatabase extends RoomDatabase {
    private static volatile ContactDatabase INSTANCE;

    abstract public ContactDao contactDao();

    public static synchronized ContactDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ContactDatabase.class, "contact_db")
                    .build();
        }
        return INSTANCE;
    }
}
