package com.geekylab.general.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekylab.general.R;


public class VolleyFragment extends BaseFragment {

	public VolleyFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_volley, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtGuide.setText("Hello Volley!!!");
        txtDescription.setVisibility(View.GONE);
        btnExample.setVisibility(View.GONE);
    }
}
