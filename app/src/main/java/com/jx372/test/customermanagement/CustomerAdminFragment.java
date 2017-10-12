package com.jx372.test.customermanagement;

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

import java.util.List;

/**
 * Created by pys on 2017. 10. 5..
 */

public class CustomerAdminFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomerAdminFragment.CustomerAdapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    public static CustomerAdminFragment newInstance(int position) {
        CustomerAdminFragment f = new CustomerAdminFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public void updateUI(){


        CustomerList customerList = CustomerList.get(getActivity());
        List<Customer> customers = customerList.getCustomers();


        if(adapter == null) {
            adapter = new CustomerAdminFragment.CustomerAdapter(customers);
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


        View view = inflater.inflate(R.layout.fragment_customeradmin,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        recyclerView = (RecyclerView) view.findViewById(R.id.customer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        updateUI();


        return view;
    }


    private class CustomerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView customerCode;
        private TextView customerName;
        private Customer customer;

        public CustomerHolder(View itemView) {
            super(itemView);
            customerCode = (TextView)itemView.findViewById(R.id.customerCode);
            customerName = (TextView)itemView.findViewById(R.id.customerName);
            itemView.setOnClickListener(this);

        }

        public void bindCrime(Customer customer){
            this.customer = customer;
            customerCode.setText(customer.getCode());
            customerName.setText(customer.getName());

        }

        @Override
        public void onClick(View view) {

            //   Toast.makeText(getActivity(),crime.getTitle()+"",Toast.LENGTH_LONG).show();

            Intent intent = CustomerModifyActivity.newIntent(getActivity(),customer.getId());
            startActivity(intent);
        }
    }


    private class CustomerAdapter extends RecyclerView.Adapter<CustomerAdminFragment.CustomerHolder>{

        private List<Customer> customers;

        public CustomerAdapter(List<Customer> customers){
            this.customers = customers;
        }

        @Override
        public CustomerAdminFragment.CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_customer,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new CustomerAdminFragment.CustomerHolder(view);
        }



        @Override
        public void onBindViewHolder(CustomerAdminFragment.CustomerHolder holder, int position) {
            Customer customer =customers.get(position);
            holder.bindCrime(customer);

        }

        @Override
        public int getItemCount() {
            return customers.size();
        }
    }



}
