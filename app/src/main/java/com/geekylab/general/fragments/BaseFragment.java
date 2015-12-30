package com.geekylab.general.fragments;


import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekylab.general.R;
import com.geekylab.general.network.events.BaseEvent;
import com.geekylab.general.network.events.EventManager;




public class BaseFragment extends Fragment {
    protected TextView txtDescription;
    protected TextView txtGuide;
    protected TextView btnExample;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtGuide = (TextView)view.findViewById(R.id.txtGuide);
        txtDescription = (TextView)view.findViewById(R.id.txtDescription);
        btnExample = (TextView)view.findViewById(R.id.btnExample);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventManager.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventManager.getBus().unregister(this);
    }

    public void onEvent(BaseEvent event){

    }

}
