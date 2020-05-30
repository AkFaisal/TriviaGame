package com.example.TriviaGame;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefarence {


    Context context;
    SharedPreferences sharedPreferences;


    private String name;
    private String password;



    public SharePrefarence(Context context) {

        this.context=context;

        sharedPreferences= context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);




    }





    public SharePrefarence logout(){

        sharedPreferences.edit().clear().commit();


        return null;
    }






    public String getName() {

        name=sharedPreferences.getString("userNamedata","");



        return name;
    }

    public void setName(String name) {
        this.name = name;
        sharedPreferences.edit().putString("userNamedata",name).commit();


    }

    public String getPassword() {


       password= sharedPreferences.getString("userPassworddata","");

        return password;
    }

    public void setPassword(String password) {


        sharedPreferences.edit().putString("userPassworddata",name).commit();

        this.password = password;
    }





}
