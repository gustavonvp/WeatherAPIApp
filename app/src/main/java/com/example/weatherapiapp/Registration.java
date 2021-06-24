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


public class Registration extends AppCompatActivity {
    Button _btnreg;
    EditText  et_username, et_pass1, et_pass2;

    DBHelper2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );

        db = new DBHelper2( this );

        _btnreg = (Button) findViewById( R.id.btnreg);
        et_username = (EditText)findViewById( R.id.Username );
        et_pass1 = (EditText)findViewById( R.id.Password );
        et_pass2 = (EditText)findViewById( R.id.rPassword );

        _btnreg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String p1 = et_pass1.getText().toString();
                String p2 = et_pass2.getText().toString();

                if(username.equals( "" )){
                    Toast.makeText( Registration.this, "Username não inserido, tente novamente",Toast.LENGTH_SHORT ).show();
                }else if (p1.equals( "" ) || p2.equals( "" )) {
                    Toast.makeText( Registration.this, "Password não preenchido, tente novamente" , Toast.LENGTH_SHORT).show();
                }else if (!p1.equals( p2 )){
                    Toast.makeText( Registration.this, "As duas password não correspondem, tente novamente", Toast.LENGTH_SHORT ).show();
                }else {
                   Boolean res = db.CriarUtilizador( username, p1 );
                   if(res == true) {
                       Toast.makeText( Registration.this, "Registro OK", Toast.LENGTH_SHORT ).show();
                   }else {
                       Toast.makeText( Registration.this, "Registro inválido, tente novamente", Toast.LENGTH_SHORT ).show();
                   }
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
