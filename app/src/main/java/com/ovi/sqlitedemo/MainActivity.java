package com.ovi.sqlitedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id,name,phnNum;
    Button add,view,delete,updateid;
    DatabaseSqlite myDb;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateAll();
        myDb=new DatabaseSqlite(this);//database class
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
                clear();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewData();
                clear();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.delete();
                clear();

            }
        });
        updateid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                clear();
            }
        });
    }




    public void ViewData() {
        textView.setText("");
        Cursor res=myDb.getAllData();
        if(res.getCount() == 0) {
            textView.setText("No Data Found");
            return;
        }

        while (res.moveToNext()){
            textView.append("ID:"+res.getString(0)+"\n");
            textView.append("Name:"+res.getString(1)+"\n");
            textView.append("Phone Number:"+res.getString(2)+"\n\n");

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void AddData() {
        boolean isInsert=(myDb.insertData(name.getText().toString(),phnNum.getText().toString()));
        if (isInsert=true){
            Toast.makeText(MainActivity.this,"Data insert successfull",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this,"Data Unsuccessfull",Toast.LENGTH_SHORT).show();
        }

    } public void update() {
        boolean isInsert=(myDb.update(id.getText().toString(),name.getText().toString(),phnNum.getText().toString()));
        if (isInsert=true){
            Toast.makeText(MainActivity.this,"Data Update successfull",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this,"Data update unsuccessfull",Toast.LENGTH_SHORT).show();
        }

    }

    public void initiateAll(){
        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        phnNum=findViewById(R.id.PhoneNumber);
        add=findViewById(R.id.addButton);
        view=findViewById(R.id.View);
        textView=findViewById(R.id.textView);
        delete=findViewById(R.id.delete);
        updateid=findViewById(R.id.updateid);
    }
    public void clear(){
        id.setText("");
        name.setText("");
        phnNum.setText("");
    }

}
