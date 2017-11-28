package io.eliot.bookr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity{

    public void print(String s){
        System.out.println(s);
    }
    EditText emailBox;
    EditText passBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        print("CURRENT ACTIVITY: SIGNUP");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        emailBox = (EditText)findViewById(R.id.emailBox);
        passBox = (EditText)findViewById(R.id.passwordBox);
        Button signUp = findViewById(R.id.button);
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("123");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        print("debugging321");
                        try{
                            String user = (emailBox.getText().toString());
                            String password = (passBox.getText().toString());
                            print("debug123");
                            if(user.contains("@")&&user.contains(".")&&password.length()>8){
                                print("att1");
                                userRegister(user,password);
                            }else{
                                print("error");
                            }
                            userRegister(user,password);
                        }catch(Exception e){
                            print("CAUGHT:"+e.toString());
                        }
                    }
                }).start();
            }
        });
        Button logIn = (Button)findViewById(R.id.button2);
        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("123");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String user = (emailBox.getText().toString());
                            String password = (passBox.getText().toString());
                            userLogin(user,password);
                        }catch(Exception e){
                            alertDisplayer("ERROR","Email/Pass wrong!");
                        }
                    }
                });
            }
        });
    }

    void alertDisplayer(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }


    void userRegister(final String username, final String password){
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                System.out.println("REGISTERING");
                if (e == null) {
                    System.out.println("SIGNUP SUCCESS");
                    alertDisplayer("Register Successful", "User: " + username);
                    Intent change = new Intent(SignUp.this, selecter.class);
                    startActivity(change);
                } else {
                    System.out.println("ERROR");
                    alertDisplayer("Register Fail", e.getMessage()+" Please Try Again");
                }
            }
        });
    }

    void userLogin(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {

                if (parseUser != null) {
                    alertDisplayer("Login Successful", "Welcome " + parseUser.getUsername());
                    Intent change = new Intent(SignUp.this, selecter.class);
                    startActivity(change);
                } else {
                    alertDisplayer("Login Failed", e.getMessage() + " Please Try Again");
                }
            }
        });
    }
}
