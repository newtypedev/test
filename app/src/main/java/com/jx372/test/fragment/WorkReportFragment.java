package com.jx372.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jx372.test.R;

/**
 * Created by pys on 2017. 9. 5..
 */

public class WorkReportFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;
    public static WorkReportFragment newInstance(int position) {
        WorkReportFragment f = new WorkReportFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_report,
                container, false);



        return rootView;
    }


}
