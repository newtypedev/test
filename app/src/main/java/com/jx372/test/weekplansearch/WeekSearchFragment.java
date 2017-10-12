package com.jx372.test.weekplansearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jx372.test.R;

import java.util.List;

/**
 * Created by pys on 2017. 10. 10..
 */

public class WeekSearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private ExpandableListAdapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;

    public static WeekSearchFragment newInstance(int position) {
        WeekSearchFragment f = new WeekSearchFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public void updateUI(List data){

        //if(adapter == null) {

            adapter =  new ExpandableListAdapter(data);
            recyclerView.setAdapter(adapter);
        //}
//        else{
//            adapter.notifyDataSetChanged();
//        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_weeksearch,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        recyclerView = (RecyclerView) view.findViewById(R.id.weeksearch_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴



       // recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



       /* List<ExpandableListAdapter.Item> data = new ArrayList<>();


        Map map = new HashMap();
        map.put("userid","test01");
        map.put("achieve","80");
        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER,map );
        places.invisibleChildren = new ArrayList<>();
        Map map2 = new HashMap();
        map2.put("daytext","MON");

        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
        map2.put("daytext","MON2");
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
        map2.put("daytext","MON3");
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
        map2.put("daytext","MON4");
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
        data.add(places);
        map = new HashMap();
        map.put("userid","test02");
        map.put("achieve","70");
        ExpandableListAdapter.Item places2 = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER,map );
        places2.invisibleChildren = new ArrayList<>();
         map2 = new HashMap();
        map2.put("daytext","MON");

        places2.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
        map2.put("daytext","MON2");
        places2.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
        map2.put("daytext","MON3");
        places2.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
        map2.put("daytext","MON4");
        places2.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
        data.add(places2);*/




//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER,"test01"));
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Banana"));
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Audi"));
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Audi2"));
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Audi3"));
//        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Audi4"));
//
//        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Places");
//        places.invisibleChildren = new ArrayList<>();
//        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Kerala"));
//        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Tamil Nadu"));
//        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Karnataka"));
//        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Maharashtra"));
//
//        data.add(places);




        //recyclerView.setAdapter(new ExpandableListAdapter(data));



        return view;
    }

}
