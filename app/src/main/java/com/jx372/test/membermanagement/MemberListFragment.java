package com.jx372.test.membermanagement;

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
 * Created by pys on 2017. 10. 6..
 */

public class MemberListFragment extends Fragment {

    private RecyclerView recyclerView;
    private MemberListFragment.MemberAdapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;

    public static MemberListFragment newInstance(int position) {
        MemberListFragment f = new MemberListFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public void updateUI(){


        MemberList memberList = MemberList.get(getActivity());
        List<Member> members = memberList.getMembers();


        if(adapter == null) {
            adapter = new MemberListFragment.MemberAdapter(members);
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


        View view = inflater.inflate(R.layout.fragment_member_list,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        recyclerView = (RecyclerView) view.findViewById(R.id.member_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        updateUI();


        return view;
    }

    private class MemberHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleTextView;
        private TextView memberId;
        private TextView memberName;
        private TextView memberGrade;
        private Member member;

        public MemberHolder(View itemView) {
            super(itemView);
            memberId = (TextView)itemView.findViewById(R.id.memberid);
            memberName = (TextView)itemView.findViewById(R.id.membername);
            memberGrade = (TextView)itemView.findViewById(R.id.membergrade);


            itemView.setOnClickListener(this);

        }

        public void bindCrime(Member member){
            this.member = member;
         memberId.setText(member.getUserId().toString());
            memberName.setText(member.getName().toString());
            memberGrade.setText(member.getGrade().toString());

        }

        @Override
        public void onClick(View view) {

            //   Toast.makeText(getActivity(),crime.getTitle()+"",Toast.LENGTH_LONG).show();

            //Intent intent = ConsultModifyActivity.newIntent(getActivity(),consult.getId());
            //startActivity(intent);
        }
    }


    private class MemberAdapter extends RecyclerView.Adapter<MemberListFragment.MemberHolder>{

        private List<Member> members;

        public MemberAdapter(List<Member> members){
            this.members = members;
        }

        @Override
        public MemberListFragment.MemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_member,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new MemberListFragment.MemberHolder(view);
        }



        @Override
        public void onBindViewHolder(MemberListFragment.MemberHolder holder, int position) {
            Member member =members.get(position);
            holder.bindCrime(member);

        }

        @Override
        public int getItemCount() {
            return members.size();
        }
    }

}
