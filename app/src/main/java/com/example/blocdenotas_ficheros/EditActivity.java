package com.example.blocdenotas_ficheros;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blocdenotas_ficheros.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class EditActivity extends AppCompatActivity {

    private String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        theme = intent.getStringExtra("theme");

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        EditText editText = findViewById(R.id.editText);

        toolbar.setTitle(theme);
        toolbar.setBackgroundColor(getResources().getColor(getThemeColor(theme)));

        String fileName = theme + ".txt";
        String content = readFromFile(fileName);
        editText.setText(content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            saveToFile();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveToFile() {
        try {
            String fileName = theme + ".txt";
            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
            EditText editText = findViewById(R.id.editText);
            String content = editText.getText().toString();
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

     private int getThemeColor(String theme) {
        // Devuelve el color asociado al tema
        return R.color.defaultColor; // Ejemplo de color para "Urgente"
    }
}
