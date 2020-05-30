package com.example.TriviaGame;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.TriviaGame.DataBase.DatabaseHelper;
import com.example.TriviaGame.DataBase.UserDetails;
import com.example.TriviaGame.databinding.ActivityRegistrationBinding;


public class Registration_Activity extends AppCompatActivity {  //implement onclick listener for date picker ..........

    private ActivityRegistrationBinding binding;

    private RadioButton radioButton;
    private DatePickerDialog datePickerDialog;

    DatabaseHelper databaseHelper; //for database
    UserDetails userDetails;//for database



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration_);

        //for database
        databaseHelper =new DatabaseHelper(this);
       SQLiteDatabase sqLiteOpenHelper= databaseHelper.getWritableDatabase();
      //..........


      // on click leastener for BirthofDate............
        binding.dateOfbirthID.setOnClickListener(new View.OnClickListener() {

            DatePicker datePicker=new DatePicker(Registration_Activity.this);


            final   int  Year= datePicker.getYear();
            final int  Month= datePicker.getMonth()+1;
            final  int  Day= datePicker.getDayOfMonth();

            @Override
            public void onClick(View v) {
//                datePickerDialog.getDatePicker().setSpinnersShown(true);
//                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog=new DatePickerDialog(Registration_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                      binding.dateOfbirthID.setText(Day+"/"+Month+"/"+Year);

//                        datePickerDialog.getDatePicker().setSpinnersShown(true);
//                        datePickerDialog.getDatePicker().setCalendarViewShown(false);
//                        return datePickerDialog;

                    }
                },Year,Month,Day);



                datePickerDialog.show();

            }
        });
    //..................................

        binding.creatAccountButtonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //for radio button,,,,male,female........
                int radioid = binding.radioGroupID.getCheckedRadioButtonId();
                radioButton = findViewById(radioid);

                //database...............

                String name =binding.firstNameID.getText().toString()+" "+binding.lastNameID.getText().toString();
                String gender =radioButton.getText().toString();
                String email =binding.EmailID.getText().toString();
                String phone_number =binding.phoneNumberID.getText().toString();
                String birthday =binding.dateOfbirthID.getText().toString();
                String password =binding.regPasswordID.getText().toString();


                userDetails=new UserDetails();

                userDetails.setName(name);
                userDetails.setGender(gender);
                userDetails.setEmail(email);
                userDetails.setPhone(phone_number);
                userDetails.setBirthday(birthday);
                userDetails.setPassword(password);


                long rowID=databaseHelper.insertData(userDetails);

                if (rowID == -1) {
                    Toast.makeText(getApplicationContext(), "Account create Unsuccessful", Toast.LENGTH_SHORT).show();
                } else {
                   userDetails.setId(rowID );

                    Toast.makeText(getApplicationContext(), "Account create Successful", Toast.LENGTH_SHORT).show();
                }

                //........................



//               int radioid = binding.radioGroupID.getCheckedRadioButtonId();
//
//                radioButton = findViewById(radioid);


               //for get text registration  to profile..........

                Intent intent = new Intent(Registration_Activity.this, Login_Activity.class);
                intent.putExtra("registraton_Info_First_name", binding.firstNameID.getText().toString());
                intent.putExtra("registraton_Info_Last_name", binding.lastNameID.getText().toString());
                intent.putExtra("registraton_Info_email", binding.EmailID.getText().toString());
                intent.putExtra("registraton_Info_phone", binding.phoneNumberID.getText().toString());
                intent.putExtra("registraton_Info_gender_male_female", radioButton.getText().toString());
                intent.putExtra("birth_Of_Date", binding.dateOfbirthID.getText().toString());

                startActivity(intent);



            }
        });


        //Adding back button in Action bar...continue..
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //.............



    }



    //Adding back button in Action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){

            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
    //..............





}
