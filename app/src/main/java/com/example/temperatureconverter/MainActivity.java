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
                adapterChosen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapterChosen.notifyDataSetChanged();
                //String text = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //adapterChosen = ArrayAdapter.createFromResource(MainActivity.this, R.array.temperatureunits, android.R.layout.simple_spinner_item);
        //unitpos = 0;
        //spinner1.setAdapter(adapterChosen);
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
                                resultInt = inputtedInt;
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
                                Toaster(resultInt + " m = " + inputtedInt + " ft.");
                            }
                            else if(val2 == 5) { //km
                                resultInt = (double) inputtedInt / 3280.839895;
                                Toaster(resultInt + " m = " + inputtedInt + " ft.");
                            }
                        }

                    }
                    result.setText(String.valueOf(resultInt));
                }
            }
        });
        //OnClick
    }
}