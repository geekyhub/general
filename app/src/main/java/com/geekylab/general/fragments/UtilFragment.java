package com.geekylab.general.fragments;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.geekylab.general.R;
import com.geekylab.general.network.TimerService;
import com.geekylab.general.network.api.ZipDownloader;
import com.geekylab.general.utils.SharePrefUtil;
import com.geekylab.general.utils.Utils;
import com.geekylab.general.utils.ui.GLDialog;


public class UtilFragment extends Fragment {
    int count;
	public UtilFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_util, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtGuide =(TextView)view.findViewById(R.id.txtGuide);
        final TextView txtDescription1 =(TextView)view.findViewById(R.id.txtDescription1);
        TextView txtDescription3 =(TextView)view.findViewById(R.id.txtDescription3);
        Button btnUtil1 =(Button)view.findViewById(R.id.btnUtil1);
        Button btnUtil2 =(Button)view.findViewById(R.id.btnUtil2);
        Button btnUtil3 =(Button)view.findViewById(R.id.btnUtil3);

        txtGuide.setText("Hello Utils!!!");

        btnUtil1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set user pref
                SharePrefUtil.getInstance(getActivity()).set(SharePrefUtil.Key.VERSION_KEY, "test" + System.currentTimeMillis());
                //get user pref
                txtDescription1.setText("getVersionKey from pref: " + SharePrefUtil.getInstance(getActivity()).get(SharePrefUtil.Key.VERSION_KEY));
                //remove user pref
                SharePrefUtil.getInstance(getActivity()).remove(SharePrefUtil.Key.VERSION_KEY);
                txtDescription1.setText(txtDescription1.getText().toString() + "\nremoveVersionKey from pref: " + SharePrefUtil.getInstance(getActivity()).get(SharePrefUtil.Key.VERSION_KEY));

            }
        });


        btnUtil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    GLDialog dialog = GLDialog.newInstance(false,
                            "title", "description",
                            getString(R.string.ok), getString(R.string.cancel), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();
                                    if (id == R.id.dialog_button1) {
                                        Utils.showToast(getActivity(), "ok message");
                                    } else {
                                        Utils.showToast(getActivity(), "cancel message");
                                    }
                                }
                            });
                    dialog.show(getFragmentManager(), null);
                count++;
            }
        });

        /*
        TimerService
        1. create TimerService.java
        2. register TimerService in manifest
           ```
           <service
            android:name="com.geekylab.general.network.TimerService"
            android:exported="false"/>
            ```
        3. in activity,
           3.1 declare broadcast receiver
           ```private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String minutes = intent.getStringExtra(TimerService.MINUTES.class.getName());
                        Utils.showToast(MainActivity.this, "my minutes: "+minutes);
                    }
            };```

            3.2 register receiver in onCreate
             ```LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter(TimerService.class.getName()));```
        4. start your service at your desire place
            ```startService(new Intent(this, TimerService.class));  ```
         */

        btnUtil3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(new Intent(getActivity(), TimerService.class));
            }
        });


    }


}
