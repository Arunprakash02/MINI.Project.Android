package com.abc.projexct_customer;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class intent_one extends AppCompatActivity {

    private EditText editTextName, editTextPhone, editTextEmail, editTextDate, editTextTime;
    private MaterialSpinner spinnerState;
    private Button submitButton;
    private TextView empytone;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_one);

        // Initialize views
        editTextName = findViewById(R.id.NAME);
        editTextPhone = findViewById(R.id.PHONE);
        editTextEmail = findViewById(R.id.EMAIL);
        spinnerState = findViewById(R.id.materialspinner);
        editTextDate = findViewById(R.id.DATE);
        editTextTime = findViewById(R.id.TIME);
        submitButton = findViewById(R.id.SUBMIT);
        empytone = findViewById(R.id.empyt);

        editTextPhone.setInputType(InputType.TYPE_CLASS_NUMBER);


        String[] country = { "India", "USA", "France", "Russia", "Japan"};
        spinnerState.setItems(country);


        // Date picker
        editTextDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(intent_one.this,
                    (view, year1, monthOfYear, dayOfMonth) ->
                            editTextDate.setText(dayOfMonth + " - " + (monthOfYear + 1) + " - " + year1),
                    year, month, day);
            datePickerDialog.show();
        });

        // Time picker (12-hour format with AM/PM)
        editTextTime.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(intent_one.this,
                    (view, hourOfDay, minuteOfHour) -> {
                        String amPm = (hourOfDay >= 12) ? "PM" : "AM";
                        int hourIn12HourFormat = (hourOfDay > 12) ? hourOfDay - 12 : hourOfDay;
                        if (hourIn12HourFormat == 0) hourIn12HourFormat = 12; // Handle midnight case
                        editTextTime.setText(String.format("%02d:%02d %s", hourIn12HourFormat, minuteOfHour, amPm));
                    }, hour, minute, false); // false for 12-hour format
            timePickerDialog.show();
        });

        // Submit button click listener
        submitButton.setOnClickListener(v -> {
            if (validateForm()) {
                JSONObject JSOBJ = new JSONObject();
                try {
                    JSOBJ.put("NAME", editTextName.getText());
                    JSOBJ.put("phone", editTextPhone.getText());
                    JSOBJ.put("email", editTextEmail.getText());
                    JSOBJ.put("date", editTextDate.getText());
                    JSOBJ.put("time", editTextTime.getText());
                    JSOBJ.put("State", spinnerState.getText());

                    Log.d("result", "onCreate: " + JSOBJ);
                    String abc = JSOBJ.toString(10);
                    empytone.setText(abc);

                } catch (JSONException e) {
                    throw new RuntimeException(e);

                }
                Toast.makeText(intent_one.this, "Form submitted successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm() {
        // Validate Name
        String name = editTextName.getText().toString().trim();
        if (name.isEmpty()) {
            showToast("Please enter your name");
            return false;
        }
        if (!name.matches("^[a-zA-Z]+$")) { // Regex for letters only
            showToast("Name can only contain letters");
            return false;
        }

        // Validate Phone Number
        String phoneNumber = editTextPhone.getText().toString().trim();
        if (phoneNumber.isEmpty()) {
            showToast("Please enter your phone number");
            return false;
        }
        else if (!phoneNumber.matches("^[0-9]+$")) { // Regex for digits only
            showToast("Phone number can only contain digits");
            return false;
        }
        // Check if phone number has fewer than 10 digits
        if (phoneNumber.length() < 10) {
            showToast("Phone number must contain at least 10 digits");
            return false;
        }

        else if (phoneNumber.length() > 13) { // Check max length
            showToast("Phone number cannot exceed 13 characters");
            return false;
        }


        // Validate Email
        String email = editTextEmail.getText().toString().trim();
        if (email.isEmpty()) {
            showToast("Please enter your email");
            return false;
        } else if (!email.contains(".com")) { // Check if email contains .com
            showToast("Email must contain '.com'");
            return false;
        }

        // Validate Material Spinner (State Selection)
        String selectedState = spinnerState.getText().toString().trim();

        if (TextUtils.isEmpty(selectedState) || selectedState.equals("Select a country")) {
            Toast.makeText(this, "Please select a valid country", Toast.LENGTH_SHORT).show();
            return false;
        }

        String[] validCountries = {"India", "USA", "France", "Russia", "Japan"};
        boolean isValidCountry = false;

        for (String validCountry : validCountries) {
            if (validCountry.equals(selectedState)) {
                isValidCountry = true;
                break;
            }
        }

        if (!isValidCountry) {
            Toast.makeText(this, "Please select a valid country from the list", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate Date
        String date = editTextDate.getText().toString().trim();
        if (date.isEmpty()) {
            showToast("Please select a date");
            return false;
        }

        // Validate Time
        String time = editTextTime.getText().toString().trim();
        if (time.isEmpty()) {
            showToast("Please select a time");
            return false;
        } else if (!time.matches(".*(AM|PM).*")) { // Check if time contains AM or PM
            showToast("Time must include 'AM' or 'PM'");
            return false;
        }

        // If all validations pass
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
