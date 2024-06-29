package com.triit.trinetv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import com.triit.trinetv2.module.HistoryModule;
import com.triit.trinetv2.adapter.UnitAdapter;
import com.triit.trinetv2.models.Data;
import com.triit.trinetv2.models.Output;
import com.triit.trinetv2.module.Validation;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String appVersion = "Version 3.6.11 Release 3 \n[Hotfix 1]";

    private Spinner spnInpUnit, spnOutUnit;
    private TextView tvOutput;
    private EditText edtValue;
    private RadioGroup rdgModeSelect;
    private RadioButton rdBitByte, rdByteBit;
    private Button btnCalc, btnClear;
    private ArrayList<String> inpUnit, outUnit;
    private ArrayAdapter<String> strBitUnit, strByteUnit;

    private Data data = new Data();
    private String input = "";

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
    @SuppressLint("DefaultLocale")
    protected void setActionCalc() {
        btnCalc.setOnClickListener(e -> {
            Output.clearOutput();
            input = edtValue.getText().toString();
            if(Validation.isValidNumber(input)) {
                data.setData(Double.parseDouble(input));
                int mode = 1;
                if(rdByteBit.isChecked())
                    mode = 0;
                String bitUnit = spnInpUnit.getSelectedItem().toString();
                String byteUnit = spnOutUnit.getSelectedItem().toString();
                // Get size of 2 units
                int size = UnitAdapter.getDistance(mode,
                        UnitAdapter.findIndexBit(bitUnit), UnitAdapter.findIndexByte(byteUnit));
                // Get user input data
                double input = Double.parseDouble(edtValue.getText().toString());
                // Set output to the static object "Output"
                Output.setOutput(UnitAdapter.calculate(mode, size, input));
                // Display converted value to TextView
                if(mode == 1) {
                    tvOutput.setText(String.format("%.2f %s", Output.getOutput(), byteUnit));
                } else {
                    tvOutput.setText(String.format("%.2f %s", Output.getOutput(), bitUnit));
                }
                // Add converted value to RecyclerView
                HistoryModule.addHistory(new Data(Output.getOutput()));
            } else {
                // Things happened if input validation is failed
                Toast.makeText(this,
                        "Dữ liệu KHÔNG hợp lệ!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @SuppressLint("SetTextI18n")
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
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        int id = item.getItemId();
        switch(id) {
            case R.id.itemHistory: {
                if(HistoryModule.getItemCount() <= 0) {
                    Toast.makeText(this,
                            "Chưa có dữ liệu tính toán!", Toast.LENGTH_SHORT).show();

                    return false;
                }
                else {
                    i = new Intent(this, HistoryActivity.class);
                    i.putExtra("App_Version", appVersion);
                    startActivity(i);
                    finish();
                }
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
