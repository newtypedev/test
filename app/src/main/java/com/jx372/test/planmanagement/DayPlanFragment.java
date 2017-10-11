package com.jx372.test.planmanagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jx372.test.R;

/**
 * Created by pys on 2017. 10. 2..
 */

public class DayPlanFragment extends Fragment{

    private static final String ARG_POSITION = "position";
    private int position;

    public static DayPlanFragment newInstance(int position) {
        DayPlanFragment f = new DayPlanFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consult_list,container,false);
        return view;
    }

    }
