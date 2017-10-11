package com.jx372.test.dayplansearch;

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
 * Created by pys on 2017. 10. 10..
 */

public class DaySearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private DaySearchFragment.Adapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;

    public static DaySearchFragment newInstance(int position) {
        DaySearchFragment f = new DaySearchFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public void updateUI(){


        DaySearchList daySearchList = DaySearchList.get(getActivity());
        List<DaySearch> daysearchs = daySearchList.getDaysearchs();


        if(adapter == null) {
            adapter = new DaySearchFragment.Adapter(daysearchs);
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


        View view = inflater.inflate(R.layout.fragment_daysearch,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        recyclerView = (RecyclerView) view.findViewById(R.id.daysearch_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        updateUI();


        return view;
    }


    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView customerCode;
        private TextView customerName;
        private DaySearch daySearch;

        public Holder(View itemView) {
            super(itemView);
            customerCode = (TextView)itemView.findViewById(R.id.customerCode);
            customerName = (TextView)itemView.findViewById(R.id.customerName);
            itemView.setOnClickListener(this);

        }

        public void bind(DaySearch daySearch){
            this.daySearch = daySearch;
           // customerCode.setText(daySearch.getCode());
            //customerName.setText(customer.getName());

        }

        @Override
        public void onClick(View view) {

            //   Toast.makeText(getActivity(),crime.getTitle()+"",Toast.LENGTH_LONG).show();

          //  Intent intent = CustomerModifyActivity.newIntent(getActivity(),customer.getId());
           // startActivity(intent);
        }
    }


    private class Adapter extends RecyclerView.Adapter<DaySearchFragment.Holder>{

        private List<DaySearch> daySearches;

        public Adapter(List<DaySearch> daySearches){
            this.daySearches = daySearches;
        }

        @Override
        public DaySearchFragment.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_daysearch,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new DaySearchFragment.Holder(view);
        }



        @Override
        public void onBindViewHolder(DaySearchFragment.Holder holder, int position) {
            DaySearch daySearch =daySearches.get(position);
            holder.bind(daySearch);

        }

        @Override
        public int getItemCount() {
            return daySearches.size();
        }
    }

}
