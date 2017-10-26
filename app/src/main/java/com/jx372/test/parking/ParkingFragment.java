package com.jx372.test.parking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jx372.test.Callback2;
import com.jx372.test.HttpConnector;
import com.jx372.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by pys on 2017. 10. 26..
 */

public class ParkingFragment extends Fragment {

    private RecyclerView recyclerView;
    private ParkingFragment.ParkingAdapter adapter;
    private EditText parkingEdit;
    private static final String ARG_POSITION = "position";
    private int position;
    private ParkingList parkingList;

    public static ParkingFragment newInstance(int position) {
        ParkingFragment f = new ParkingFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    // 주차장 리스트 콜백
    Callback2 parkingselect = new Callback2() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                // Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                //Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson", msg);


                parkingList = ParkingList.get(getActivity());
                parkingList.cleanList();
                JSONObject datas = jsonbody.getJSONObject("GetParkInfo");
                    JSONArray datas2 = datas.getJSONArray("row");

                    int size = datas2.length();
                    Log.v("size", String.valueOf(size));
                    Log.v("sizecontent", datas+"");
               //     memberList = MemberList.get(LeaderMainActivity.this);
                   // memberList.cleanList();

                    for (int i = 0; i < size; i++) {

                        Parking p = new Parking(datas2.getJSONObject(i).getString("PARKING_NAME"),
                                datas2.getJSONObject(i).getString("CAPACITY"),
                                datas2.getJSONObject(i).getString("RATES"),
                                datas2.getJSONObject(i).getString("TIME_RATE")

                        );

                        parkingList.addParking(p);

                        // Log.v("customer들어오니",datas.getJSONObject(i).getString("name"));
                    }
                updateUI();

                //  nowMemberFragment.updateUI();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };



    public void updateUI(){


        ParkingList parkingList = ParkingList.get(getActivity());
        List<Parking> parkings = parkingList.getParkings();


        if(adapter == null) {
            adapter = new ParkingFragment.ParkingAdapter(parkings);
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


        View view = inflater.inflate(R.layout.fragment_parking,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        recyclerView = (RecyclerView) view.findViewById(R.id.parking_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        parkingEdit = (EditText)view.findViewById(R.id.parkingedit);
        view.findViewById(R.id.parkingsearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpConnector httpcon = new HttpConnector();
                httpcon.accessParking("신정동",parkingselect);
            }
        });
      //  updateUI();


        return view;
    }

    private class ParkingHolder extends RecyclerView.ViewHolder {


        private TextView parkname;
        private TextView parknum;
        private TextView parktime;
        private TextView parkpay;

        private Parking parking;

        public ParkingHolder(View itemView) {
            super(itemView);
            parkname = (TextView)itemView.findViewById(R.id.parkname);
            parknum = (TextView)itemView.findViewById(R.id.parknum);
            parktime = (TextView)itemView.findViewById(R.id.parktime);
            parkpay = (TextView)itemView.findViewById(R.id.parkpay);




        }

        public void bindCrime(Parking parking){
            this.parking = parking;
            parkname.setText(parking.getName().toString());
            parknum.setText(parking.getNum().toString());
            parktime.setText(parking.getTime().toString());
            parkpay.setText(parking.getPay().toString());


        }


    }


    private class ParkingAdapter extends RecyclerView.Adapter<ParkingFragment.ParkingHolder>{

        private List<Parking> parkings;

        public ParkingAdapter(List<Parking> parkings){
            this.parkings = parkings;
        }

        @Override
        public ParkingFragment.ParkingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_parking,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new ParkingFragment.ParkingHolder(view);
        }



        @Override
        public void onBindViewHolder(ParkingFragment.ParkingHolder holder, int position) {
            Parking parking =parkings.get(position);
            holder.bindCrime(parking);

        }

        @Override
        public int getItemCount() {
            return parkings.size();
        }
    }

}
