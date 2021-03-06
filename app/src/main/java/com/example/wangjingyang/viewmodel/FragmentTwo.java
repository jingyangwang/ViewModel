package com.example.wangjingyang.viewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentTwo extends Fragment{
    TextView showdatatv;
    Button adddata;
    private CommunicateViewModel mCommunicateViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommunicateViewModel = ViewModelProviders.of(getActivity()).get(CommunicateViewModel.class);
        mCommunicateViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showdatatv.setText("FragmentTwo 加载的数据   "+s);
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_two, container, false);
        adddata=view.findViewById(R.id.adddata);
        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommunicateViewModel.setName("FragmentTwo SET DATA  FRAA");
            }
        });
        showdatatv=view.findViewById(R.id.showdatatv);
        return view;
    }
}
