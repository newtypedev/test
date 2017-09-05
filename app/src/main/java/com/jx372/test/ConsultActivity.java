package com.jx372.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import java.util.UUID;

public class ConsultActivity extends AppCompatActivity {


    public static final String CRIME_ID = "";

    private Consult consult;
    private EditText titleField;
    private Button dateButton;
    private CheckBox solvedCheckBox;
    private  UUID consultId;

    public static Intent newIntent(Context packageContext, UUID crimeid) {

        Intent intent = new Intent(packageContext, ConsultActivity.class);
        intent.putExtra(CRIME_ID, crimeid);
        return intent;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        consultId = (UUID)getIntent().getSerializableExtra(ConsultActivity.CRIME_ID);
        consult = ConsultList.get(this).getConsults(consultId);



        dateButton = (Button)findViewById(R.id.crime_date);
        dateButton.setText(consult.getDate().toString());
        //dateButton.setEnabled(false);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultList cl =ConsultList.get(ConsultActivity.this);
                cl.deleteItem(consultId);
            }
        });

        solvedCheckBox = (CheckBox)findViewById(R.id.crime_solved);
        solvedCheckBox.setChecked(consult.isSolved());
        solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheck) {
                consult.setSolved(isCheck);
            }
        });


        titleField = (EditText) findViewById(R.id.crime_title);
        titleField.setText(consult.getTitle());
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                consult.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}