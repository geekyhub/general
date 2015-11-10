package com.geekylab.general.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekylab.general.R;
import com.geekylab.general.network.events.BaseEvent;
import com.geekylab.general.network.events.EventManager;

import java.util.HashMap;


public class EventBusFragment extends BaseFragment {

	public EventBusFragment(){}
    private int counter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_event_bus, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String desc = "<h1>Event Bus</h1> 1. compile 'de.greenrobot:eventbus:2.4.0'<br> 2. Create EventManager.java to handle all the events 3. Create as many `event object` as you want under /network/events <br> 4. Register/unregister in the fragment/activity onStart and onStop respectively <br> 5. override onEvent() method for callback <br> 6. Push content from somewhere else. In this example we push from current fragment, but we could also push from some background task";
        txtGuide.setText(Html.fromHtml(desc));
        txtDescription.setText("This button has been clicked 0 times");



        btnExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("x", counter+"");
                BaseEvent example = new BaseEvent(getActivity(), params, null);
                EventManager.getBus().post(example);
            }
        });
    }


    public void onEvent(BaseEvent event){
        txtDescription.setText("This button has been clicked "+ event.params.get("x") + " times");
    }

}
