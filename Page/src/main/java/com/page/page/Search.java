package com.page.page;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by timfe_000 on 1/28/14.
 */
public class Search extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initialize();
    }

    public void initialize() {
        Button searchButton = (Button) findViewById(R.id.bSubmitSearch);
        searchButton.setOnClickListener(this);

        spinnerInitialize();

    }

    public void spinnerInitialize() {
        Spinner courseSub = (Spinner) findViewById(R.id.sSubject);
        Spinner courseNum = (Spinner) findViewById(R.id.sClassNumber);

        //I set the course subject array to the course subject spinner
        ArrayAdapter<CharSequence> subject = ArrayAdapter.createFromResource(this,
                R.array.course_subject, android.R.layout.simple_spinner_item);
        subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSub.setAdapter(subject);

        //I can probably hack this.  I can take away the course number from the course name
        //by setting up a string-array with the same name as the subject.

        //I set the course number array to the course number spinner.
        ArrayAdapter<CharSequence> number = ArrayAdapter.createFromResource(this,
                R.array.course_number, android.R.layout.simple_spinner_item);
        subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseNum.setAdapter(number);
    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView) findViewById(R.id.tvTest);
        tv.setText("It worked!");
    }
}
