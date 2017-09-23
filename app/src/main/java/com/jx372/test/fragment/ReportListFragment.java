package com.jx372.test.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jx372.test.R;
import com.jx372.test.ReportList;
import com.jx372.test.ReportListItem;

import java.util.List;

public class ReportListFragment extends Fragment {

    private CardView cardview;
    private RecyclerView rerpotRecyclerView;
    private ReportListFragment.ReportAdapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;

    public static ReportListFragment newInstance(int position) {
        ReportListFragment f = new ReportListFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




        // itemUpdate();
        //onBackPressed();
        if(item.getItemId() == R.id.writeconsult){
            //itemUpdate();
            //    NavUtils.navigateUpFromSameTask(this);
            //  onBackPressed();
            Log.v("작성받음","ㄴㄹㄴㄹ");


        }

        if(item.getItemId()==R.id.dayback){

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        position = getArguments().getInt(ARG_POSITION);
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

    private void updateUI(){
        ReportList reportList = ReportList.get(getActivity());
        List<ReportListItem> consults = reportList.getReports();
        if(adapter == null) {
            adapter = new ReportListFragment.ReportAdapter(consults);
            rerpotRecyclerView.setAdapter(adapter);
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }

    private class ReportHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleTextView;
        private TextView dateTextView;
        private CheckBox solveCheckbox;
        private ReportListItem report;
        public ReportHolder(View itemView) {
            super(itemView);

            cardview = (CardView) itemView.findViewById(R.id.cardview);
            titleTextView = (TextView) itemView.findViewById(R.id.titleText);
            dateTextView = (TextView) itemView.findViewById(R.id.dataText);
            solveCheckbox = (CheckBox) itemView.findViewById(R.id.checkSolve);

            itemView.setOnClickListener(this);

        }

        public void bindCrime(ReportListItem consult){
            this.report = consult;
            titleTextView.setText(report.getTitle());
         //  dateTextView.setText(report.getId()+"");
            solveCheckbox.setChecked(report.isSolved());
            Log.v("bunhoho",report.getTitle());

            if(report.getOpinion()==0) {
                cardview.setCardBackgroundColor(getActivity().getResources().getColor(R.color.material_green_100));
            }
            else if(report.getOpinion()==1) {
                cardview.setCardBackgroundColor(getActivity().getResources().getColor(R.color.material_red_100));
            }
            else if(report.getOpinion()==2) {
                cardview.setCardBackgroundColor(getActivity().getResources().getColor(R.color.material_red_100));
            }
            else if(report.getOpinion()==3) {
                cardview.setCardBackgroundColor(getActivity().getResources().getColor(R.color.material_blue_100));
            }
            else{
                cardview.setCardBackgroundColor(getActivity().getResources().getColor(R.color.main_color_grey_300));
            }

        }

        @Override
        public void onClick(View view) {

            //   Toast.makeText(getActivity(),crime.getTitle()+"",Toast.LENGTH_LONG).show();

            //Intent intent = ConsultActivity.newIntent(getActivity(),consult.getId());
           // startActivity(intent);
        }
    }

    private class ReportAdapter extends RecyclerView.Adapter<ReportListFragment.ReportHolder>{

        private List<ReportListItem> reports;

        public ReportAdapter(List<ReportListItem> crimes){
            this.reports = crimes;
        }

        @Override
        public ReportListFragment.ReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_report,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new ReportListFragment.ReportHolder (view);
        }



        @Override
        public void onBindViewHolder(ReportListFragment.ReportHolder  holder, int position) {
            ReportListItem consult = reports.get(position);
            holder.bindCrime(consult);

        }

        @Override
        public int getItemCount() {
            return reports.size();
        }
    }



}