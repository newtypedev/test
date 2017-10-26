package com.jx372.test.workapproval;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jx372.test.R;
import com.jx372.test.ReportItems;
import com.jx372.test.WorkReportList;

import java.util.List;


public class WorkApprovalFragment extends Fragment {
    private RecyclerView recyclerView;
    private WorkApprovalFragment.ApprovalAdapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;

    public static WorkApprovalFragment newInstance(int position) {
        WorkApprovalFragment f = new WorkApprovalFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }



    public void updateUI(){


        WorkReportList reportList = WorkReportList.get(getActivity());
        List<ReportItems> reports = reportList.getReports();
        if(adapter == null) {
            adapter = new WorkApprovalFragment.ApprovalAdapter(reports);
            recyclerView.setAdapter(adapter);
            Log.v("널실행이다","프래그");
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_approval,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        recyclerView = (RecyclerView) view.findViewById(R.id.approval_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        updateUI();


        return view;
    }

    private class ApprovalHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private ReportItems report;
        private TextView memberid;
        private TextView reporttarget;
        private TextView reportsales;
        private TextView reportachieve;
        private TextView repstartdis;
        private TextView rependdis;
        private TextView reportmile;
        private TextView approvalText;

        public ApprovalHolder(View itemView) {
            super(itemView);
            approvalText = (TextView) itemView.findViewById(R.id.approvalText);
            memberid = (TextView)itemView.findViewById(R.id.memberid);
            reporttarget = (TextView)itemView.findViewById(R.id.reporttarget);
            reportsales = (TextView)itemView.findViewById(R.id.reportsales);
            reportachieve = (TextView)itemView.findViewById(R.id.reportachieve);
            repstartdis = (TextView)itemView.findViewById(R.id.repstartdis);
            rependdis = (TextView)itemView.findViewById(R.id.rependdis);
            reportmile = (TextView)itemView.findViewById(R.id.reportmile);
            itemView.setOnClickListener(this);

        }

        public void bindCrime(ReportItems report){
            this.report = report;
            memberid.setText(report.getUserid());
            reporttarget.setText(report.getTargetFigure());
            reportsales.setText(report.getSalesAccount());
            reportachieve.setText(report.getAchiveRate());
            repstartdis.setText(report.getStartGauge());
            rependdis.setText(report.getEndGauge());
            reportmile.setText(report.getMileleage());
            if(report.getApproval().equals("0")) {
                approvalText.setText("저장");
                approvalText.setTextColor(getActivity().getResources().getColor(R.color.main_color_grey_800));
            }
            else if(report.getApproval().equals("1")) {
                approvalText.setText("제출");
                approvalText.setTextColor(getActivity().getResources().getColor(R.color.material_blue_800));
            }
            else if(report.getApproval().equals("2")) {
                approvalText.setText("승인");
                approvalText.setTextColor(getActivity().getResources().getColor(R.color.material_green_800));
            }
            else if(report.getApproval().equals("3")) {
                approvalText.setText("반려");
                approvalText.setTextColor(getActivity().getResources().getColor(R.color.material_red_500));
            }

        }

        @Override
        public void onClick(View view) {

            //   Toast.makeText(getActivity(),crime.getTitle()+"",Toast.LENGTH_LONG).show();


            Intent intent = ApprovalActivity.newIntent(getActivity(),report.getId());
            startActivity(intent);

        }
    }


    private class ApprovalAdapter extends RecyclerView.Adapter<WorkApprovalFragment.ApprovalHolder>{

        private List<ReportItems> reports;

        public ApprovalAdapter(List<ReportItems> reports){
            this.reports = reports;
        }

        @Override
        public WorkApprovalFragment.ApprovalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_approval,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new WorkApprovalFragment.ApprovalHolder(view);
        }



        @Override
        public void onBindViewHolder(WorkApprovalFragment.ApprovalHolder holder, int position) {
            ReportItems report = reports.get(position);
            holder.bindCrime(report);


        }

        @Override
        public int getItemCount() {
            return reports.size();
        }
    }

}
