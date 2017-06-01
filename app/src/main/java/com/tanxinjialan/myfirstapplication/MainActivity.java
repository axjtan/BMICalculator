package com.tanxinjialan.myfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private float height;
    private float weight;
    private float BMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button compute_button = (Button) findViewById(R.id.compute_button);
        final EditText height_value = (EditText) findViewById(R.id.height);
        final EditText weight_value = (EditText) findViewById(R.id.weight);
        final TextView result = (TextView) findViewById(R.id.result);

        compute_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Close virtual keyboard on button ("Compute My BMI") press
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                // Check the length of both height_value and weight_value if more than zero
                if (height_value.getText().length() > 0 && weight_value.getText().length() > 0) {
                    height = Float.parseFloat(height_value.getText().toString());
                    weight = Float.parseFloat(weight_value.getText().toString());

                    // Check value of height and weight if in the suitable range
                    if (height > 20.0 && weight > 0.0) {

                        BMI = calculateBMI(weight, height);

                        if (BMI < 16)
                            result.setText("YourBMI: " + BMI + "( Severely underweight )");
                        else if (BMI < 18.5) result.setText("YourBMI: " + BMI + "( Underweight )");
                        else if (BMI < 25) result.setText("YourBMI: " + BMI + "( Normal )");
                        else if (BMI < 30) result.setText("YourBMI: " + BMI + "( Overweight )");
                        else result.setText("YourBMI: " + BMI + "( Obese )");

                    } else
                        Toast.makeText(getApplicationContext(), "Invalid Entry", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Please enter Height and/or Weight", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Own define method to do BMI calculation
    private float calculateBMI(float weight, float height) {
        height = (height / 100);
        return (weight / (height * height));
    }

}
