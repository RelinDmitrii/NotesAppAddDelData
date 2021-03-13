package com.example.notesappadddeldata;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class NoteAddFragment extends Fragment {

    EditText etTitle;
    TextView etData;
    EditText editTextDescription;
    MaterialButton materialButtonSave;
    MaterialButton materialButtonDataPicker;
    DatePickerDialog picker;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_add, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        materialButtonDataPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etData.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        materialButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etTitle.getText()) && !TextUtils.isEmpty(editTextDescription.getText()) && !TextUtils.isEmpty(etData.getText())){
                    Note note = new Note(etTitle.getText().toString(), editTextDescription.getText().toString(), etData.getText().toString());
                    ((MainActivity) getActivity()).notes.add(note);
                 getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void init(View view) {
        etTitle = view.findViewById(R.id.et_add_fragment_title);
        etData = view.findViewById(R.id.et_add_fragment_data);
        editTextDescription = view.findViewById(R.id.et_add_fragment_description);
        materialButtonSave = view.findViewById(R.id.mb_note_add_fragment_save);
        materialButtonDataPicker = view.findViewById(R.id.mb_note_add_fragment_addData);

    }


}
