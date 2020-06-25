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
                if (parent.getItemAtPosition(position).equals("Weight")){
                    adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.weightunits, android.R.layout.simple_spinner_item);
                    spinner1.setAdapter(adapterChosen);
                    spinner2.setAdapter(adapterChosen);
                    unitpos = 2;
                    Toaster("Weight selected");
                }
                if (parent.getItemAtPosition(position).equals("Speed/Velocity")){
                    adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.velocityunits, android.R.layout.simple_spinner_item);
                    spinner1.setAdapter(adapterChosen);
                    spinner2.setAdapter(adapterChosen);
                    unitpos = 3;
                    Toaster("Speed/Velocity selected");
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
                        if (val1 == 0) {   //fahrenheit
                            if (val2 == 0) { //fahrenheit
                                resultInt = inputtedInt;
                                Toaster(resultInt + " F* = " + inputtedInt + " F*.");
                            }
                            else if (val2 == 1) { //celsius
                                resultInt = (double) (inputtedInt - 32) * 5 / 9;
                                //resultInt = (double)
                                Toaster(resultInt + " C* = " + inputtedInt + " F*.");
                            }
                            else if (val2 == 2) { //kelvin
                                resultInt = (resultInt = (double) (inputtedInt - 32) * 5 / 9) + 273.15;
                                Toaster(resultInt + " K* = " + inputtedInt + " F*.");
                            }
                        }
                        if (val1 == 1) {   //celsius
                            if (val2 == 0) { //fahrenheit
                                resultInt = (inputtedInt * 9 / 5) + 32;
                                Toaster(resultInt + " F* = " + inputtedInt + " C*.");
                            }
                            else if (val2 == 1) { //celsius
                                resultInt = inputtedInt;
                                Toaster(resultInt + " C* = " + inputtedInt + " C*.");
                            }
                            else if (val2 == 2) { //kelvin
                                resultInt = inputtedInt + 273.15;
                                Toaster(resultInt + " K = " + inputtedInt + " C*.");
                            }
                        }
                        if (val1 == 2) {   //kelvin
                            if (val2 == 0) { //fahrenheit
                                resultInt = ((inputtedInt - 273.15) * 9 / 5) + 32;
                                Toaster(resultInt + " F* = " + inputtedInt + " K.");
                            }
                            else if (val2 == 1) { //celsius
                                resultInt = inputtedInt - 273.15;
                                Toaster(resultInt + " C* = " + inputtedInt + " K.");
                            }
                            else if (val2 == 2) { //kelvin
                                resultInt = inputtedInt;
                                Toaster(resultInt + " K = " + inputtedInt + " K.");
                            }
                        }

                    }
                    else if (unitpos == 1){ //length
                        if(val1 == 0){ //feet
                            if(val2 == 0) { //feet
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " ft = " + inputtedInt + " ft.");
                            }
                            else if(val2 == 1) { //mile
                                resultInt = (double) inputtedInt / 5280;
                                Toaster(resultInt + " mi = " + inputtedInt + " ft.");
                            }
                            else if(val2 == 2) { //yard
                                resultInt = (double) inputtedInt / 3;
                                Toaster(resultInt + " yd = " + inputtedInt + " ft.");
                            }
                            else if(val2 == 3) { //inch
                                resultInt = (double) inputtedInt * 12;
                                Toaster(resultInt + " in = " + inputtedInt + " ft.");
                            }
                            else if(val2 == 4) { //meters
                                resultInt = (double) inputtedInt / 3.280839895;
                                Toaster(resultInt + " m  = " + inputtedInt + " ft.");
                            }
                            else if(val2 == 5) { //km
                                resultInt = (double) inputtedInt / 3280.839895;
                                Toaster(resultInt + " km = " + inputtedInt + " ft.");
                            }
                        }
                        else if(val1 == 1){ //mile
                            if(val2 == 0) { //feet
                                resultInt = (double) inputtedInt * 5280;
                                Toaster(resultInt + " ft = " + inputtedInt + " mi.");
                            }
                            else if(val2 == 1) { //mile
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " mi = " + inputtedInt + " mi.");
                            }
                            else if(val2 == 2) { //yard
                                resultInt = (double) inputtedInt * 1760;
                                Toaster(resultInt + " yd = " + inputtedInt + " mi.");
                            }
                            else if(val2 == 3) { //inch
                                resultInt = (double) inputtedInt * 63360;
                                Toaster(resultInt + " in = " + inputtedInt + " mi.");
                            }
                            else if(val2 == 4) { //meters
                                resultInt = (double) inputtedInt * 1609.344;
                                Toaster(resultInt + " m  = " + inputtedInt + " mi.");
                            }
                            else if(val2 == 5) { //km
                                resultInt = (double) inputtedInt * 1.609344;
                                Toaster(resultInt + " km = " + inputtedInt + " mi.");
                            }
                        }
                        else if(val1 == 2){ //yard
                            if(val2 == 0) { //feet
                                resultInt = (double) inputtedInt * 3;
                                Toaster(resultInt + " ft = " + inputtedInt + " yd.");
                            }
                            else if(val2 == 1) { //mile
                                resultInt = (double) inputtedInt / 1760;
                                Toaster(resultInt + " mi = " + inputtedInt + " yd.");
                            }
                            else if(val2 == 2) { //yard
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " yd = " + inputtedInt + " yd.");
                            }
                            else if(val2 == 3) { //inch
                                resultInt = (double) inputtedInt * 36;
                                Toaster(resultInt + " in = " + inputtedInt + " yd.");
                            }
                            else if(val2 == 4) { //meters
                                resultInt = (double) inputtedInt / 1.0936133;
                                Toaster(resultInt + " m  = " + inputtedInt + " yd.");
                            }
                            else if(val2 == 5) { //km
                                resultInt = (double) inputtedInt / 1093.6133;
                                Toaster(resultInt + " km = " + inputtedInt + " yd.");
                            }
                        }
                        else if(val1 == 3){ //inch
                            if(val2 == 0) { //feet
                                resultInt = (double) inputtedInt / 12;
                                Toaster(resultInt + " ft = " + inputtedInt + " in.");
                            }
                            else if(val2 == 1) { //mile
                                resultInt = (double) inputtedInt / 63360;
                                Toaster(resultInt + " mi = " + inputtedInt + " in.");
                            }
                            else if(val2 == 2) { //yard
                                resultInt = (double) inputtedInt / 36;
                                Toaster(resultInt + " yd = " + inputtedInt + " in.");
                            }
                            else if(val2 == 3) { //inch
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " in = " + inputtedInt + " in.");
                            }
                            else if(val2 == 4) { //meters
                                resultInt = (double) inputtedInt / 39.3700787;
                                Toaster(resultInt + " m  = " + inputtedInt + " in.");
                            }
                            else if(val2 == 5) { //km
                                resultInt = (double) inputtedInt / 39370.0787;
                                Toaster(resultInt + " km = " + inputtedInt + " in.");
                            }
                        }
                        else if(val1 == 4){ //meters
                            if(val2 == 0) { //feet
                                resultInt = (double) inputtedInt * 3.28084;
                                Toaster(resultInt + " ft = " + inputtedInt + " m.");
                            }
                            else if(val2 == 1) { //mile
                                resultInt = (double) inputtedInt / 1609.34395;
                                Toaster(resultInt + " mi = " + inputtedInt + " m.");
                            }
                            else if(val2 == 2) { //yard
                                resultInt = (double) inputtedInt * 1.09361;
                                Toaster(resultInt + " yd = " + inputtedInt + " m.");
                            }
                            else if(val2 == 3) { //inch
                                resultInt = (double) inputtedInt * 39.3701;
                                Toaster(resultInt + " in = " + inputtedInt + " m.");
                            }
                            else if(val2 == 4) { //meters
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " m  = " + inputtedInt + " m.");
                            }
                            else if(val2 == 5) { //km
                                resultInt = (double) inputtedInt / 1000;
                                Toaster(resultInt + " km = " + inputtedInt + " m.");
                            }
                        }
                        else if(val1 == 5){ //km
                            if(val2 == 0) { //feet
                                resultInt = (double) inputtedInt * 3280.84;
                                Toaster(resultInt + " ft = " + inputtedInt + " km.");
                            }
                            else if(val2 == 1) { //mile
                                resultInt = (double) inputtedInt / 1.60934395;
                                Toaster(resultInt + " mi = " + inputtedInt + " km.");
                            }
                            else if(val2 == 2) { //yard
                                resultInt = (double) inputtedInt * 1093.61;
                                Toaster(resultInt + " yd = " + inputtedInt + " km.");
                            }
                            else if(val2 == 3) { //inch
                                resultInt = (double) inputtedInt * 39370.1;
                                Toaster(resultInt + " in = " + inputtedInt + " km.");
                            }
                            else if(val2 == 4) { //meters
                                resultInt = (double) inputtedInt * 1000;
                                Toaster(resultInt + " m  = " + inputtedInt + " km.");
                            }
                            else if(val2 == 5) { //km
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " km = " + inputtedInt + " km.");
                            }
                        }
                    }
                    else if (unitpos == 2){ //weight
                        if(val1 == 0) { //lbs
                            if (val2 == 0) { //lbs
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " lb = " + inputtedInt + " lb.");
                            }
                            else if(val2 == 1) { //g
                                resultInt = (double) inputtedInt * 453.59237;
                                Toaster(resultInt + " g = " + inputtedInt + " lb.");
                            }
                            else if(val2 == 2) { //kg
                                resultInt = (double) inputtedInt * 0.45359237;
                                Toaster(resultInt + " kg = " + inputtedInt + " lb.");
                            }
                            else if(val2 == 3) { //oz
                                resultInt = (double) inputtedInt * 16;
                                Toaster(resultInt + " oz = " + inputtedInt + " lb.");
                            }
                            else if(val2 == 4) { //sh. t.
                                resultInt = (double) inputtedInt / 2000;
                                Toaster(resultInt + " sh. t. = " + inputtedInt + " lb.");
                            }
                            else if(val2 == 5) { //l. t.
                                resultInt = (double) inputtedInt / 2240;
                                Toaster(resultInt + " l. t. = " + inputtedInt + " lb.");
                            }
                            else if(val2 == 6) { //mt
                                resultInt = (double) inputtedInt * 0.00045359237;
                                Toaster(resultInt + " mt = " + inputtedInt + " lb.");
                            }
                        }
                        else if(val1 == 1) { //g
                            if (val2 == 0) { //lbs
                                resultInt = (double) inputtedInt * 0.0022046226218;
                                Toaster(resultInt + " lb = " + inputtedInt + " g.");
                            }
                            else if(val2 == 1) { //g
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " g = " + inputtedInt + " g.");
                            }
                            else if(val2 == 2) { //kg
                                resultInt = (double) inputtedInt / 1000;
                                Toaster(resultInt + " kg = " + inputtedInt + " g.");
                            }
                            else if(val2 == 3) { //oz
                                resultInt = (double) inputtedInt / 28.34952;
                                Toaster(resultInt + " oz = " + inputtedInt + " g.");
                            }
                            else if(val2 == 4) { //sh. t.
                                resultInt = (double) inputtedInt / 907185;
                                Toaster(resultInt + " sh. t. = " + inputtedInt + " g.");
                            }
                            else if(val2 == 5) { //l. t.
                                resultInt = (double) inputtedInt * 0.00000098420652761106;
                                Toaster(resultInt + " l. t. = " + inputtedInt + " g.");
                            }
                            else if(val2 == 6) { //mt
                                resultInt = (double) inputtedInt / 1000000;
                                Toaster(resultInt + " mt = " + inputtedInt + " g.");
                            }
                        }
                        else if(val1 == 2) { //kg
                            if (val2 == 0) { //lbs
                                resultInt = (double) inputtedInt * 2.2046226218;
                                Toaster(resultInt + " lb = " + inputtedInt + " kg.");
                            }
                            else if(val2 == 1) { //g
                                resultInt = (double) inputtedInt * 1000;
                                Toaster(resultInt + " g = " + inputtedInt + " kg.");
                            }
                            else if(val2 == 2) { //kg
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " kg = " + inputtedInt + " kg.");
                            }
                            else if(val2 == 3) { //oz
                                resultInt = (double) inputtedInt / 0.02834952;
                                Toaster(resultInt + " oz = " + inputtedInt + " kg.");
                            }
                            else if(val2 == 4) { //sh. t.
                                resultInt = (double) inputtedInt / 907.185;
                                Toaster(resultInt + " sh. t. = " + inputtedInt + " kg.");
                            }
                            else if(val2 == 5) { //l. t.
                                resultInt = (double) inputtedInt * 0.00098420652761106;
                                Toaster(resultInt + " l. t. = " + inputtedInt + " kg.");
                            }
                            else if(val2 == 6) { //mt
                                resultInt = (double) inputtedInt / 1000;
                                Toaster(resultInt + " mt = " + inputtedInt + " kg.");
                            }
                        }
                        else if(val1 == 3) { //oz
                            if (val2 == 0) { //lbs
                                resultInt = (double) inputtedInt / 16;
                                Toaster(resultInt + " lb = " + inputtedInt + " oz.");
                            }
                            else if(val2 == 1) { //g
                                resultInt = (double) inputtedInt * 28.34952;
                                Toaster(resultInt + " g = " + inputtedInt + " oz.");
                            }
                            else if(val2 == 2) { //kg
                                resultInt = (double) inputtedInt * 0.02834952;
                                Toaster(resultInt + " kg = " + inputtedInt + " oz.");
                            }
                            else if(val2 == 3) { //oz
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " oz = " + inputtedInt + " oz.");
                            }
                            else if(val2 == 4) { //sh. t.
                                resultInt = (double) inputtedInt / 32000;
                                Toaster(resultInt + " sh. t. = " + inputtedInt + " oz.");
                            }
                            else if(val2 == 5) { //l. t.
                                resultInt = (double) inputtedInt / 35840;
                                Toaster(resultInt + " l. t. = " + inputtedInt + " oz.");
                            }
                            else if(val2 == 6) { //mt
                                resultInt = (double) inputtedInt * 0.0000283495;
                                Toaster(resultInt + " mt = " + inputtedInt + " oz.");
                            }
                        }
                        else if(val1 == 4) { //sh. t.
                            if (val2 == 0) { //lbs
                                resultInt = (double) inputtedInt * 2000;
                                Toaster(resultInt + " lb = " + inputtedInt + " sh. t..");
                            }
                            else if(val2 == 1) { //g
                                resultInt = (double) inputtedInt * 907184.737;
                                Toaster(resultInt + " g = " + inputtedInt + " sh. t..");
                            }
                            else if(val2 == 2) { //kg
                                resultInt = (double) inputtedInt * 907.184737;
                                Toaster(resultInt + " kg = " + inputtedInt + " sh. t..");
                            }
                            else if(val2 == 3) { //oz
                                resultInt = (double) inputtedInt * 32000;
                                Toaster(resultInt + " oz = " + inputtedInt + " sh. t..");
                            }
                            else if(val2 == 4) { //sh. t.
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " sh. t. = " + inputtedInt + " sh. t..");
                            }
                            else if(val2 == 5) { //l. t.
                                resultInt = (double) inputtedInt * 0.892857143;
                                Toaster(resultInt + " l. t. = " + inputtedInt + " sh. t..");
                            }
                            else if(val2 == 6) { //mt
                                resultInt = (double) inputtedInt * 0.907441016;
                                Toaster(resultInt + " mt = " + inputtedInt + " sh. t..");
                            }
                        }
                        else if(val1 == 5) { //l. t.
                            if (val2 == 0) { //lbs
                                resultInt = (double) inputtedInt * 2240;
                                Toaster(resultInt + " lb = " + inputtedInt + " l. t..");
                            }
                            else if(val2 == 1) { //g
                                resultInt = (double) inputtedInt * 1016046.9088;
                                Toaster(resultInt + " g = " + inputtedInt + " l. t..");
                            }
                            else if(val2 == 2) { //kg
                                resultInt = (double) inputtedInt * 1016.0469088;
                                Toaster(resultInt + " kg = " + inputtedInt + " l. t..");
                            }
                            else if(val2 == 3) { //oz
                                resultInt = (double) inputtedInt * 35840;
                                Toaster(resultInt + " oz = " + inputtedInt + " l. t..");
                            }
                            else if(val2 == 4) { //sh. t.
                                resultInt = (double) inputtedInt * 1.12;
                                Toaster(resultInt + " sh. t. = " + inputtedInt + " l. t..");
                            }
                            else if(val2 == 5) { //l. t.
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " l. t. = " + inputtedInt + " l. t..");
                            }
                            else if(val2 == 6) { //mt
                                resultInt = (double) inputtedInt * 1.0160469088;
                                Toaster(resultInt + " mt = " + inputtedInt + " l. t..");
                            }
                        }
                        else if(val1 == 6) { //mt
                            if (val2 == 0) { //lbs
                                resultInt = (double) inputtedInt * 2204.62262;
                                Toaster(resultInt + " lb = " + inputtedInt + " mt.");
                            }
                            else if(val2 == 1) { //g
                                resultInt = (double) inputtedInt * 1000000;
                                Toaster(resultInt + " g = " + inputtedInt + " mt");
                            }
                            else if(val2 == 2) { //kg
                                resultInt = (double) inputtedInt * 1000;
                                Toaster(resultInt + " kg = " + inputtedInt + " mt.");
                            }
                            else if(val2 == 3) { //oz
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " oz = " + inputtedInt + " mt.");
                            }
                            else if(val2 == 4) { //sh. t.
                                resultInt = (double) inputtedInt * 1.10231131;
                                Toaster(resultInt + " sh. t. = " + inputtedInt + " mt.");
                            }
                            else if(val2 == 5) { //l. t.
                                resultInt = (double) inputtedInt / 1.01604642;
                                Toaster(resultInt + " l. t. = " + inputtedInt + " mt.");
                            }
                            else if(val2 == 6) { //mt
                                resultInt = (double) inputtedInt;
                                Toaster(resultInt + " mt = " + inputtedInt + " mt.");
                            }
                        }
                    }
                    else if (unitpos == 3) { // speed/velocity
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
                                        resultInt = (double) inputtedInt * 0.592484;
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
                    result.setText(String.valueOf(resultInt));
                }
            }
        });
        //OnClick
    }
}