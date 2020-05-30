package com.example.TriviaGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TriviaGame.DataBase.DatabaseHelper;
import com.example.TriviaGame.DataBase.UserDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final long START_TIME_IN_MILLIS = 60000;
    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private List<CountryEntity> mCountryEntityList;
    //private List<String> ClList;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private Button button;
    private EditText capitalEDT;
    Context context;
    private CountryEntity mCurrentCountryEntity;

    private int pointCounter = 0;
    private int roundCounter = 200;
    private TextView count;
    private TextView round;

    DatabaseHelper databaseHelper; //for database
    UserDetails mUserDetails;//for database



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mUserDetails = (UserDetails) getIntent().getSerializableExtra( "USER_DETAILS" );
        if(mUserDetails == null) {
            Toast.makeText( this, "Invalid user", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }

        //for database
        databaseHelper =new DatabaseHelper(this);
        SQLiteDatabase sqLiteOpenHelper= databaseHelper.getWritableDatabase();
        //..........

        round = findViewById( R.id.roundID ); //for Round.............
        count = findViewById( R.id.pointID ); //for point.............

        mTextViewCountDown = findViewById( R.id.timerID );//for timer.............

        mRecyclerView = (RecyclerView) findViewById( R.id.rvListItems );
        mAdapter = new RecyclerViewAdapter( this, getListData() );
        mAdapter.setCallback( new OnItemSelectCallback() {
            @Override
            public void onItemSelected(CountryEntity countryEntity) {
                mCurrentCountryEntity = countryEntity;
            }
        } );
        LinearLayoutManager manager = new LinearLayoutManager( MainActivity.this );
        mRecyclerView.setHasFixedSize( true );
        mRecyclerView.setLayoutManager( manager );
        mRecyclerView.setAdapter( mAdapter );

        capitalEDT = findViewById( R.id.countryCapitalTextID );
        button = findViewById( R.id.buttonID );
        button.setOnClickListener( this );

        startTimer();   //timer function call...............
    }

    // create timer function .......................
    private void startTimer() {
        mCountDownTimer = new CountDownTimer( mTimeLeftInMillis, 100 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                int seconds = (int) (mTimeLeftInMillis / 1000) + +1;
                String timeLeftFormatted = String.format( Locale.getDefault(), "%02d:%02d", minutes, seconds );
                mTextViewCountDown.setText( timeLeftFormatted );
                roundCounter++;
                round.setText( Integer.toString( roundCounter / 198 ) );
            }

            @Override
            public void onFinish() {
                //database...............
                String score =count.getText().toString();

                mUserDetails.setScore( score );

                long rowID=databaseHelper.update( mUserDetails );
                if (rowID == - 1) {
                    Toast.makeText(getApplicationContext(), "secore Unsuccessful", Toast.LENGTH_SHORT).show();
                } else {
                    Intent  intent = new Intent( MainActivity.this, ScoreActivity.class );
                    intent.putExtra( "USER_DETAILS", mUserDetails );
                    startActivity( intent );
                    Toast.makeText( MainActivity.this, "Time is over", Toast.LENGTH_SHORT ).show();
                }
            }
        }.start();
    }

///////.............................................


    private List<CountryEntity> getListData() {
        mCountryEntityList = new ArrayList<>();
        mCountryEntityList.add( new CountryEntity( "Bangladesh ", "Dhaka" ) );
        mCountryEntityList.add( new CountryEntity( "India ", "New Delhi" ) );
        mCountryEntityList.add( new CountryEntity( "Pakistan ", "Korachi" ) );
        mCountryEntityList.add( new CountryEntity( "Bhutan ", "Thimphu" ) );
        mCountryEntityList.add( new CountryEntity( "Srilanka ", "Colombo" ) );
        mCountryEntityList.add( new CountryEntity( "America ", "Washington" ) );
        mCountryEntityList.add( new CountryEntity( "Nepal ", "Kathmandu" ) );
        mCountryEntityList.add( new CountryEntity( "Uganda", "Kampala" ) );
        mCountryEntityList.add( new CountryEntity( "Saudi Arabia ", "Riyadh" ) );
        mCountryEntityList.add( new CountryEntity( "Canada ", "Ottawa" ) );
        mCountryEntityList.add( new CountryEntity( "Italy ", "Rome" ) );
        Collections.shuffle( mCountryEntityList );

        return mCountryEntityList;
    }


    private void deleteSelectedItems() {

        String inputCapital = capitalEDT.getText().toString();
        if (inputCapital.isEmpty()) {
            Toast.makeText( this, "Please Write the capital name", Toast.LENGTH_SHORT ).show();

            return;
        }
        if (!inputCapital.equals( mCurrentCountryEntity.getCapital() )) {
            Toast.makeText( this, "Wrong answer", Toast.LENGTH_SHORT ).show();

            return;
        }


        if (inputCapital.equals( mCurrentCountryEntity.getCapital() )) {
            int indexToRemove = mCountryEntityList.indexOf( mCurrentCountryEntity );
            mCountryEntityList.remove( mCurrentCountryEntity );
            mAdapter.notifyItemRemoved( indexToRemove );
            pointCounter++;
            count.setText(  String.valueOf(pointCounter  ) );
            Toast.makeText( this, "You Earn Point", Toast.LENGTH_SHORT ).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String s = capitalEDT.getText().toString();
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteSelectedItems();
                break;
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onClick(View v) {
        if(mCurrentCountryEntity == null) {
            Toast.makeText( this, "Please select country", Toast.LENGTH_SHORT).show();
            return;

        }

        deleteSelectedItems();
    }
}
