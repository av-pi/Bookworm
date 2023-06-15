package com.example.bookworm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AllBooksRecViewAdapter extends RecyclerView.Adapter<AllBooksRecViewAdapter.ViewHolder> {

    private ArrayList<Book> bookList = new ArrayList<>();
    private Context allBooksContext;

    public AllBooksRecViewAdapter(Context allBooksContext) {
        this.allBooksContext = allBooksContext;
    }

    public void setAllBooksContext(Context allBooksContext) {
        this.allBooksContext = allBooksContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookName.setText(bookList.get(position).getName());

        Glide.with(allBooksContext)
                .asBitmap()
                .load(bookList.get(position).getImageURL())
                .into(holder.bookImage);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(allBooksContext, bookList.get(position).getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(allBooksContext, BookActivity.class);
                allBooksContext.startActivity(intent);
            }
        });

        holder.authorName.setText(bookList.get(position).getAuthor());
        holder.shortDesc.setText(bookList.get(position).getShortDesc());

        if (bookList.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView bookImage;
        private TextView bookName;
        private ImageView downArrow, upArrow;
        private TextView shortDesc, authorName;
        private RelativeLayout expandedLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.book_card_view);
            bookImage = itemView.findViewById(R.id.book_image);
            bookName = itemView.findViewById(R.id.book_name);
            downArrow = itemView.findViewById(R.id.btn_down_arrow);
            upArrow = itemView.findViewById(R.id.btn_up_arrow);
            expandedLayout = itemView.findViewById(R.id.expanded_relative_layout);
            authorName = itemView.findViewById(R.id.txt_author);
            shortDesc = itemView.findViewById(R.id.txt_short_desc);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = bookList.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = bookList.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}
