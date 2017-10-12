package com.jx372.test.workapproval;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.irshulx.Editor;
import com.jx372.test.R;
import com.jx372.test.ReportItems;
import com.jx372.test.WorkReportList;

/**
 * Created by pys on 2017. 10. 12..
 */

public class ApprovalReportFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;
    private ReportItems report;
    private Editor repmemo;
    private TextView approval;
    private TextView reporttarget;
    private TextView reportachieve;
    private TextView repstartdis;
    private TextView rependdis;
    private TextView reportmile;
    private TextView reportsale;
    private TextView reportopinion;
    public static ApprovalReportFragment newInstance(int position) {
        ApprovalReportFragment f = new ApprovalReportFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_approval_report,
                container, false);

        approval = (TextView) rootView.findViewById(R.id.approvalText);
        repmemo =  (Editor) rootView.findViewById(R.id.replistmemo);
        reporttarget = (TextView) rootView.findViewById(R.id.reporttarget);
        reportsale= (TextView)rootView.findViewById(R.id.reportsales);
        reportachieve= (TextView)rootView.findViewById(R.id.reportachieve);
        repstartdis= (TextView)rootView.findViewById(R.id.repstartdis);
        rependdis= (TextView)rootView.findViewById(R.id.rependdis);
        reportmile= (TextView)rootView.findViewById(R.id.reportmile);
        reportopinion= (TextView)rootView.findViewById(R.id.reportopinion);

        ApprovalActivity approvalActivity = (ApprovalActivity) getActivity();

        report = WorkReportList.get(getActivity()).getReports(approvalActivity.getReportId());
        repmemo.Render(report.getContent());
        reporttarget.setText(report.getTargetFigure());
        reportsale.setText(report.getSalesAccount());
        reportachieve.setText(report.getAchiveRate()+"");
        repstartdis.setText(report.getStartGauge());
        rependdis.setText(report.getEndGauge());
        reportmile.setText(report.getMileleage());
        reportopinion.setText(report.getOpinion());


        if(report.getApproval().equals("0")) {
            approval.setText("저장");
            approval.setTextColor(getActivity().getResources().getColor(R.color.main_color_grey_800));
        }
        else if(report.getApproval().equals("1")) {
            approval.setText("제출");
            approval.setTextColor(getActivity().getResources().getColor(R.color.material_blue_800));
        }
        else if(report.getApproval().equals("2")) {
            approval.setText("승인");
            approval.setTextColor(getActivity().getResources().getColor(R.color.material_green_800));
        }
        else if(report.getApproval().equals("3")) {
            approval.setText("반려");
            approval.setTextColor(getActivity().getResources().getColor(R.color.material_red_500));
        }

        return rootView;
    }
}

