package com.example.saveandread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd, btnDelete, btnRead;
    EditText etName;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnRead = findViewById(R.id.btnRead);
        etName = findViewById(R.id.etName);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()){
            case R.id.btnAdd:
                cv.put("name", name);
                db.insert("mytable", null, cv);

                break;
            case R.id.btnDelete:
                db.delete("mytable", null, null);
                break;
            case R.id.btnRead:
                Cursor c = db.query("mytable", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");

                    do {
                        Log.d("LOG_TAG",
                                "ID = " + c.getInt(idColIndex) +
                                        ", name = " + c.getString(nameColIndex));
                    } while (c.moveToNext());

                } else
                    Log.d("LOG_TAG", "0 rows");
                c.close();

                break;
            default:
                break;
        }
    }

}