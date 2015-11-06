package com.geekylab.general.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekylab.general.R;


public class BaseFragment extends Fragment {
    protected TextView txtDescription;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtDescription = (TextView)view.findViewById(R.id.txtDescription);
    }
}
