package com.example.appcontactv20;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    public List<Contact> getAllContacts();

    @Insert
    public void insertContact(Contact...contact);

}
