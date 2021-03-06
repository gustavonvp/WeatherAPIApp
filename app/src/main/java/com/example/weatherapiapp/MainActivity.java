package com.example.weatherapiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class    MainActivity extends AppCompatActivity {
//    SQLiteOpenHelper openHelper;
//    SQLiteDatabase db;

    Button _btnlogin, _btnreg;
//    EditText _txtEmail, _txtPass;
//    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        _btnlogin=(Button)findViewById( R.id.btnlogin );
        _btnreg = (Button) findViewById( R.id.btnregister);

        _btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AuthLoginPage.class);
                startActivity( i );
            }
        } );

        _btnreg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Registration.class);
                startActivity( i );
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


