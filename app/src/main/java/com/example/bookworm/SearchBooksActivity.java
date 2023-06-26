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

    /**
     * Perform the search and parse the response into usable java Book objects
     * @param query
     */
    private void searchQuery(String query) {

        // RequestQueue initialized
        requestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        jsonRequest = new JsonObjectRequest(Request.Method.GET, url + query, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("items");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject item = array.getJSONObject(i);

                        // Surrounded in try catch blocks to handle missing fields in JSON response
                        try {
                            // Book object fields
                            String title = item.getJSONObject("volumeInfo").getString("title");
                            String author = parseAuthors(item.getJSONObject("volumeInfo").getJSONArray("authors"));
                            String imgUrl = parseImageUrl(item.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail"));
                            String bookUrl = item.getJSONObject("volumeInfo").getString("previewLink");
                            String shortDesc = item.getJSONObject("searchInfo").getString("textSnippet");
                            String longDesc = item.getJSONObject("volumeInfo").getString("description");


                            Book book = new Book(title, author, imgUrl, bookUrl, shortDesc, longDesc);
                            searchResultsList.add(book);
                        } catch(Exception e) {
                            // if any of the above fields are missing ignore current book
                            continue;
                        }


                    }
                    BooksRecViewAdapter adapter = new BooksRecViewAdapter(SearchBooksActivity.this);
                    searchResultsRecView.setAdapter(adapter);

                    searchResultsRecView.setLayoutManager(new LinearLayoutManager(SearchBooksActivity.this));

                    adapter.setBookList(searchResultsList);

                } catch (JSONException e) {
                    Toast.makeText(SearchBooksActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            /**
             * Parse one or more authors into a single
             * string for the Book author field
             * @param array
             * @return String with all the authors
             */
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

            /**
             * Turn image url from http to https
             * @param url
             * @return newUrl with https
             */
            private String parseImageUrl(String url) {
                String newUrl = url.substring(0,4) + "s" + url.substring(4);
                return newUrl;
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