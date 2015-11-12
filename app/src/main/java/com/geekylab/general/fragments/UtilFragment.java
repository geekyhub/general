package com.geekylab.general.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.geekylab.general.R;
import com.geekylab.general.utils.SharePrefUtil;
import com.geekylab.general.utils.ui.GLDialog;


public class UtilFragment extends Fragment {

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
        Button btnUtil1 =(Button)view.findViewById(R.id.btnUtil1);
        Button btnUtil2 =(Button)view.findViewById(R.id.btnUtil2);
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


    }


}
