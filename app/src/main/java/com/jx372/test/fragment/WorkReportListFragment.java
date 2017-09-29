package com.jx372.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.irshulx.Editor;
import com.jx372.test.R;
import com.jx372.test.ReportItems;
import com.jx372.test.ReportModifyActivity;
import com.jx372.test.WorkReportList;

import java.util.List;

/**
 * Created by pys on 2017. 9. 24..
 */

public class WorkReportListFragment extends Fragment {

    private CardView cardview;
    private RecyclerView rerpotRecyclerView;
    private WorkReportListFragment.ReportAdapter adapter;
    private static final String ARG_POSITION = "position";
    private int position;

    public static WorkReportListFragment newInstance(int position) {

        WorkReportListFragment f = new WorkReportListFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }




    @Override
    public void onResume() {
       Log.v("리포트리스트리줌","실행");
        super.onResume();



        updateUI();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_report_list,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        rerpotRecyclerView = (RecyclerView) view.findViewById(R.id.report_recycler_view);
        rerpotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        updateUI();


        return view;
    }

    public void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }

    public void updateUI(){
        Log.v("언제실행","실행됨");
        TextView temp =(TextView)getActivity().findViewById(R.id.reportcount);

        WorkReportList reportList = WorkReportList.get(getActivity());
        List<ReportItems> reports = reportList.getReports();
        temp.setText("Total "+reports.size()+"");
        if(adapter == null) {
            adapter = new WorkReportListFragment.ReportAdapter(reports);
            rerpotRecyclerView.setAdapter(adapter);
            Log.v("널실행이다","프래그");
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }
    public void updateUI2(){
        WorkReportList reportList = WorkReportList.get(getActivity());
        List<ReportItems> reports = reportList.getReports();
        Log.v("update2 size",reports.size()+"");
        adapter.notifyDataSetChanged();
    }


    private class ReportHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView salesAccount;
        private ReportItems reportItems;
        private TextView repstartdis;
        private TextView rependdis;
       // private TextView repmemo;
        private Editor repmemo;
        private TextView approval;
        private TextView reporttarget;
        private TextView reportsale;
        private TextView titleTextView;
        private TextView dateTextView;
        private CheckBox solveCheckbox;
        private ReportItems report;
        public ReportHolder(View itemView) {
            super(itemView);

            approval = (TextView) itemView.findViewById(R.id.approvalText);
            repmemo =  (Editor) itemView.findViewById(R.id.replistmemo);
            reporttarget = (TextView) itemView.findViewById(R.id.reporttarget);
                    reportsale= (TextView)itemView.findViewById(R.id.reportsales);


            repstartdis = (TextView) itemView.findViewById(R.id.repstartdis);
            rependdis = (TextView) itemView.findViewById(R.id.enddis);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
          //  titleTextView = (TextView) itemView.findViewById(R.id.repmemo);
            dateTextView = (TextView) itemView.findViewById(R.id.dataText);
            solveCheckbox = (CheckBox) itemView.findViewById(R.id.checkSolve);

            itemView.setOnClickListener(this);

        }

        public void bindCrime(ReportItems report){
            this.report = report;
            this.repmemo.clearAllContents();
            repmemo.Render(report.getContent());
            reporttarget.setText(report.getTargetFigure());
            reportsale.setText(report.getSalesAccount());
          //  repmemo.Render(report.getContent());
            //titleTextView.setText(report.getContent());
            //  dateTextView.setText(report.getId()+"");
           // Log.v("bunhoho",report.getId()+"");


            //cardview.setCardBackgroundColor(getActivity().getResources().getColor(R.color.material_green_50));

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

        }

        @Override
        public void onClick(View view) {

            Intent intent = ReportModifyActivity.newIntent(getActivity(),report.getId());
            startActivity(intent);
        }
    }

    private class ReportAdapter extends RecyclerView.Adapter<WorkReportListFragment.ReportHolder>{

        private List<ReportItems> reports;

        public ReportAdapter(List<ReportItems> reports){
            this.reports = reports;
        }

        @Override
        public WorkReportListFragment.ReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_workreport,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new WorkReportListFragment.ReportHolder (view);
        }



        @Override
        public void onBindViewHolder(WorkReportListFragment.ReportHolder  holder, int position) {
            ReportItems report = reports.get(position);
            holder.bindCrime(report);

        }

        @Override
        public int getItemCount() {
            return reports.size();
        }
    }



}
