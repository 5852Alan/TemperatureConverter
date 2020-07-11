package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Vars
    private TextView result;
    private EditText enterVal;
    int inputtedInt = 0;
    double resultInt = 0;
    int unitpos = 0;
    int val1= 0;
    int val2= 0;
    ArrayAdapter<CharSequence> adapterChosen;
    //Spinners
    Spinner spinner1;
    Spinner spinner2;


    //Toast echo
    void Toaster (CharSequence msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinners defined;
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        //for choosing units
        Spinner spinnerUnit = findViewById(R.id.spinnerunit);

        //spinnerUnit
        ArrayAdapter<CharSequence> adapterUnit = ArrayAdapter.createFromResource(this, R.array.totalunits, android.R.layout.simple_spinner_item);
        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapterUnit);

        //set up
        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Temperature")){//Temperature
                    adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.temperatureunits, android.R.layout.simple_spinner_item);
                    spinner1.setAdapter(adapterChosen);
                    spinner2.setAdapter(adapterChosen);
                    unitpos = 0;
                    Toaster("Temperature selected");
                }
                if (parent.getItemAtPosition(position).equals("Length")) { //Length
                    adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.lengthunits, android.R.layout.simple_spinner_item);
                    spinner1.setAdapter(adapterChosen);
                    spinner2.setAdapter(adapterChosen);
                    unitpos = 1;
                    Toaster("Length selected");
                }
                if (parent.getItemAtPosition(position).equals("Area")) {
                    adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.areaunits, android.R.layout.simple_spinner_item);
                    spinner1.setAdapter(adapterChosen);
                    spinner2.setAdapter(adapterChosen);
                    unitpos = 2;
                    Toaster("Area selected");
                }
                if (parent.getItemAtPosition(position).equals("Weight")) {
                    adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.weightunits, android.R.layout.simple_spinner_item);
                    spinner1.setAdapter(adapterChosen);
                    spinner2.setAdapter(adapterChosen);
                    unitpos = 3;
                    Toaster("Weight selected");
                }
                if (parent.getItemAtPosition(position).equals("Speed/Velocity")){
                    adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.velocityunits, android.R.layout.simple_spinner_item);
                    spinner1.setAdapter(adapterChosen);
                    spinner2.setAdapter(adapterChosen);
                    unitpos = 4;
                    Toaster("Speed/Velocity selected");
                }
                if (parent.getItemAtPosition(position).equals("Time")){
                    adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.timeunits, android.R.layout.simple_spinner_item);
                    spinner1.setAdapter(adapterChosen);
                    spinner2.setAdapter(adapterChosen);
                    unitpos = 5;
                    Toaster("Time selected");
                }
                adapterChosen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapterChosen.notifyDataSetChanged();
                //String text = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
                val1 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nothing selected
            }
            });


        //spinner2.setAdapter(adapterChosen);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
                val2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nothing selected
            }
        });

        //Vars
        result = findViewById(R.id.result);
        enterVal = findViewById(R.id.enterVal);
        //Button
        Button conversion = findViewById(R.id.conversion);

        //Actual conversions
        conversion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(enterVal.getText().toString().trim().length() > 0){
                    String inputtedString = enterVal.getText().toString();
                    inputtedInt = Integer.parseInt(inputtedString);
                    resultInt=0;
                    //positions
                    if(unitpos == 0) { //temp
                        switch (val1) {
                            case 0://fahrenheit
                                switch (val2) {
                                    case 0: //fahrenheit
                                        resultInt = inputtedInt;
                                        Toaster(resultInt + " F* = " + inputtedInt + " F*.");
                                        break;
                                    case 1: //celsius
                                        resultInt = (double) (inputtedInt - 32) * 5 / 9;
                                        Toaster(resultInt + " C* = " + inputtedInt + " F*.");
                                        break;
                                    case 2: //kelvin
                                        resultInt = (resultInt = (double) (inputtedInt - 32) * 5 / 9) + 273.15;
                                        Toaster(resultInt + " K* = " + inputtedInt + " F*.");
                                        break;
                                }
                                break;
                            case 1:   //celsius
                                switch (val2) {
                                    case 0: //fahrenheit
                                        resultInt =  (double) inputtedInt * 1.8 + 32;
                                        Toaster(resultInt + " F* = " + inputtedInt + " C*.");
                                        break;
                                    case 1: //celsius
                                        resultInt = inputtedInt;
                                        Toaster(resultInt + " C* = " + inputtedInt + " C*.");
                                        break;
                                    case 2: //kelvin
                                        resultInt = inputtedInt + 273.15;
                                        Toaster(resultInt + " K = " + inputtedInt + " C*.");
                                        break;
                                }
                                break;
                            case 2:    //kelvin
                                switch (val2) {
                                    case 0: //fahrenheit
                                        resultInt = (inputtedInt - 273.15) * 1.8 + 32;
                                        Toaster(resultInt + " F* = " + inputtedInt + " K.");
                                        break;
                                    case 1: //celsius
                                        resultInt = inputtedInt - 273.15;
                                        Toaster(resultInt + " C* = " + inputtedInt + " K.");
                                        break;
                                    case 2: //kelvin
                                        resultInt = inputtedInt;
                                        Toaster(resultInt + " K = " + inputtedInt + " K.");
                                        break;
                                }
                                break;
                        }
                    }
                    else if (unitpos == 1){ //length
                        switch (val1) {
                            case 0://feet
                                switch (val2) {
                                    case 0: //feet
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " ft = " + inputtedInt + " ft.");
                                        break;
                                    case 1: //mile
                                        resultInt = (double) inputtedInt / 5280;
                                        Toaster(resultInt + " mi = " + inputtedInt + " ft.");
                                        break;
                                    case 2:  //yard
                                        resultInt = (double) inputtedInt / 3;
                                        Toaster(resultInt + " yd = " + inputtedInt + " ft.");
                                        break;
                                    case 3: //inch
                                        resultInt = (double) inputtedInt * 12;
                                        Toaster(resultInt + " in = " + inputtedInt + " ft.");
                                        break;
                                    case 4: //meters
                                        resultInt = (double) inputtedInt / 3.280839895;
                                        Toaster(resultInt + " m  = " + inputtedInt + " ft.");
                                        break;
                                    case 5: //km
                                        resultInt = (double) inputtedInt / 3280.839895;
                                        Toaster(resultInt + " km = " + inputtedInt + " ft.");
                                        break;
                                }
                                break;
                            case 1: //mile
                                switch (val2) {
                                    case 0: //feet
                                        resultInt = (double) inputtedInt * 5280;
                                        Toaster(resultInt + " ft = " + inputtedInt + " mi.");
                                        break;
                                    case 1: //mile
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " mi = " + inputtedInt + " mi.");
                                        break;
                                    case 2: //yard
                                        resultInt = (double) inputtedInt * 1760;
                                        Toaster(resultInt + " yd = " + inputtedInt + " mi.");
                                        break;
                                    case 3: //inch
                                        resultInt = (double) inputtedInt * 63360;
                                        Toaster(resultInt + " in = " + inputtedInt + " mi.");
                                        break;
                                    case 4: //meters
                                        resultInt = (double) inputtedInt * 1609.344;
                                        Toaster(resultInt + " m  = " + inputtedInt + " mi.");
                                        break;
                                    case 5:  //km
                                        resultInt = (double) inputtedInt * 1.609344;
                                        Toaster(resultInt + " km = " + inputtedInt + " mi.");
                                        break;
                                }
                                break;
                            case 2: //yard
                                switch (val2) {
                                    case 0: //feet
                                        resultInt = (double) inputtedInt * 3;
                                        Toaster(resultInt + " ft = " + inputtedInt + " yd.");
                                        break;
                                    case 1: //mile
                                        resultInt = (double) inputtedInt / 1760;
                                        Toaster(resultInt + " mi = " + inputtedInt + " yd.");
                                        break;
                                    case 2: //yard
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " yd = " + inputtedInt + " yd.");
                                        break;
                                    case 3: //inch
                                        resultInt = (double) inputtedInt * 36;
                                        Toaster(resultInt + " in = " + inputtedInt + " yd.");
                                        break;
                                    case 4: //meters
                                        resultInt = (double) inputtedInt / 1.0936133;
                                        Toaster(resultInt + " m  = " + inputtedInt + " yd.");
                                        break;
                                    case 5: //km
                                        resultInt = (double) inputtedInt / 1093.6133;
                                        Toaster(resultInt + " km = " + inputtedInt + " yd.");
                                        break;
                                }
                                break;
                            case 3: //inch
                                switch (val2) {
                                    case 0: //feet
                                        resultInt = (double) inputtedInt / 12;
                                        Toaster(resultInt + " ft = " + inputtedInt + " in.");
                                        break;
                                    case 1: //mile
                                        resultInt = (double) inputtedInt / 63360;
                                        Toaster(resultInt + " mi = " + inputtedInt + " in.");
                                        break;
                                    case 2: //yard
                                        resultInt = (double) inputtedInt / 36;
                                        Toaster(resultInt + " yd = " + inputtedInt + " in.");
                                        break;
                                    case 3: //inch
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " in = " + inputtedInt + " in.");
                                        break;
                                    case 4:  //meters
                                        resultInt = (double) inputtedInt / 39.3700787;
                                        Toaster(resultInt + " m  = " + inputtedInt + " in.");
                                        break;
                                    case 5: //km
                                        resultInt = (double) inputtedInt / 39370.0787;
                                        Toaster(resultInt + " km = " + inputtedInt + " in.");
                                        break;
                                }
                                break;
                            case 4: //meters
                                switch (val2) {
                                    case 0: //feet
                                        resultInt = (double) inputtedInt * 3.28084;
                                        Toaster(resultInt + " ft = " + inputtedInt + " m.");
                                        break;
                                    case 1: //mile
                                        resultInt = (double) inputtedInt / 1609.34395;
                                        Toaster(resultInt + " mi = " + inputtedInt + " m.");
                                        break;
                                    case 2: //yard
                                        resultInt = (double) inputtedInt * 1.09361;
                                        Toaster(resultInt + " yd = " + inputtedInt + " m.");
                                        break;
                                    case 3: //inch
                                        resultInt = (double) inputtedInt * 39.3701;
                                        Toaster(resultInt + " in = " + inputtedInt + " m.");
                                        break;
                                    case 4: //meters
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " m  = " + inputtedInt + " m.");
                                        break;
                                    case 5: //km
                                        resultInt = (double) inputtedInt / 1000;
                                        Toaster(resultInt + " km = " + inputtedInt + " m.");
                                        break;
                                }
                                break;
                            case 5: //km
                                switch (val2) {
                                    case 0: //feet
                                        resultInt = (double) inputtedInt * 3280.84;
                                        Toaster(resultInt + " ft = " + inputtedInt + " km.");
                                        break;
                                    case 1: //mile
                                        resultInt = (double) inputtedInt / 1.60934395;
                                        Toaster(resultInt + " mi = " + inputtedInt + " km.");
                                        break;
                                    case 2: //yard
                                        resultInt = (double) inputtedInt * 1093.61;
                                        Toaster(resultInt + " yd = " + inputtedInt + " km.");
                                        break;
                                    case 3: //inch
                                        resultInt = (double) inputtedInt * 39370.1;
                                        Toaster(resultInt + " in = " + inputtedInt + " km.");
                                        break;
                                    case 4: //meters
                                        resultInt = (double) inputtedInt * 1000;
                                        Toaster(resultInt + " m  = " + inputtedInt + " km.");
                                        break;
                                    case 5: //km
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " km = " + inputtedInt + " km.");
                                        break;
                                }
                                break;
                        }
                    }
                    else if (unitpos == 2){ //area
                        switch (val1){
                            case 0: //sq. inch
                                switch (val2) {
                                    case 0: //sq. inch
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " sq. inch = " + inputtedInt + " sq. inch.");
                                        break;
                                    case 1: //sq. foot
                                        resultInt = (double) inputtedInt / 144;
                                        Toaster(resultInt + " sq. foot = " + inputtedInt + " sq. inch.");
                                        break;
                                    case 2: //sq. yard
                                        resultInt = (double) inputtedInt / 1296;
                                        Toaster(resultInt + " sq. yard = " + inputtedInt + " sq. inch.");
                                        break;
                                    case 3: //sq. mile
                                        resultInt = (double) inputtedInt / 4014000000L;
                                        Toaster(resultInt + " sq. mile = " + inputtedInt + " sq. inch.");
                                        break;
                                    case 4: //sq. meter
                                        resultInt = (double) inputtedInt / 1550;
                                        Toaster(resultInt + " sq. meter = " + inputtedInt + " sq. inch.");
                                        break;
                                    case 5: //sq. kilometer
                                        resultInt = (double) inputtedInt / 1550000000L;
                                        Toaster(resultInt + " sq. kilometer = " + inputtedInt + " sq. inch.");
                                        break;
                                    case 6: //acre
                                        resultInt = (double) inputtedInt / 6273000;
                                        Toaster(resultInt + " acre = " + inputtedInt + " sq. inch.");
                                        break;
                                    case 7: //hectare
                                        resultInt = (double) inputtedInt / 15500000;
                                        Toaster(resultInt + " hectare = " + inputtedInt + " sq. inch.");
                                        break;
                                }
                                break;
                            case 1: //sq. foot
                                switch (val2) {
                                    case 0: //sq. inch
                                        resultInt = (double) inputtedInt * 144;
                                        Toaster(resultInt + " sq. inch = " + inputtedInt + " sq. foot.");
                                        break;
                                    case 1: //sq. foot
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " sq. foot = " + inputtedInt + " sq. foot.");
                                        break;
                                    case 2: //sq. yard
                                        resultInt = (double) inputtedInt / 9;
                                        Toaster(resultInt + " sq. yard = " + inputtedInt + " sq. foot.");
                                        break;
                                    case 3: //sq. mile
                                        resultInt = (double) inputtedInt / 27880000;
                                        Toaster(resultInt + " sq. mile = " + inputtedInt + " sq. foot.");
                                        break;
                                    case 4: //sq. meter
                                        resultInt = (double) inputtedInt / 10.764;
                                        Toaster(resultInt + " sq. meter = " + inputtedInt + " sq. foot.");
                                        break;
                                    case 5: //sq. kilometer
                                        resultInt = (double) inputtedInt / 10764000;
                                        Toaster(resultInt + " sq. kilometer = " + inputtedInt + " sq. foot.");
                                        break;
                                    case 6: //acre
                                        resultInt = (double) inputtedInt / 43560;
                                        Toaster(resultInt + " acre = " + inputtedInt + " sq. foot.");
                                        break;
                                    case 7: //hectare
                                        resultInt = (double) inputtedInt / 107639;
                                        Toaster(resultInt + " hectare = " + inputtedInt + " sq. foot.");
                                        break;
                                }
                                break;
                            case 2: //sq. yard
                                switch (val2) {
                                    case 0: //sq. inch
                                        resultInt = (double) inputtedInt*1296;
                                        Toaster(resultInt + " sq. inch = " + inputtedInt + " sq. yard.");
                                        break;
                                    case 1: //sq. foot
                                        resultInt = (double) inputtedInt*9;
                                        Toaster(resultInt + " sq. foot = " + inputtedInt + " sq. yard.");
                                        break;
                                    case 2: //sq. yard
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " sq. yard = " + inputtedInt + " sq. yard.");
                                        break;
                                    case 3: //sq. mile
                                        resultInt = (double) inputtedInt / 3098000;
                                        Toaster(resultInt + " sq. mile = " + inputtedInt + " sq. yard.");
                                        break;
                                    case 4: //sq. meter
                                        resultInt = (double) inputtedInt / 1.196;
                                        Toaster(resultInt + " sq. meter = " + inputtedInt + " sq. yard.");
                                        break;
                                    case 5: //sq. kilometer
                                        resultInt = (double) inputtedInt / 1196000;
                                        Toaster(resultInt + " sq. kilometer = " + inputtedInt + " sq. yard.");
                                        break;
                                    case 6: //acre
                                        resultInt = (double) inputtedInt / 4840;
                                        Toaster(resultInt + " acre = " + inputtedInt + " sq. yard.");
                                        break;
                                    case 7: //hectare
                                        resultInt = (double) inputtedInt / 11960;
                                        Toaster(resultInt + " hectare = " + inputtedInt + " sq. yard.");
                                        break;
                                }
                                break;
                            case 3: //sq. mile
                                switch (val2) {
                                    case 0: //sq. inch
                                        resultInt = (double) inputtedInt * 4014000000L;
                                        Toaster(resultInt + " sq. inch = " + inputtedInt + " sq. mile.");
                                        break;
                                    case 1: //sq. foot
                                        resultInt = (double) inputtedInt * 27880000;
                                        Toaster(resultInt + " sq. foot = " + inputtedInt + " sq. mile.");
                                        break;
                                    case 2: //sq. yard
                                        resultInt = (double) inputtedInt * 3098000;
                                        Toaster(resultInt + " sq. yard = " + inputtedInt + " sq. mile.");
                                        break;
                                    case 3: //sq. mile
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " sq. mile = " + inputtedInt + " sq. mile.");
                                        break;
                                    case 4: //sq. meter
                                        resultInt = (double) inputtedInt * 2589999;
                                        Toaster(resultInt + " sq. meter = " + inputtedInt + " sq. mile.");
                                        break;
                                    case 5: //sq. kilometer
                                        resultInt = (double) inputtedInt * 2.58999;
                                        Toaster(resultInt + " sq. kilometer = " + inputtedInt + " sq. mile.");
                                        break;
                                    case 6: //acre
                                        resultInt = (double) inputtedInt * 640;
                                        Toaster(resultInt + " acre = " + inputtedInt + " sq. mile.");
                                        break;
                                    case 7: //hectare
                                        resultInt = (double) inputtedInt * 258.9999;
                                        Toaster(resultInt + " hectare = " + inputtedInt + " sq. mile.");
                                        break;
                                }
                                break;
                            case 4: //sq. meter
                                switch (val2) {
                                    case 0: //sq. inch
                                        resultInt = (double) inputtedInt * 1550;
                                        Toaster(resultInt + " sq. inch = " + inputtedInt + " sq. meter.");
                                        break;
                                    case 1: //sq. foot
                                        resultInt = (double) inputtedInt * 10.764;
                                        Toaster(resultInt + " sq. foot = " + inputtedInt + " sq. meter.");
                                        break;
                                    case 2: //sq. yard
                                        resultInt = (double) inputtedInt * 1.196;
                                        Toaster(resultInt + " sq. yard = " + inputtedInt + " sq. meter.");
                                        break;
                                    case 3: //sq. mile
                                        resultInt = (double) inputtedInt / 2590000;
                                        Toaster(resultInt + " sq. mile = " + inputtedInt + " sq. meter.");
                                        break;
                                    case 4: //sq. meter
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " sq. meter = " + inputtedInt + " sq. meter.");
                                        break;
                                    case 5: //sq. kilometer
                                        resultInt = (double) inputtedInt / 1000000;
                                        Toaster(resultInt + " sq. kilometer = " + inputtedInt + " sq. meter.");
                                        break;
                                    case 6: //acre
                                        resultInt = (double) inputtedInt/4047;
                                        Toaster(resultInt + " acre = " + inputtedInt + " sq. meter.");
                                        break;
                                    case 7: //hectare
                                        resultInt = (double) inputtedInt / 10000;
                                        Toaster(resultInt + " hectare = " + inputtedInt + " sq. meter.");
                                        break;
                                }
                                break;
                            case 5: //sq. km
                                switch (val2) {
                                    case 0: //sq. inch
                                        resultInt = (double) inputtedInt * 1550;
                                        Toaster(resultInt + " sq. inch = " + inputtedInt + " sq. kilometer.");
                                        break;
                                    case 1: //sq. foot
                                        resultInt = (double) inputtedInt * 10764000;
                                        Toaster(resultInt + " sq. foot = " + inputtedInt + " sq. kilometer.");
                                        break;
                                    case 2: //sq. yard
                                        resultInt = (double) inputtedInt * 1196000;
                                        Toaster(resultInt + " sq. yard = " + inputtedInt + " sq. kilometer.");
                                        break;
                                    case 3: //sq. mile
                                        resultInt = (double) inputtedInt / 2.59;
                                        Toaster(resultInt + " sq. mile = " + inputtedInt + " sq. kilometer.");
                                        break;
                                    case 4: //sq. meter
                                        resultInt = (double) inputtedInt * 1000000;
                                        Toaster(resultInt + " sq. meter = " + inputtedInt + " sq. kilometer.");
                                        break;
                                    case 5: //sq. kilometer
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " sq. kilometer = " + inputtedInt + " sq. kilometer.");
                                        break;
                                    case 6: //acre
                                        resultInt = (double) inputtedInt * 247.105;
                                        Toaster(resultInt + " acre = " + inputtedInt + " sq. kilometer.");
                                        break;
                                    case 7: //hectare
                                        resultInt = (double) inputtedInt * 100;
                                        Toaster(resultInt + " hectare = " + inputtedInt + " sq. kilometer.");
                                        break;
                                }
                                break;
                            case 6: //acre
                                switch (val2) {
                                    case 0: //sq. inch
                                        resultInt = (double) inputtedInt * 6273000;
                                        Toaster(resultInt + " sq. inch = " + inputtedInt + " acre.");
                                        break;
                                    case 1: //sq. foot
                                        resultInt = (double) inputtedInt * 43560;
                                        Toaster(resultInt + " sq. foot = " + inputtedInt + " acre.");
                                        break;
                                    case 2: //sq. yard
                                        resultInt = (double) inputtedInt * 4840;
                                        Toaster(resultInt + " sq. yard = " + inputtedInt + " acre.");
                                        break;
                                    case 3: //sq. mile
                                        resultInt = (double) inputtedInt / 640;
                                        Toaster(resultInt + " sq. mile = " + inputtedInt + " acre.");
                                        break;
                                    case 4: //sq. meter
                                        resultInt = (double) inputtedInt * 4046.86;
                                        Toaster(resultInt + " sq. meter = " + inputtedInt + " acre.");
                                        break;
                                    case 5: //sq. kilometer
                                        resultInt = (double) inputtedInt / 247.105381;
                                        Toaster(resultInt + " sq. kilometer = " + inputtedInt + " acre.");
                                        break;
                                    case 6: //acre
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " acre = " + inputtedInt + " acre.");
                                        break;
                                    case 7: //hectare
                                        resultInt = (double) inputtedInt * 0.404686;
                                        Toaster(resultInt + " hectare = " + inputtedInt + " acre.");
                                        break;
                                }
                                break;
                            case  7: //hectare
                                switch (val2) {
                                    case 0: //sq. inch
                                        resultInt = (double) inputtedInt * 15500000;
                                        Toaster(resultInt + " sq. inch = " + inputtedInt + " hectare.");
                                        break;
                                    case 1: //sq. foot
                                        resultInt = (double) inputtedInt * 107639;
                                        Toaster(resultInt + " sq. foot = " + inputtedInt + " hectare.");
                                        break;
                                    case 2: //sq. yard
                                        resultInt = (double) inputtedInt * 11960;
                                        Toaster(resultInt + " sq. yard = " + inputtedInt + " hectare.");
                                        break;
                                    case 3: //sq. mile
                                        resultInt = (double) inputtedInt / 258.998917;
                                        Toaster(resultInt + " sq. mile = " + inputtedInt + " hectare.");
                                        break;
                                    case 4: //sq. meter
                                        resultInt = (double) inputtedInt * 10000;
                                        Toaster(resultInt + " sq. meter = " + inputtedInt + " hectare.");
                                        break;
                                    case 5: //sq. kilometer
                                        resultInt = (double) inputtedInt / 100;
                                        Toaster(resultInt + " sq. kilometer = " + inputtedInt + " hectare.");
                                        break;
                                    case 6: //acre
                                        resultInt = (double) inputtedInt * 2.47105;
                                        Toaster(resultInt + " acre = " + inputtedInt + " hectare.");
                                        break;
                                    case 7: //hectare
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " hectare = " + inputtedInt + " hectare.");
                                        break;
                                }
                                break;
                        }
                    }
                    else if (unitpos == 3){ //weight
                        switch (val1) {
                            case 0: //lbs
                                switch (val2) {
                                    case 0: //lbs
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " lb = " + inputtedInt + " lb.");
                                        break;
                                    case 1: //g
                                        resultInt = (double) inputtedInt * 453.59237;
                                        Toaster(resultInt + " g = " + inputtedInt + " lb.");
                                        break;
                                    case 2: //kg
                                        resultInt = (double) inputtedInt * 0.45359237;
                                        Toaster(resultInt + " kg = " + inputtedInt + " lb.");
                                        break;
                                    case 3: //oz
                                        resultInt = (double) inputtedInt * 16;
                                        Toaster(resultInt + " oz = " + inputtedInt + " lb.");
                                        break;
                                    case 4: //sh. t.
                                        resultInt = (double) inputtedInt / 2000;
                                        Toaster(resultInt + " sh. t. = " + inputtedInt + " lb.");
                                        break;
                                    case 5: //l. t.
                                        resultInt = (double) inputtedInt / 2240;
                                        Toaster(resultInt + " l. t. = " + inputtedInt + " lb.");
                                        break;
                                    case 6: //mt
                                        resultInt = (double) inputtedInt * 0.00045359237;
                                        Toaster(resultInt + " mt = " + inputtedInt + " lb.");
                                        break;
                                }
                                break;
                            case 1: //g
                                switch (val2) {
                                    case 0: //lbs
                                        resultInt = (double) inputtedInt * 0.0022046226218;
                                        Toaster(resultInt + " lb = " + inputtedInt + " g.");
                                        break;
                                    case 1: //g
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " g = " + inputtedInt + " g.");
                                        break;
                                    case 2: //kg
                                        resultInt = (double) inputtedInt / 1000;
                                        Toaster(resultInt + " kg = " + inputtedInt + " g.");
                                        break;
                                    case 3: //oz
                                        resultInt = (double) inputtedInt / 28.34952;
                                        Toaster(resultInt + " oz = " + inputtedInt + " g.");
                                        break;
                                    case 4: //sh. t.
                                        resultInt = (double) inputtedInt / 907185;
                                        Toaster(resultInt + " sh. t. = " + inputtedInt + " g.");
                                        break;
                                    case 5: //l. t.
                                        resultInt = (double) inputtedInt * 0.00000098420652761106;
                                        Toaster(resultInt + " l. t. = " + inputtedInt + " g.");
                                        break;
                                    case 6:  //mt
                                        resultInt = (double) inputtedInt / 1000000;
                                        Toaster(resultInt + " mt = " + inputtedInt + " g.");
                                        break;
                                }
                                break;
                            case 2: //kg
                                switch (val2) {
                                    case 0: //lbs
                                        resultInt = (double) inputtedInt * 2.2046226218;
                                        Toaster(resultInt + " lb = " + inputtedInt + " kg.");
                                        break;
                                    case 1: //g
                                        resultInt = (double) inputtedInt * 1000;
                                        Toaster(resultInt + " g = " + inputtedInt + " kg.");
                                        break;
                                    case 2:  //kg
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " kg = " + inputtedInt + " kg.");
                                        break;
                                    case 3: //oz
                                        resultInt = (double) inputtedInt / 0.02834952;
                                        Toaster(resultInt + " oz = " + inputtedInt + " kg.");
                                        break;
                                    case 4: //sh. t.
                                        resultInt = (double) inputtedInt / 907.185;
                                        Toaster(resultInt + " sh. t. = " + inputtedInt + " kg.");
                                        break;
                                    case 5: //l. t.
                                        resultInt = (double) inputtedInt * 0.00098420652761106;
                                        Toaster(resultInt + " l. t. = " + inputtedInt + " kg.");
                                        break;
                                    case 6: //mt
                                        resultInt = (double) inputtedInt / 1000;
                                        Toaster(resultInt + " mt = " + inputtedInt + " kg.");
                                        break;
                                }
                                break;
                            case 3: //oz
                                switch (val2) {
                                    case 0: //lbs
                                        resultInt = (double) inputtedInt / 16;
                                        Toaster(resultInt + " lb = " + inputtedInt + " oz.");
                                        break;
                                    case 1: //g
                                        resultInt = (double) inputtedInt * 28.34952;
                                        Toaster(resultInt + " g = " + inputtedInt + " oz.");
                                        break;
                                    case 2: //kg
                                        resultInt = (double) inputtedInt * 0.02834952;
                                        Toaster(resultInt + " kg = " + inputtedInt + " oz.");
                                        break;
                                    case 3: //oz
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " oz = " + inputtedInt + " oz.");
                                        break;
                                    case 4: //sh. t.
                                        resultInt = (double) inputtedInt / 32000;
                                        Toaster(resultInt + " sh. t. = " + inputtedInt + " oz.");
                                        break;
                                    case 5: //l. t.
                                        resultInt = (double) inputtedInt / 35840;
                                        Toaster(resultInt + " l. t. = " + inputtedInt + " oz.");
                                        break;
                                    case 6: //mt
                                        resultInt = (double) inputtedInt * 0.0000283495;
                                        Toaster(resultInt + " mt = " + inputtedInt + " oz.");
                                        break;
                                }
                                break;
                            case 4: //sh. t.
                                switch (val2) {
                                    case 0: //lbs
                                        resultInt = (double) inputtedInt * 2000;
                                        Toaster(resultInt + " lb = " + inputtedInt + " sh. t..");
                                        break;
                                    case 1: //g
                                        resultInt = (double) inputtedInt * 907184.737;
                                        Toaster(resultInt + " g = " + inputtedInt + " sh. t..");
                                        break;
                                    case 2: //kg
                                        resultInt = (double) inputtedInt * 907.184737;
                                        Toaster(resultInt + " kg = " + inputtedInt + " sh. t..");
                                        break;
                                    case 3:  //oz
                                        resultInt = (double) inputtedInt * 32000;
                                        Toaster(resultInt + " oz = " + inputtedInt + " sh. t..");
                                        break;
                                    case 4:  //sh. t.
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " sh. t. = " + inputtedInt + " sh. t..");
                                        break;
                                    case 5: //l. t.
                                        resultInt = (double) inputtedInt * 0.892857143;
                                        Toaster(resultInt + " l. t. = " + inputtedInt + " sh. t..");
                                        break;
                                    case 6: //mt
                                        resultInt = (double) inputtedInt * 0.90718474;
                                        Toaster(resultInt + " mt = " + inputtedInt + " sh. t..");
                                        break;
                                }
                                break;
                            case 5: //l. t.
                                switch (val2) {
                                    case 0: //lbs
                                        resultInt = (double) inputtedInt * 2240;
                                        Toaster(resultInt + " lb = " + inputtedInt + " l. t..");
                                        break;
                                    case 1: //g
                                        resultInt = (double) inputtedInt * 1016046.9088;
                                        Toaster(resultInt + " g = " + inputtedInt + " l. t..");
                                        break;
                                    case 2: //kg
                                        resultInt = (double) inputtedInt * 1016.0469088;
                                        Toaster(resultInt + " kg = " + inputtedInt + " l. t..");
                                        break;
                                    case 3:  //oz
                                        resultInt = (double) inputtedInt * 35840;
                                        Toaster(resultInt + " oz = " + inputtedInt + " l. t..");
                                        break;
                                    case 4: //sh. t.
                                        resultInt = (double) inputtedInt * 1.12;
                                        Toaster(resultInt + " sh. t. = " + inputtedInt + " l. t..");
                                        break;
                                    case 5: //l. t.
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " l. t. = " + inputtedInt + " l. t..");
                                        break;
                                    case 6: //mt
                                        resultInt = (double) inputtedInt * 1.0160469088;
                                        Toaster(resultInt + " mt = " + inputtedInt + " l. t..");
                                        break;
                                }
                                break;
                            case 6: //mt
                                switch (val2) {
                                    case 0: //lbs
                                        resultInt = (double) inputtedInt * 2204.62262;
                                        Toaster(resultInt + " lb = " + inputtedInt + " mt.");
                                        break;
                                    case 1: //g
                                        resultInt = (double) inputtedInt * 1000000;
                                        Toaster(resultInt + " g = " + inputtedInt + " mt");
                                        break;
                                    case 2://kg
                                        resultInt = (double) inputtedInt * 1000;
                                        Toaster(resultInt + " kg = " + inputtedInt + " mt.");
                                        break;
                                    case 3: //oz
                                        resultInt = (double) inputtedInt * 35274;
                                        Toaster(resultInt + " oz = " + inputtedInt + " mt.");
                                        break;
                                    case 4: //sh. t.
                                        resultInt = (double) inputtedInt * 1.10231131;
                                        Toaster(resultInt + " sh. t. = " + inputtedInt + " mt.");
                                        break;
                                    case 5: //l. t.
                                        resultInt = (double) inputtedInt / 1.01604642;
                                        Toaster(resultInt + " l. t. = " + inputtedInt + " mt.");
                                        break;
                                    case 6: //mt
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " mt = " + inputtedInt + " mt.");
                                        break;
                                }
                                break;
                        }
                    }
                    else if (unitpos == 4) { // speed/velocity
                        switch(val1){
                            case 0: // m/s
                                switch(val2){
                                    case 0: // m/s
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " m/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 1: // km/h
                                        resultInt = (double) inputtedInt * 3.6;
                                        Toaster(resultInt + " km/h = " + inputtedInt + " m/s.");
                                        break;
                                    case 2: // mph
                                        resultInt = (double) inputtedInt * 2.23693629;
                                        Toaster(resultInt + " mph = " + inputtedInt + " m/s.");
                                        break;
                                    case 3: // ft/s
                                        resultInt = (double) inputtedInt * 3.2808399;
                                        Toaster(resultInt + " ft/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 4: // kn
                                        resultInt = (double) inputtedInt * 1.94384449;
                                        Toaster(resultInt + " kn = " + inputtedInt + " m/s.");
                                        break;
                                }
                                break;
                            case 1: // km/h
                                switch(val2){
                                    case 0: // m/s
                                        resultInt = (double) inputtedInt / 3.6;
                                        Toaster(resultInt + " m/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 1: // km/h
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " km/h = " + inputtedInt + " m/s.");
                                        break;
                                    case 2: // mph
                                        resultInt = (double) inputtedInt * 0.621371;
                                        Toaster(resultInt + " mph = " + inputtedInt + " m/s.");
                                        break;
                                    case 3: // ft/s
                                        resultInt = (double) inputtedInt * 0.911344;
                                        Toaster(resultInt + " ft/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 4: // kn
                                        resultInt = (double) inputtedInt * 0.539957;
                                        Toaster(resultInt + " kn = " + inputtedInt + " m/s.");
                                        break;
                                }
                                break;
                            case 2: // mph
                                switch(val2){
                                    case 0: // m/s
                                        resultInt = (double) inputtedInt * 0.44704;
                                        Toaster(resultInt + " m/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 1: // km/h
                                        resultInt = (double) inputtedInt * 1.60934;
                                        Toaster(resultInt + " km/h = " + inputtedInt + " m/s.");
                                        break;
                                    case 2: // mph
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " mph = " + inputtedInt + " m/s.");
                                        break;
                                    case 3: // ft/s
                                        resultInt = (double) inputtedInt * 1.46666667;
                                        Toaster(resultInt + " ft/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 4: // kn
                                        resultInt = (double) inputtedInt * 0.868976;
                                        Toaster(resultInt + " kn = " + inputtedInt + " m/s.");
                                        break;
                                }
                                break;
                            case 3: // ft/s
                                switch(val2){
                                    case 0: // m/s
                                        resultInt = (double) inputtedInt * 0.304785126;
                                        Toaster(resultInt + " m/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 1: // km/h
                                        resultInt = (double) inputtedInt * 1.09728;
                                        Toaster(resultInt + " km/h = " + inputtedInt + " m/s.");
                                        break;
                                    case 2: // mph
                                        resultInt = (double) inputtedInt * 0.681818;
                                        Toaster(resultInt + " mph = " + inputtedInt + " m/s.");
                                        break;
                                    case 3: // ft/s
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " ft/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 4: // kn
                                        resultInt = (double) inputtedInt * 0.592484;
                                        Toaster(resultInt + " kn = " + inputtedInt + " m/s.");
                                        break;
                                }
                                break;
                            case 4: // kn
                                switch(val2){
                                    case 0: // m/s
                                        resultInt = (double) inputtedInt * 0.514444;
                                        Toaster(resultInt + " m/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 1: // km/h
                                        resultInt = (double) inputtedInt * 1.852;
                                        Toaster(resultInt + " km/h = " + inputtedInt + " m/s.");
                                        break;
                                    case 2: // mph
                                        resultInt = (double) inputtedInt * 1.15078;
                                        Toaster(resultInt + " mph = " + inputtedInt + " m/s.");
                                        break;
                                    case 3: // ft/s
                                        resultInt = (double) inputtedInt * 1.68781;
                                        Toaster(resultInt + " ft/s = " + inputtedInt + " m/s.");
                                        break;
                                    case 4: // kn
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " kn = " + inputtedInt + " m/s.");
                                        break;
                                }
                                break;
                        }
                    }
                    else if (unitpos == 5){
                        switch(val1) {
                            case 0: //second
                                switch(val2){
                                    case 0: //second
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " seconds = " + inputtedInt + " seconds.");
                                        break;
                                    case 1: //minute
                                        resultInt = (double) inputtedInt/60;
                                        Toaster(resultInt + " minutes = " + inputtedInt + " seconds.");
                                        break;
                                    case 2: //hour
                                        resultInt = (double) inputtedInt/3600;
                                        Toaster(resultInt + " hours = " + inputtedInt + " seconds.");
                                        break;
                                    case 3: //day
                                        resultInt = (double) inputtedInt/86400;
                                        Toaster(resultInt + " days = " + inputtedInt + " seconds.");
                                        break;
                                    case 4: //week
                                        resultInt = (double) inputtedInt/604800;
                                        Toaster(resultInt + " week = " + inputtedInt + " seconds.");
                                        break;
                                    case 5: //month
                                        resultInt = (double) inputtedInt/2628000;
                                        Toaster(resultInt + " month = " + inputtedInt + " seconds.");
                                        break;
                                    case 6: //year
                                        resultInt = (double) inputtedInt/31536000;
                                        Toaster(resultInt + " year = " + inputtedInt + " seconds.");
                                        break;
                                }
                                break;
                            case 1: //minute
                                switch(val2){
                                    case 0: //second
                                        resultInt = (double) inputtedInt*60;
                                        Toaster(resultInt + " seconds = " + inputtedInt + " minute.");
                                        break;
                                    case 1: //minute
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " minutes = " + inputtedInt + " minute.");
                                        break;
                                    case 2: //hour
                                        resultInt = (double) inputtedInt/60;
                                        Toaster(resultInt + " hours = " + inputtedInt + " minute.");
                                        break;
                                    case 3: //day
                                        resultInt = (double) inputtedInt/1440;
                                        Toaster(resultInt + " days = " + inputtedInt + " minute.");
                                        break;
                                    case 4: //week
                                        resultInt = (double) inputtedInt/10080;
                                        Toaster(resultInt + " week = " + inputtedInt + " minute.");
                                        break;
                                    case 5: //month
                                        resultInt = (double) inputtedInt/43800;
                                        Toaster(resultInt + " month = " + inputtedInt + " minute.");
                                        break;
                                    case 6: //year
                                        resultInt = (double) inputtedInt/525600;
                                        Toaster(resultInt + " year = " + inputtedInt + " minute.");
                                        break;
                                }
                                break;
                            case 2: //hour
                                switch(val2){
                                    case 0: //second
                                        resultInt = (double) inputtedInt*3600;
                                        Toaster(resultInt + " seconds = " + inputtedInt + " hour.");
                                        break;
                                    case 1: //minute
                                        resultInt = (double) inputtedInt*60;
                                        Toaster(resultInt + " minutes = " + inputtedInt + " hour.");
                                        break;
                                    case 2: //hour
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " hours = " + inputtedInt + " hour.");
                                        break;
                                    case 3: //day
                                        resultInt = (double) inputtedInt/24;
                                        Toaster(resultInt + " days = " + inputtedInt + " hour.");
                                        break;
                                    case 4: //week
                                        resultInt = (double) inputtedInt/168;
                                        Toaster(resultInt + " week = " + inputtedInt + " hour.");
                                        break;
                                    case 5: //month
                                        resultInt = (double) inputtedInt/730;
                                        Toaster(resultInt + " month = " + inputtedInt + " hour.");
                                        break;
                                    case 6: //year
                                        resultInt = (double) inputtedInt/8760;
                                        Toaster(resultInt + " year = " + inputtedInt + " hour.");
                                        break;
                                }
                                break;
                            case 3: //day
                                switch(val2){
                                    case 0: //second
                                        resultInt = (double) inputtedInt*86400;
                                        Toaster(resultInt + " seconds = " + inputtedInt + " day.");
                                        break;
                                    case 1: //minute
                                        resultInt = (double) inputtedInt*1440;
                                        Toaster(resultInt + " minutes = " + inputtedInt + " day.");
                                        break;
                                    case 2: //hour
                                        resultInt = (double) inputtedInt*24;
                                        Toaster(resultInt + " hours = " + inputtedInt + " day.");
                                        break;
                                    case 3: //day
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " days = " + inputtedInt + " day.");
                                        break;
                                    case 4: //week
                                        resultInt = (double) inputtedInt/7;
                                        Toaster(resultInt + " week = " + inputtedInt + " day.");
                                        break;
                                    case 5: //month
                                        resultInt = (double) inputtedInt/30.4166667;
                                        Toaster(resultInt + " month = " + inputtedInt + " day.");
                                        break;
                                    case 6: //year
                                        resultInt = (double) inputtedInt/365;
                                        Toaster(resultInt + " year = " + inputtedInt + " day.");
                                        break;
                                }
                                break;
                            case 4: //week
                                switch(val2){
                                    case 0: //second
                                        resultInt = (double) inputtedInt*604800;
                                        Toaster(resultInt + " seconds = " + inputtedInt + " week.");
                                        break;
                                    case 1: //minute
                                        resultInt = (double) inputtedInt*10080;
                                        Toaster(resultInt + " minutes = " + inputtedInt + " week.");
                                        break;
                                    case 2: //hour
                                        resultInt = (double) inputtedInt*168;
                                        Toaster(resultInt + " hours = " + inputtedInt + " week.");
                                        break;
                                    case 3: //day
                                        resultInt = (double) inputtedInt*7;
                                        Toaster(resultInt + " days = " + inputtedInt + " week.");
                                        break;
                                    case 4: //week
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " week = " + inputtedInt + " week.");
                                        break;
                                    case 5: //month
                                        resultInt = (double) inputtedInt/4.34523784;
                                        Toaster(resultInt + " month = " + inputtedInt + " week.");
                                        break;
                                    case 6: //year
                                        resultInt = (double) inputtedInt/52.1428087;
                                        Toaster(resultInt + " year = " + inputtedInt + " week.");
                                        break;
                                }
                                break;
                            case 5: //month
                                switch(val2){
                                    case 0: //second
                                        resultInt = (double) inputtedInt*2628000;
                                        Toaster(resultInt + " seconds = " + inputtedInt + " month.");
                                        break;
                                    case 1: //minute
                                        resultInt = (double) inputtedInt*43800;
                                        Toaster(resultInt + " minutes = " + inputtedInt + " month.");
                                        break;
                                    case 2: //hour
                                        resultInt = (double) inputtedInt*730;
                                        Toaster(resultInt + " hours = " + inputtedInt + " month.");
                                        break;
                                    case 3: //day
                                        resultInt = (double) inputtedInt*30.4166667;
                                        Toaster(resultInt + " days = " + inputtedInt + " month.");
                                        break;
                                    case 4: //week
                                        resultInt = (double) inputtedInt*4.34523784;
                                        Toaster(resultInt + " week = " + inputtedInt + " month.");
                                        break;
                                    case 5: //month
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " month = " + inputtedInt + " month.");
                                        break;
                                    case 6: //year
                                        resultInt = (double) inputtedInt/12;
                                        Toaster(resultInt + " year = " + inputtedInt + " month.");
                                        break;
                                }
                                break;
                            case 6: //year
                                switch(val2){
                                    case 0: //second
                                        resultInt = (double) inputtedInt*31536000;
                                        Toaster(resultInt + " seconds = " + inputtedInt + " year.");
                                        break;
                                    case 1: //minute
                                        resultInt = (double) inputtedInt*525600;
                                        Toaster(resultInt + " minutes = " + inputtedInt + " year.");
                                        break;
                                    case 2: //hour
                                        resultInt = (double) inputtedInt*8760;
                                        Toaster(resultInt + " hours = " + inputtedInt + " year.");
                                        break;
                                    case 3: //day
                                        resultInt = (double) inputtedInt*365;
                                        Toaster(resultInt + " days = " + inputtedInt + " year.");
                                        break;
                                    case 4: //week
                                        resultInt = (double) inputtedInt*52.1428571;
                                        Toaster(resultInt + " week = " + inputtedInt + " year.");
                                        break;
                                    case 5: //month
                                        resultInt = (double) inputtedInt*12;
                                        Toaster(resultInt + " month = " + inputtedInt + " year.");
                                        break;
                                    case 6: //year
                                        resultInt = (double) inputtedInt;
                                        Toaster(resultInt + " year = " + inputtedInt + " year.");
                                        break;
                                }
                                break;
                        }
                    }
                    result.setText(String.valueOf(resultInt));
                }
            }
        });
        //OnClick
    }
}