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
    private EditText enterTemp;
    int inputtedInt = 0;
    double resultInt = 0;
    double tempVal = 0;
    int pos = 0;
    int val1= 0;
    int val2= 0;
    //Toast echo
    void Toaster (CharSequence msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Spinners
        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinnerUnit = findViewById(R.id.spinnerunit);
        ArrayAdapter<CharSequence> adapterUnit = ArrayAdapter.createFromResource(this, R.array.totalunits, android.R.layout.simple_spinner_item);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.temperatureunits, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
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


        spinner2.setAdapter(adapter);
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
        enterTemp = findViewById(R.id.enterTemp);
        //Button
        Button conversion = findViewById(R.id.conversion);

        conversion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(enterTemp.getText().toString().trim().length() > 0){
                    String inputtedString = enterTemp.getText().toString();
                    inputtedInt = Integer.parseInt(inputtedString);
                    resultInt=0;
                    //positions
                    if(val1 == 0){   //fahrenheit
                        if(val2 == 0){ //fahrenheit
                            resultInt = inputtedInt;
                            Toaster(resultInt + " F* = " + inputtedInt + " F*.");
                        }
                        if(val2 == 1){ //celsius
                            resultInt = (double)(inputtedInt - 32) * 5 / 9;
                            //resultInt = (double)
                            Toaster(resultInt + " C* = " + inputtedInt + " F*.");
                        }
                        if(val2 == 2){ //kelvin
                            resultInt = (resultInt = (double)(inputtedInt - 32) * 5 / 9) + 273.15;
                            Toaster(resultInt + " K* = " + inputtedInt + " F*.");
                        }
                    }
                    if(val1 == 1){   //celsius
                        if(val2 == 0){ //fahrenheit
                            resultInt = (inputtedInt*9/5)+32;
                            Toaster(resultInt + " F* = " + inputtedInt + " C*.");
                        }
                        if(val2 == 1){ //celsius
                            resultInt = inputtedInt;
                            Toaster(resultInt + " C* = " + inputtedInt + " C*.");
                        }
                        if(val2 == 2){ //kelvin
                            resultInt = inputtedInt + 273.15;
                            Toaster(resultInt + " K = " + inputtedInt + " C*.");
                        }
                    }
                    if(val1 == 2){   //kelvin
                        if(val2 == 0){ //fahrenheit
                            resultInt = (double)((inputtedInt-273.15)* 9/5)+32;
                            Toaster(resultInt + " F* = " + inputtedInt + " K.");
                        }
                        if(val2 == 1){ //celsius
                            resultInt = inputtedInt-273.15;
                            Toaster(resultInt + " C* = " + inputtedInt + " K.");
                        }
                        if(val2 == 2){ //kelvin
                            resultInt = inputtedInt;
                            Toaster(resultInt + " K = " + inputtedInt + " K.");
                        }
                    }
                    result.setText(String.valueOf(resultInt));
                }
            }
        });
        //OnClick
    }
}