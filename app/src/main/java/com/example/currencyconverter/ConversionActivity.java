package com.example.currencyconverter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConversionActivity extends AppCompatActivity {
    private Button btnReturn, btnConvFrmUSD, btnConvToUSD;
    private TextView txtSelectedCurrency;
    private EditText txtDcmlUSD, txtDcmlCurrncy;

    private final double CAD_RATE = 1.26;
    private final double YEN_RATE = 109.94;
    private final double EUR_RATE = 0.85;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        // Initialize views
        txtSelectedCurrency = findViewById(R.id.txtSelectedCurrency);
        btnReturn = findViewById(R.id.btnReturn);
        btnConvFrmUSD = findViewById(R.id.btnconvFrmUSD);
        btnConvToUSD = findViewById(R.id.btnconvToUSD);
        txtDcmlUSD = findViewById(R.id.txtDcmlUSD);
        txtDcmlCurrncy = findViewById(R.id.txtDcmlCurrncy);

        // Get selected currency
        String selectedCurrency = getIntent().getStringExtra("SELECTED_CURRENCY");
        txtSelectedCurrency.setText("Converting to: " + selectedCurrency);

        // Set up button listeners
        btnReturn.setOnClickListener(v -> finish());

        btnConvFrmUSD.setOnClickListener(v -> convertFromUSD(selectedCurrency));

        btnConvToUSD.setOnClickListener(v -> convertToUSD(selectedCurrency));
    }

    private void convertFromUSD(String currency) {
        double usdAmount = Double.parseDouble(txtDcmlUSD.getText().toString());
        double convertedAmount;
        String symbol = "";

        switch(currency) {
            case "CAD":
                convertedAmount = usdAmount * CAD_RATE;
                symbol = "CA$";
                break;
            case "YEN":
                convertedAmount = usdAmount * YEN_RATE;
                symbol = "¥";
                break;
            case "EUR":
                convertedAmount = usdAmount * EUR_RATE;
                symbol = "€";
                break;
            default:
                convertedAmount = 0;
        }

        txtDcmlCurrncy.setText(String.format("%.2f %s", convertedAmount, symbol));
    }

    private void convertToUSD(String currency) {
        String input = txtDcmlCurrncy.getText().toString().replaceAll("[^\\d.]", "");
        double foreignAmount = Double.parseDouble(input);
        double convertedAmount;

        switch(currency) {
            case "CAD":
                convertedAmount = foreignAmount / CAD_RATE;
                break;
            case "YEN":
                convertedAmount = foreignAmount / YEN_RATE;
                break;
            case "EUR":
                convertedAmount = foreignAmount / EUR_RATE;
                break;
            default:
                convertedAmount = 0;
        }

        txtDcmlUSD.setText(String.format("$%.2f", convertedAmount));
    }
}