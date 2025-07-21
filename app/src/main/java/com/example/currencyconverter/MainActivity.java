package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnNxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        RadioButton rdbCAD = findViewById(R.id.rdbCAD);
        RadioButton rdbYEN = findViewById(R.id.rdbYEN);
        RadioButton rdbEUR = findViewById(R.id.rdbEUR);
        btnNxt= findViewById(R.id.btnNxt);

        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCurrency = "NONE";
                if (rdbEUR.isChecked()) {
                    selectedCurrency = "EUR";
                } else if (rdbCAD.isChecked()) {
                    selectedCurrency = "CAD";
                } else if (rdbYEN.isChecked()) {
                    selectedCurrency = "YEN";
                }
                Intent intent = new Intent(MainActivity.this, ConversionActivity.class);
                intent.putExtra("SELECTED_CURRENCY", selectedCurrency);
                        startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}