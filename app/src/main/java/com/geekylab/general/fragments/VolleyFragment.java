package com.geekylab.general.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekylab.general.R;
import com.geekylab.general.network.api.RequestManager;
import com.geekylab.general.network.events.BaseEvent;
import com.geekylab.general.network.events.EventManager;
import com.geekylab.general.network.events.GetUserEvent;

import java.util.HashMap;


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
        txtGuide.setText(Html.fromHtml("Hello Volley!!!<br><br>1. import volley module <br>2. compile project(':volley') <br>3. copy CustomRequest.java and RequestManager.java from /network <br>4. request with `RequestManager.getInstance(this).makeRequest()` <br>5. received on your eventbus callback `onEvent(BaseEvent event)`"));
        txtDescription.setText("");

        btnExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("test", "testing");
                txtDescription.setText("request in process....");
                RequestManager.getInstance(getActivity()).makeRequest(new GetUserEvent(getActivity(), params));
            }
        });
    }

    public void onEvent(BaseEvent event){
        if(event instanceof GetUserEvent){
            txtDescription.setText("my response: "+event.response);
        }

    }
}
