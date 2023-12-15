package com.triit.trinetv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.triit.trinetv2.adapter.HistoryModule;
import com.triit.trinetv2.adapter.UnitAdapter;
import com.triit.trinetv2.adapter.Validation;
import com.triit.trinetv2.model.Data;
import com.triit.trinetv2.model.Output;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String appVersion = "Version 3.6.4 Release 1";

    private Spinner spnInpUnit, spnOutUnit;
    private TextView tvOutput;
    private EditText edtValue;
    private RadioGroup rdgModeSelect;
    private RadioButton rdBitByte, rdByteBit;
    private Button btnCalc, btnClear;
    private ArrayList<String> inpUnit, outUnit;
    private ArrayAdapter<String> strBitUnit, strByteUnit;


    protected void setComponents() {
        rdgModeSelect = findViewById(R.id.rdgModeSelect);
        tvOutput = findViewById(R.id.tvOutput);
        rdBitByte = findViewById(R.id.rdBitByte);
        rdByteBit = findViewById(R.id.rdByteBit);
        btnCalc = findViewById(R.id.btnCalc);
        btnClear = findViewById(R.id.btnClear);
        //TODO: Set edtValue's default value
        edtValue = findViewById(R.id.edtValue);
        //TODO: Fill Spinners default values
        UnitAdapter.loadUnitBit();
        UnitAdapter.loadUnitByte();
        spnInpUnit = findViewById(R.id.spnInpUnit);
        spnOutUnit = findViewById(R.id.spnOutUnit);
        inpUnit = UnitAdapter.getStrBitList();
        outUnit = UnitAdapter.getStrByteList();
        strBitUnit = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, inpUnit);
        strByteUnit = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, outUnit);
        strBitUnit.
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        strByteUnit.
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnInpUnit.setAdapter(strBitUnit);
        spnOutUnit.setAdapter(strByteUnit);
    }
    protected void setActionCalc() {
        btnCalc.setOnClickListener(e -> {
            Output.clearOutput();
            if(Validation.isValidNumber(edtValue.getText().toString())) {
                int mode = 1;
                if(rdByteBit.isChecked())
                    mode = 0;
                String bitUnit = spnInpUnit.getSelectedItem().toString();
                String byteUnit = spnOutUnit.getSelectedItem().toString();

                int size = UnitAdapter.getDistance(mode,
                        UnitAdapter.findIndexBit(bitUnit), UnitAdapter.findIndexByte(byteUnit));
                Output.setOutput(UnitAdapter.
                        calculate(mode, size, Double.parseDouble(edtValue.getText().toString())));
                if(mode == 1) {
                    tvOutput.setText(String.format("%s %s", Output.getOutput(), byteUnit));
                } else {
                    tvOutput.setText(String.format("%s %s", Output.getOutput(), bitUnit));
                }
                HistoryModule.addHistory(new Data(Output.getOutput()));
            } else {
                Toast.makeText(this,
                        "Dữ liệu KHÔNG hợp lệ!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void setActionClear() {
        btnClear.setOnClickListener(e -> {
            edtValue.setText("");
            tvOutput.setText("Đang chờ nhập dữ liệu...");
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        int id = item.getItemId();
        switch(id) {
            case R.id.itemHistory: {
                i = new Intent(this, HistoryActivity.class);
                i.putExtra("App_Version", appVersion);
                startActivity(i);
                finish();
            } break;
            case R.id.itemVersion: {
                Toast.makeText(this, appVersion, Toast.LENGTH_SHORT).show();
            } break;
        }

        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponents();
        setActionClear();
        setActionCalc();
    }
}