package com.example.TriviaGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.TriviaGame.DataBase.UserDetails;

import com.example.TriviaGame.databinding.ActivityStartgameBinding;

public class StartGame_Activity extends AppCompatActivity {

    private ActivityStartgameBinding mBinding;

    private UserDetails mUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_startgame_ );

        mUserDetails = (UserDetails) getIntent().getSerializableExtra( "USER_DETAILS" );
        if(mUserDetails == null) {
            Toast.makeText( this, "Invalid user", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }

        mUserDetails = (UserDetails) getIntent().getSerializableExtra( "USER_DETAILS" );//get data from mainActivity OnFinish() method
        mBinding.startGametextViewID.setText( mUserDetails.getName()+" your previous score is :"+mUserDetails.getScore());
        mBinding.startGameButtonID.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent( StartGame_Activity.this, MainActivity.class);
        intent.putExtra( "USER_DETAILS" , mUserDetails);
        startActivity(intent);
    }
} );

    }

}
