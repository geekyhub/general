package com.geekylab.general.utils.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.geekylab.general.fragments.BaseFragment;

import java.lang.ref.WeakReference;

/**
 * Created by User on 11/11/15.
 */
public class GLDialogFragment extends DialogFragment {
    private WeakReference<Object> reference = new WeakReference<Object>(null);
    private int requestCode;
    private String tag;

    public void setTarget(Object o, int requestCode) {
        reference = new WeakReference<Object>(o);
        this.requestCode = requestCode;
        this.getArguments().putInt("request-code", requestCode);
        if (o instanceof BaseFragment) {
            this.getArguments().putString("target-tag",
                    ((BaseFragment) o).getTag());
        }
    }

    @Override
    public void onActivityCreated(Bundle saved){
        super.onActivityCreated(saved);
        refreshTarget();
    }

    private void refreshTarget() {
        if(null!=reference.get()){
            return;
        }
        Bundle args = this.getArguments();
        if (args.containsKey("target-tag")) {
            this.tag = this.getArguments().getString("target-tag");
            try{
                Fragment fragment = getActivity().getSupportFragmentManager()
                        .findFragmentByTag(tag);
                if (null != fragment) {
                    reference = new WeakReference<Object>(fragment);
                }
            }
            catch(Exception e){}


        }
        if (args.containsKey("request-code")) {
            this.requestCode = args.getInt("request-code");

        }
    }

    public Object getTarget() {
        return reference.get();
    }

    public int getRequestCode() {
        return requestCode;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        if (getParentFragment() != null && getParentFragment() instanceof OnDismissListener) {
            OnDismissListener dismissListener = (OnDismissListener) getParentFragment();
            dismissListener.onDialogDismissed();
        }

        super.onDismiss(dialog);

    }

    public interface OnDismissListener {
        void onDialogDismissed();
    }
}
