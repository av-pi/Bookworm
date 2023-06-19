package com.example.bookworm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button allBooks, currentBooks, readBooks, wishList, abandoned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    /**
     * On click events for each shelf button
     * @param v The Button that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_all_books:
                Intent intentOne = new Intent(v.getContext(), AllBooksActivity.class);
                startActivity(intentOne);
                break;

            case R.id.btn_current_books:
                Intent intentTwo = new Intent(v.getContext(), CurrentlyReadingBooksActivity.class);
                startActivity(intentTwo);
                break;

            case R.id.btn_want_to_read:
                Intent intentThree = new Intent(v.getContext(), InterestedBooksActivity.class);
                startActivity(intentThree);
                break;

            case R.id.btn_read_books:
                Intent intentFour = new Intent(v.getContext(), FinishedBooksActivity.class);
                startActivity(intentFour);
                break;

            case R.id.btn_add_abandoned:
                Intent intentFive = new Intent(v.getContext(), AbandonedBooksActivity.class);
                startActivity(intentFive);
                break;

            default:
                break;
        }
    }

    private void initViews() {
        allBooks = findViewById(R.id.btn_all_books);
        allBooks.setOnClickListener(this);

        currentBooks = findViewById(R.id.btn_current_books);
        currentBooks.setOnClickListener(this);

        readBooks = findViewById(R.id.btn_read_books);
        readBooks.setOnClickListener(this);

        wishList = findViewById(R.id.btn_want_to_read);
        wishList.setOnClickListener(this);

        abandoned = findViewById(R.id.btn_add_abandoned);
        abandoned.setOnClickListener(this);
    }




}