package com.example.eatheaven;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatheaven.Adapter.GenerateBillAdapter;
import com.example.eatheaven.Adapter.GenerateBillList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GenerateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GenerateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GenerateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<GenerateBillList> listItems;
    GenerateBillList listItem;
    TextView total_cost;

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("Hotel");






    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GenerateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GenerateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GenerateFragment newInstance(String param1, String param2) {
        GenerateFragment fragment = new GenerateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v= inflater.inflate(R.layout.fragment_generate,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.generatebill_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems=new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


        myRef.child("tables").child(firebaseUser.getUid()).child("cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                total_cost = (TextView)v.findViewById(R.id.fragment_generate_total);
                int temp_total = 0;//Integer.parseInt(total_cost.getText().toString());


                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    listItem= new GenerateBillList(""+ds.child("name").getValue() , ""+ds.child("cost").getValue(),""+ds.child("quantity").getValue());
                    listItems.add(listItem);
                    //total_cost.setText();

                   try{


                       temp_total+= Integer.parseInt(ds.child("cost").getValue().toString())*Integer.parseInt(ds.child("quantity").getValue().toString());
                   }catch(Exception e){
                       e.printStackTrace();
                   }
                }
                adapter=new GenerateBillAdapter(listItems, getContext());
                recyclerView.setAdapter(adapter);
                total_cost.setText(String.valueOf(temp_total));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Check your Internet", Toast.LENGTH_SHORT).show();
            }
        });



        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
