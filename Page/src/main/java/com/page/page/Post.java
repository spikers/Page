package com.page.page;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by timfe_000 on 1/28/14.
 */
public class Post extends Activity {

    Spinner sSubject, sNumber;
    EditText etPrice, etPhone, etDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        initialize();

    }

    public void initialize() {
        etPrice = (EditText) findViewById(R.id.etPrice);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etDescription = (EditText) findViewById(R.id.etDescription);

        spinnerInitialize();
    }

    public void spinnerInitialize() {
        sSubject = (Spinner) findViewById(R.id.sSubject);
        sNumber = (Spinner) findViewById(R.id.sClassNumber);

    }

    public void imageCapture(View view) {
        TextView tv = (TextView) findViewById(R.id.tvPostPrice);
        tv.setText("imageCapture class was called");
    }

    public void bookSubmit(View view) {
        TextView tv = (TextView) findViewById(R.id.tvPostZeroZero);
        tv.setText("bookSubmit class was called");
    }
}
