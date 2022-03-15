package com.example.appincatsessions.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.appincatsessions.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addContact(Contact contact);

    @Query("SELECT * From contacts ORDER By id DESC")
    LiveData<List<Contact>> getAllContacts();

}
