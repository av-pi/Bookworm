package com.example.bookworm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Insert
    void insert(Book book);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);

    @Query("SELECT * FROM book WHERE status = :status")
    List<Book> getShelf(String status);

    //@Query("UPDATE book SET status = :status WHERE id = :bookId")
    //void addToShelf(int bookId, String status);
}
