package com.jx372.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jx372.test.R;
import com.jx372.test.User;

/**
 * Created by pys on 2017. 10. 12..
 */

public class MypageFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private TextView myid;
    private TextView myname;
    private TextView mydept;
    private TextView mygrade;
    private int position;

    public static MypageFragment newInstance(int position) {
        MypageFragment f = new MypageFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_mypage,
                container, false);
        myid = (TextView) rootView.findViewById(R.id.myid);
        myname = (TextView) rootView.findViewById(R.id.myname);
        mydept = (TextView) rootView.findViewById(R.id.mydept);
        mygrade = (TextView) rootView.findViewById(R.id.mygrade);

        myid.setText(User.get().getId());
        myname.setText(User.get().getName());
        mydept.setText(User.get().getDept());
        mygrade.setText(User.get().getGrade());


        return rootView;
    }


}
