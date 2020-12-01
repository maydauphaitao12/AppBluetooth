package com.example.appbt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SetTimer extends AppCompatActivity implements View.OnClickListener {
    Button btnTimePicker, btnTimePicker1;
    EditText txtTime, txtTime1;

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    private int mHour, mMinute;
    private Button[] btnTimer;
    private EditText[] txtTimer;
    private boolean[] btnTimerState = {false, false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer);

        btnTimePicker = (Button) findViewById(R.id.btn_time);
        btnTimePicker1 = (Button) findViewById(R.id.btn_time1);


        txtTime = (EditText) findViewById(R.id.in_time);
        txtTime1 = (EditText) findViewById(R.id.in_time1);

        btnTimer = new Button[]{btnTimePicker,btnTimePicker1};
        txtTimer = new EditText[]{txtTime,txtTime1};

        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);
        Button buttonApply = findViewById(R.id.button_apply);

        for (int i = 0 ; i<btnTimer.length;i++) {
            for (int x = 0; x < (txtTimer.length); x++) {
                Button btnTime = btnTimer[i];


                int finalI = i;
                btnTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isOn = btnTimerState[finalI];
                        int code = isOn ? finalI * 2 + 2 : finalI * 2 + 1;

                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);


                        TimePickerDialog timePickerDialog = new TimePickerDialog(SetTimer.this, new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                });
            }
        }

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date d1 = null;
                Date d2 = null;
                long diffTime = 0;
                String diffTimeText = "";
                try {
                    d1 = format.parse(txtTime.getText().toString());
                    d2 = format.parse(txtTime1.getText().toString());

                    //in milliseconds
                    diffTime = d2.getTime() - d1.getTime();

                    Log.e("MainActivity", "diff = " + diffTime);

                    long diffHour = diffTime / (60 * 60 * 1000);
                    long diffMinute  = (diffTime / 1000 / 60) % 60;
                    diffTimeText = diffHour + ":" + diffMinute;

                } catch (Exception e) {
                    // TODO: handle exception
                }

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                textView.setText("View: " + diffTimeText);
            }
        });


    }

    public void onClick(View v) {


//        if (v == btnTimePicker) {
//
//            // Get Current Time
//            final Calendar c = Calendar.getInstance();
//            mHour = c.get(Calendar.HOUR_OF_DAY);
//            mMinute = c.get(Calendar.MINUTE);
//
//            // Launch Time Picker Dialog
//            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                    new TimePickerDialog.OnTimeSetListener() {
//
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay,
//                                              int minute) {
//
//                            txtTime.setText(hourOfDay + ":" + minute);
//                        }
//                    }, mHour, mMinute, false);
//            timePickerDialog.show();
//        }
//
//        if (v == btnTimePicker1) {
//
//            // Get Current Time
//            final Calendar c = Calendar.getInstance();
//            mHour = c.get(Calendar.HOUR_OF_DAY);
//            mMinute = c.get(Calendar.MINUTE);
//
//            // Launch Time Picker Dialog
//            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                    new TimePickerDialog.OnTimeSetListener() {
//
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay,
//                                              int minute) {
//
//                            txtTime1.setText(hourOfDay + ":" + minute);
//                        }
//                    }, mHour, mMinute, false);
//            timePickerDialog.show();
//        }
    }

    public void checkButton(View v) {

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}