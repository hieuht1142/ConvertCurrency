package com.example.convertcurrency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerFrom;
    Spinner spinnerTo;
    EditText edtAmount;
    TextView txtResult;
    double ctr1, ctr2;

    HashMap<String, Double> country = new HashMap<>();
    List<String> listCountry = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCountry();

        listCountry = new ArrayList<>();
        Iterator<String> itr = country.keySet().iterator();
        while (itr.hasNext()) {
            listCountry.add(itr.next());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.my_spinner, R.id.my_txt, listCountry);

        spinnerFrom = findViewById(R.id.spinner_from);
        spinnerTo = findViewById(R.id.spinner_to);
        edtAmount = findViewById(R.id.edt_amount);
        txtResult = findViewById(R.id.txt_result);

        spinnerFrom.setAdapter(adapter);
        spinnerFrom.setSelection(8);
        spinnerTo.setAdapter(adapter);
        spinnerTo.setSelection(4);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = listCountry.get(position);
                ctr1 = country.get(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = listCountry.get(position);
                ctr2 = country.get(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String data = edtAmount.getText().toString();
                if (!data.equals("")) {
                    double rs = Double.parseDouble(data) * (ctr2 / ctr1);
                    txtResult.setText(String.format("%.6f", rs));
                }

            }
        });

    }

    private void initCountry() {
        country.put("US Dollar USD", 1.0);
        country.put("Vietnamese Dong VND", 23186.64);
        country.put("Euro EUR", 0.843283);
        country.put("Australian Dollar AUD", 1.40692);
        country.put("South Korean Won KRW", 1132.35);
        country.put("Japanese Yen YEN", 104.558);
        country.put("Chinese Yuan Renminbi CNY", 6.65345);
        country.put("Thai Baht THB", 31.2246);
        country.put("Philippine Peso PHP", 48.5015);
        country.put("Singapore Dollar SGD", 1.53443);
    }
}