package com.example.blocdenotas_ficheros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        private String[] themes = {"Urgente", "Viajes", "Conciertos", "Familia", "Amigos", "Deportes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout themesLayout = findViewById(R.id.themesLayout);

        for (String theme : themes) {
            addThemeToLayout(themesLayout, theme);
        }
    }

    private void addThemeToLayout(LinearLayout themesLayout, final String theme) {
        // Crear dinámicamente la vista para cada tema
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(getThemeIconResource(theme));
        imageView.setOnClickListener(v -> openEditScreen(theme));

        TextView textView = new TextView(this);
        textView.setText(theme);

        LinearLayout themeContainer = new LinearLayout(this);
        themeContainer.setOrientation(LinearLayout.VERTICAL);
        themeContainer.addView(imageView);
        themeContainer.addView(textView);

        themesLayout.addView(themeContainer);
    }

    private void openEditScreen(String theme) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("theme", theme);
        startActivity(intent);
    }

    private int getThemeIconResource(String theme) {
        switch (theme) {
            case "Urgente":
                return R.drawable.ic_urgent;
            case "Viajes":
                return R.drawable.ic_travel;
            case "Conciertos":
                return R.drawable.ic_concert;
            case "Familia":
                return R.drawable.ic_family;
            case "Amigos":
                return R.drawable.ic_friends;
            case "Deportes":
                return R.drawable.ic_sports;
            default:
                return R.drawable.ic_launcher_foreground;
        }
    }
}
