package com.example.notesappadddeldata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class NoteFragmentEdit extends Fragment {

    public static final String ARG_INDEX = "arg_index_notes_edit_fragment";

    public static NoteFragmentEdit newInstance(int index) {
        NoteFragmentEdit fragment = new NoteFragmentEdit();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    TextView textViewTitle;
    TextView textViewData;
    EditText editTextDescription;
    MaterialButton materialButtonSave;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_edit, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            String title = ((MainActivity) getActivity()).notes.get(getArguments().getInt(ARG_INDEX)).title;
            String description = ((MainActivity) getActivity()).notes.get(getArguments().getInt(ARG_INDEX)).description;
            String data = ((MainActivity) getActivity()).notes.get(getArguments().getInt(ARG_INDEX)).data;
            textViewTitle.setText(title);
            textViewData.setText(data);
            editTextDescription.setText(description);
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickOnButton();

    }

    public void init(View view) {
        textViewTitle = view.findViewById(R.id.tv_edit_fragment_title);
        textViewData = view.findViewById(R.id.tv_edit_fragment_data);
        editTextDescription = view.findViewById(R.id.et_edit_fragment_description);
        materialButtonSave = view.findViewById(R.id.mb_edit_fragment_save);
    }

    public void clickOnButton(){
        materialButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                // Пока передаем 1 поле с текстом
                result.putString("bundleKey", editTextDescription.getText().toString());
                getParentFragmentManager().setFragmentResult("requestKey", result);
                getActivity().onBackPressed();
            }
        });
    }

}
