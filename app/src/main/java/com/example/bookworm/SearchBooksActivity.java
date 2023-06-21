package com.example.bookworm;

import static android.content.ContentValues.TAG;

import static com.example.bookworm.Book.BOOK_STATUS_INTERESTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchBooksActivity extends AppCompatActivity {

    private RecyclerView searchResultsRecView;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonRequest;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=";

    private ArrayList<Book> searchResultsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);

        searchResultsRecView = findViewById(R.id.search_results_rec_view);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchQuery(query);
        }

    }

    private void searchQuery(String query) {

        // RequestQueue initialized
        requestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        jsonRequest = new JsonObjectRequest(Request.Method.GET, url + query, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: Parse response object and show results
                try {
                    JSONArray array = response.getJSONArray("items");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject item = array.getJSONObject(i);

                        // Book object fields
                        String title = item.getJSONObject("volumeInfo").getString("title");
                        String author = parseAuthors(item.getJSONObject("volumeInfo").getJSONArray("authors"));
                        String imgUrl = item.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail") + ".jpg";
                        String bookUrl = item.getJSONObject("volumeInfo").getString("previewLink");
                        String shortDesc = item.getJSONObject("searchInfo").getString("textSnippet");
                        String longDesc = item.getJSONObject("volumeInfo").getString("description");


                        Book book = new Book(title, author, imgUrl, bookUrl, shortDesc, longDesc);
                        searchResultsList.add(book);

                    }
                    BooksRecViewAdapter adapter = new BooksRecViewAdapter(SearchBooksActivity.this);
                    searchResultsRecView.setAdapter(adapter);

                    searchResultsRecView.setLayoutManager(new LinearLayoutManager(SearchBooksActivity.this));

                    adapter.setBookList(searchResultsList);
                    //test.setText(response.getString("title"));
                } catch (JSONException e) {
                    Toast.makeText(SearchBooksActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            private String parseAuthors(JSONArray array) {
                String authors = "";
                for (int i = 0; i < array.length(); i++) {
                    try {
                        authors = authors + array.get(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    authors = authors + "\n";
                }
                return authors;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchBooksActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonRequest);
    }

}