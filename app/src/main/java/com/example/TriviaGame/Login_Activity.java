package com.example.TriviaGame;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.TriviaGame.DataBase.DatabaseHelper;
import com.example.TriviaGame.DataBase.UserDetails;
import com.example.TriviaGame.databinding.ActivityLoginBinding;

public class Login_Activity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_);
        databaseHelper=new DatabaseHelper(this);
        //create Account Intent.........
        binding.creatAccountTextID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login_Activity.this, Registration_Activity.class);
                startActivity(intent);
            }
        });

        //login Intent and share preference and database.........
        binding.loginButtonID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String email=binding.loginEmailEditTextID.getText().toString();
                String password=binding.loginPasswordEditTextID.getText().toString();
                UserDetails userDetails = databaseHelper.findUser(email,password); //after Regeneration  when data is save and given for login

                if (userDetails != null) {

                    if(binding.rememberMeID.isChecked()){
                        SharePrefarence sharePrefarence =new SharePrefarence(Login_Activity.this);
                      sharePrefarence.setName("userNamedata");
                      sharePrefarence.setPassword("userPassworddata");
                        Intent intent = new Intent(Login_Activity.this, StartGame_Activity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Intent intent = new Intent(Login_Activity.this, StartGame_Activity.class);
                        intent.putExtra( "USER_DETAILS" , userDetails);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {

                    Toast.makeText(getApplicationContext(),"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
