package com.example.TriviaGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.TriviaGame.DataBase.UserDetails;

public class ScoreActivity extends AppCompatActivity {


    private TextView playerScoreName;
    private UserDetails mUserDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_score );
        playerScoreName=findViewById( R.id.player_scoreNameID );

        mUserDetails = (UserDetails) getIntent().getSerializableExtra( "USER_DETAILS" );//get data from mainActivity OnFinish() method
        playerScoreName.setText( mUserDetails.getName()+" your earning point :"+mUserDetails.getScore());


    }
}
