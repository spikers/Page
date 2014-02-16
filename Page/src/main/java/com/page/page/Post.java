package com.page.page;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by timfe_000 on 1/28/14.
 */
public class Post extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner sSubject, sNumber;
    EditText etPrice, etPhone, etDescription;
    String numberExtract, subjectExtract;

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

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
        sNumber = (Spinner) findViewById(R.id.sNumber);

        //I set the course subject array to the course subject spinner
        ArrayAdapter<CharSequence> subject = ArrayAdapter.createFromResource(this,
                R.array.course_subject, android.R.layout.simple_spinner_item);
        subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSubject.setAdapter(subject);

        //I can probably hack this.  I can take away the course number from the course name
        //by setting up a string-array with the same name as the subject.

        //I set the course number array to the course number spinner.
        ArrayAdapter<CharSequence> number = ArrayAdapter.createFromResource(this,
                R.array.course_number, android.R.layout.simple_spinner_item);
        number.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNumber.setAdapter(number);
    }

    public void imageCapture(View view) {
        TextView tv = (TextView) findViewById(R.id.tvPostPrice);
        tv.setText("imageCapture class was called");
    }

    public void bookSubmit(View view) {
        boolean didItWork= true;
        try {
            String description = etDescription.getText().toString();
            String phone = etPhone.getText().toString();
            String price = etPrice.getText().toString();

            LiteSequel entry = new LiteSequel(this);
            entry.write();
            //whatishappening here?
            entry.createEntry(description, phone, price);//, subjectExtract, numberExtract);
            entry.close();
        } catch (Exception e) {
            didItWork = false;
            Dialog d = new Dialog(this);
            d.setTitle("Error!");
            TextView tvDbChecker = new TextView(this);
            tvDbChecker.setText(e.toString());
            d.setContentView(tvDbChecker);
            d.show();
        } finally {
            if (didItWork) {
                Intent i = new Intent(this, SQLView.class);
                startActivity(i);
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //There's something amiss here.  Not sure what it is.  I'm getting null/null for both
        if (parent == sSubject) {
            subjectExtract = parent.getItemAtPosition(position).toString();
        } else {
            numberExtract = parent.getItemAtPosition(position).toString();
        }
    }
}
