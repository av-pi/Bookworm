package com.example.bookworm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Entity class for managing each Book added in the application
 */
@Entity(tableName = "book")
public class Book implements Serializable {

    public static final String BOOK_STATUS_READING = "reading";
    public static final String BOOK_STATUS_INTERESTED = "Interested";
    public static final String BOOK_STATUS_FINISHED = "finished";
    public static final String BOOK_STATUS_ABANDONED = "abandoned";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String name;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "image_url")
    private String imageURL;

    @ColumnInfo(name = "book_url")
    private String bookURL;

    @ColumnInfo(name = "short_description")
    private String shortDesc;

    @ColumnInfo(name = "long_description")
    private String longDesc;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "is_expanded")
    private boolean isExpanded;

    public Book(int id, String name, String author, String imageURL, String bookURL, String shortDesc, String longDesc) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.imageURL = imageURL;
        this.bookURL = bookURL;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.isExpanded = false;
        this.status = "interested"; //Interested, Reading, Finished, Abandoned, Searched
    }

    @Ignore
    public Book(String name, String author, String imageURL, String bookURL, String shortDesc, String longDesc) {
        this.name = name;
        this.author = author;
        this.imageURL = imageURL;
        this.bookURL = bookURL;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.isExpanded = false;
        this.status = "interested";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBookURL() {
        return bookURL;
    }

    public void setBookURL(String bookURL) {
        this.bookURL = bookURL;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", bookURL='" + bookURL + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                '}';
    }

}
