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
import androidx.fragment.app.FragmentResultListener;

import com.google.android.material.button.MaterialButton;

public class NotesDetailFragment extends Fragment {

    public static final String ARG_INDEX = "arg_index_notes_detail_fragment";

    public TextView titleNotesDetail;
    public TextView dataNotesDetail;
    public EditText descriptionNotesDetail;
    public MaterialButton mbEditNote;

    public static NotesDetailFragment newInstance(int index) {
        NotesDetailFragment fragment = new NotesDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmnet_notes_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
        clickOnButton();
    }

    public void initList(View view) {
        titleNotesDetail = view.findViewById(R.id.tv_title_nd);
        dataNotesDetail = view.findViewById(R.id.tv_data_nd);
        descriptionNotesDetail = view.findViewById(R.id.et_description_nd);
        mbEditNote = view.findViewById(R.id.mb_notes_detail_edit);
    }

    public void clickOnButton(){
        mbEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteFragmentEdit noteFragmentEdit = NoteFragmentEdit.newInstance(getArguments().getInt(ARG_INDEX));
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, noteFragmentEdit)
                        .commit();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            String title = ((MainActivity) getActivity()).notes.get(getArguments().getInt(ARG_INDEX)).title;
            String description = ((MainActivity) getActivity()).notes.get(getArguments().getInt(ARG_INDEX)).description;
            String data = ((MainActivity) getActivity()).notes.get(getArguments().getInt(ARG_INDEX)).data;
            titleNotesDetail.setText(title);
            dataNotesDetail.setText(data);
            descriptionNotesDetail.setText(description);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String result = bundle.getString("bundleKey");
                descriptionNotesDetail.setText(result);
                ((MainActivity) getActivity()).notes.get(getArguments().getInt(ARG_INDEX)).description = result;
            }
        });
    }
}
