package com.page.page;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by timfe_000 on 2/7/14.
 */
public class SQLView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLInfo);
        LiteSequel info = new LiteSequel(this);

        try {
            info.write();
        } catch (SQLException e) {
            e.getStackTrace();
        }

        String data = info.getData();
        info.close();
        tv.setText(data);
    }
}