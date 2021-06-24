package com.example.weatherapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthLoginPage extends AppCompatActivity {

    EditText et_username,et_password;
    Button bt_login;
    DBHelper2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_authlogin );
        db = new DBHelper2( this );

        et_username = (EditText)findViewById( R.id.Username );
        et_password = (EditText)findViewById( R.id.Password );

        bt_login = (Button)findViewById( R.id.btnlogin );

        bt_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username  = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (username.equals( "" )) {
                    Toast.makeText( AuthLoginPage.this, "Username não inserido, tente novamente", Toast.LENGTH_SHORT ).show();
                }else if (password.equals( "" )){
                    Toast.makeText( AuthLoginPage.this, "Password não inserida, tente novamente", Toast.LENGTH_SHORT ).show();
                }else {
                    int rest = db.ValidarLogin(username,password);
                    if(rest == 1){
                        startActivity( new Intent(getApplicationContext(),ServiceActivity.class ));
                    }else{
                        Toast.makeText( AuthLoginPage.this, "Login ou Senha invalidos!", Toast.LENGTH_SHORT ).show();
                    }

                    overridePendingTransition( 0,0 );
                }
            }
        } );



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById( R.id.bottom_navigation );
        bottomNavigationView.setSelectedItemId( R.id.nav_home );

        bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity( new Intent(getApplicationContext(),MainActivity.class ));
                        overridePendingTransition( 0,0 );
                        return true;

                    case R.id.nav_developer:
                        startActivity( new Intent(getApplicationContext(),ServiceActivity.class ));
                        overridePendingTransition( 0,0 );
                        return true;

                    case R.id.nav_login:
                        startActivity( new Intent(getApplicationContext(),LoginActivity.class ));
                        overridePendingTransition( 0,0 );
                        return true;
                }


                return false;
            }
        } );

    }
}
