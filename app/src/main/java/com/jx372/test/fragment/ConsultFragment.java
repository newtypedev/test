package com.jx372.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.irshulx.Editor;
import com.jx372.test.Consult;
import com.jx372.test.ConsultList;
import com.jx372.test.ConsultModifyActivity;
import com.jx372.test.R;

import java.util.List;

/**
 * Created by pys on 2017. 9. 5..
 */

public class ConsultFragment extends Fragment{

    private RecyclerView consultRecyclerView;
    private ConsultAdapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;

    public static ConsultFragment newInstance(int position) {
        ConsultFragment f = new ConsultFragment();
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
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_consult_list,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        consultRecyclerView = (RecyclerView) view.findViewById(R.id.consult_recycler_view);
        consultRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        updateUI();


        return view;
    }
    public void updateUI(){

        TextView temp =(TextView)getActivity().findViewById(R.id.reportcount);

        ConsultList consultList = ConsultList.get(getActivity());
        List<Consult> consults = consultList.getConsults();

        temp.setText("Total "+consults.size()+"");
        if(adapter == null) {
            adapter = new ConsultAdapter(consults);
            consultRecyclerView.setAdapter(adapter);
            Log.v("널실행이다","프래그");
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }


    private class ConsultHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleTextView;
        private TextView dateTextView;
        private CheckBox solveCheckbox;
        private Consult consult;
        private TextView customerName;
        private TextView customerCode;
        private TextView chiefName;
        private TextView customerAddress;
        private TextView consultTitle;
        private Editor consultmemo;
        public ConsultHolder(View itemView) {
            super(itemView);
            consultmemo = (Editor) itemView.findViewById(R.id.consultmemoview) ;
            customerName = (TextView) itemView.findViewById(R.id.customerName);
            customerCode = (TextView) itemView.findViewById(R.id.customerCode);
            chiefName = (TextView) itemView.findViewById(R.id.chiefName);
            customerAddress = (TextView) itemView.findViewById(R.id.customerAddress);
            consultTitle = (TextView) itemView.findViewById(R.id.consultTitle);


            itemView.setOnClickListener(this);

        }

        public void bindCrime(Consult consult){
            this.consult = consult;
            this.consultmemo.clearAllContents();
            consultmemo.Render(consult.getContent());
            customerName.setText(consult.getName());
            customerCode.setText(consult.getCode());
            chiefName.setText(consult.getManager_name());
            consultTitle.setText(consult.getTitle());

        }

        @Override
        public void onClick(View view) {

            //   Toast.makeText(getActivity(),crime.getTitle()+"",Toast.LENGTH_LONG).show();

            Intent intent = ConsultModifyActivity.newIntent(getActivity(),consult.getId());
            startActivity(intent);
        }
    }

    private class ConsultAdapter extends RecyclerView.Adapter<ConsultHolder>{

        private List<Consult> consults;

        public ConsultAdapter(List<Consult> consults){
            this.consults = consults;
        }

        @Override
        public ConsultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_consult,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new ConsultHolder(view);
        }



        @Override
        public void onBindViewHolder(ConsultHolder holder, int position) {
            Consult consult = consults.get(position);
            holder.bindCrime(consult);

        }

        @Override
        public int getItemCount() {
            return consults.size();
        }
    }


}
