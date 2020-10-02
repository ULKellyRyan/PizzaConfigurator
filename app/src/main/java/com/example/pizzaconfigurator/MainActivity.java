package com.example.pizzaconfigurator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    StringBuilder pizzaOrder = new StringBuilder();
    public static final String ORDER = "com.example.pizzaconfigurator";
    boolean switchClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populates spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinnerPizzaMenu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pizza_menu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner listener
        spinner.setOnItemSelectedListener(this);
    }

    //Called when a pizza is selected from the spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        pizzaOrder.append(parent.getItemAtPosition(pos).toString());
    }

    public void onNothingSelected(AdapterView<?> parent){
    }

    //called when checkboxes are clicked to add extras
    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()){
            case R.id.checkBox_cheese:
                if(checked){
                    pizzaOrder.append(", extra cheese");
                }
                break;
            case R.id.checkBox_mushroom:
                if(checked){
                    pizzaOrder.append(", extra mushrooms");
                }
                break;
            case R.id.checkBox_pineapple:
                if(checked){
                    pizzaOrder.append(", extra pineapple");
                }
                break;
            case R.id.checkBox_bacon:
                if(checked){
                    pizzaOrder.append(", extra bacon");
                }
                break;
            case R.id.checkBox_pepperoni:
                if(checked){
                    pizzaOrder.append(", extra pepperoni");
                }
                break;
            default:
                pizzaOrder.append(", no extras");
        }
    }

    public void onSwitchClicked(View view){
        switchClicked = ((CompoundButton) view).isChecked();
        pizzaOrder.append(", cheesy crust");
    }

    public void submitOrder(View view){
        if(!switchClicked){
            pizzaOrder.append(", no cheesy crust");
        }

        Intent intent = new Intent(this, DisplayOrderActivity.class);
        intent.putExtra(ORDER, pizzaOrder.toString());
        startActivity(intent);
    }
}
