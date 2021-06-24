package com.example.weatherapiapp;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends  AppCompatActivity {
    EditText name, contact, dob, email;
    Button insert, update, delete, view;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        name = findViewById( R.id.name );
        contact = findViewById( R.id.contact );
        dob = findViewById( R.id.dob );
        email = findViewById( R.id.email );


        insert = findViewById( R.id.btnInsert );
        update = findViewById( R.id.btnUpdate );
        delete = findViewById( R.id.btnDelete );
        view = findViewById( R.id.btnView );
        DB = new DBHelper( this );

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

        insert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = contact.getText().toString();
                String emailTXT = email.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata( nameTXT, contactTXT, dobTXT, emailTXT );

                if(nameTXT.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Name not inserted", Toast.LENGTH_SHORT).show();
                }

                if(contactTXT.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Contact not inserted", Toast.LENGTH_SHORT).show();
                }

                if(dobTXT.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Dob not inserted", Toast.LENGTH_SHORT).show();
                }

                if(emailTXT.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email not inserted", Toast.LENGTH_SHORT).show();
                }

                if(checkinsertdata == true)
                    Toast.makeText(LoginActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText( LoginActivity.this, "", Toast.LENGTH_SHORT ).show();
            }
        } );


        update.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = contact.getText().toString();
                String emailTXT = email.getText().toString();

                Boolean checkupdateddata = DB.updateuserdata( nameTXT, contactTXT, dobTXT , emailTXT  );

                if(checkupdateddata == true)
                    Toast.makeText(LoginActivity.this, " Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText( LoginActivity.this, " New Entry Not Updated", Toast.LENGTH_SHORT ).show();
            }
        } );

        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();

                Boolean checkdeleteddata = DB.deletedata( nameTXT );

                if(checkdeleteddata == true)
                    Toast.makeText(LoginActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText( LoginActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT ).show();
            }
        } );

        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount() == 0) {
                    Toast.makeText( LoginActivity.this, "No Entry Exists", Toast.LENGTH_SHORT ).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append( "Name : "+res.getString( 0 )+"\n" );
                    buffer.append( "Contact : "+res.getString( 1 )+"\n" );
                    buffer.append( "Date of Birth : "+res.getString( 2)+"\n\n" );
                    buffer.append( "Email adress : "+res.getString( 3 ) + "\n\n\n" );
                }

                AlertDialog.Builder builder = new AlertDialog.Builder( LoginActivity.this );
                builder.setCancelable( true );
                builder.setTitle( "User Entries" );
                builder.setMessage( buffer.toString() );
                builder.show();


            }
        } );




    }

}
