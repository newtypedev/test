package com.jx372.test.workapproval;

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

import java.util.List;

/**
 * Created by pys on 2017. 10. 3..
 */

public class ApprovalFragment extends Fragment {

    private RecyclerView recyclerView;
    private ApprovalFragment.ApprovalAdapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;

    public static ApprovalFragment newInstance(int position) {
        ApprovalFragment f = new ApprovalFragment();
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


        ApprovalList approvalList = ApprovalList.get(getActivity());
        List<Approval> approvals = approvalList.getApprovals();


        if(adapter == null) {
            adapter = new ApprovalFragment.ApprovalAdapter(approvals);
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

        private TextView titleTextView;
        private Approval approval;

        public ApprovalHolder(View itemView) {
            super(itemView);
           // titleTextView = (TextView)itemView.findViewById(R.id.approvaltest);
            itemView.setOnClickListener(this);

        }

        public void bindCrime(Approval approval){
            this.approval = approval;
//            titleTextView.setText(approval.getTitle());

        }

        @Override
        public void onClick(View view) {

            //   Toast.makeText(getActivity(),crime.getTitle()+"",Toast.LENGTH_LONG).show();

           // Intent intent = ApprovalActivity.newIntent(getActivity(),approval.getId());
           // startActivity(intent);
        }
    }


    private class ApprovalAdapter extends RecyclerView.Adapter<ApprovalFragment.ApprovalHolder>{

        private List<Approval> approvals;

        public ApprovalAdapter(List<Approval> approvals){
            this.approvals = approvals;
        }

        @Override
        public ApprovalFragment.ApprovalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_approval,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new ApprovalFragment.ApprovalHolder(view);
        }



        @Override
        public void onBindViewHolder(ApprovalFragment.ApprovalHolder holder, int position) {
            Approval approval =approvals.get(position);
            holder.bindCrime(approval);

        }

        @Override
        public int getItemCount() {
            return approvals.size();
        }
    }


}
