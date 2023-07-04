package com.openclassrooms.mymeeting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoomFilterDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomFilterDialogFragment extends Fragment {

    public String roomSelected;

    public RoomFilterDialogFragment() {
        // Required empty public constructor
    }
    public static RoomFilterDialogFragment newInstance() {
        RoomFilterDialogFragment fragment = new RoomFilterDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_filter, container, false);
    }
}