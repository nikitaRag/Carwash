package com.example.nekit.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class Review extends AppCompatActivity {
    EditText review;
    Button reviewButton;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        review = (EditText) findViewById(R.id.review);
        reviewButton = (Button) findViewById(R.id.reviewButton);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void sendReview(View view){
        String post = "client_id=" + Login.client.getClient_id()+"&appointment_id="+ MainActivity.resp +"&review=" + review.getText().toString() + "&rating=" + ratingBar.getRating();
        DataBase DB = Factory.createDB();
        DB.execute("sendreview.php",post);
        Intent intent = new Intent(Review.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
