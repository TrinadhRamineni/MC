package com.example.admin.devika;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ArticlesActivity extends AppCompatActivity {
    private TextView articles_text;
    private String url;
    private int count;
    private ImageView imageArticle;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        imageArticle = findViewById(R.id.ivArticle);
        articles_text = findViewById(R.id.taArticle);
        Button previous = findViewById(R.id.btnPre);
        Button next = findViewById(R.id.btnNxt);
        url = "https://devika-87a00.firebaseio.com/articles/1";
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl(url);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                count = 0;
                imageArticle.setImageResource(R.drawable.a);
                //           Picasso.with(ArticlesActivity.this).load("https://firebasestorage.googleapis.com/v0/b/devika-87a00.appspot.com/o/ArticleImages%2F111.jpg?alt=media&token=2690dbf3-b1f0-4774-908b-0a82c35a2eb7").centerCrop().into(imageArticle);
                articles_text.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                url = url + '1';
                count++;
                if(count >= 10)
                {
                    count = 10;
                    articles_text.setText("Maximum of 10 Articles only");
                }
                else{
                databaseReference = firebaseDatabase.getReferenceFromUrl(url);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        if (count == 0 || count == 1){
                            imageArticle.setImageResource(R.drawable.a);
       //                 Picasso.with(ArticlesActivity.this).load("https://firebasestorage.googleapis.com/v0/b/devika-87a00.appspot.com/o/ArticleImages%2F111.jpg?alt=media&token=2690dbf3-b1f0-4774-908b-0a82c35a2eb7").resize(50, 50).centerCrop().into(imageArticle);
                    }
                        if(count == 2) {
                            imageArticle.setImageResource(R.drawable.aa);
         //                   Picasso.with(ArticlesActivity.this).load("https://firebasestorage.googleapis.com/v0/b/devika-87a00.appspot.com/o/ArticleImages%2F11.jpg?alt=media&token=6e8cd239-5eaf-4e30-a92c-1d5355caa5df").resize(75, 50).centerCrop().into(imageArticle);
                        }
                        if(count == 3) {
                            imageArticle.setImageResource(R.drawable.aaa);
           //                 Picasso.with(ArticlesActivity.this).load("https://firebasestorage.googleapis.com/v0/b/devika-87a00.appspot.com/o/ArticleImages%2F1.jpg?alt=media&token=886b4e9b-9c90-4bb0-af1f-933f29b15f1e").resize(80, 50).centerCrop().into(imageArticle);
                        }

                        articles_text.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (url.charAt(url.length()-1) != '/'){
                    if(count != 0){
                    url = url.substring(0,url.length()-1);
                    count--;
                    }
                    //url = url.replace(url.substring(url.length()-1), "");
                    databaseReference = firebaseDatabase.getReferenceFromUrl(url);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue(String.class);
                            articles_text.setText(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else{
                    articles_text.setText("No Articles");
                }
            }
        });
    }
}