package com.geekylab.general.utils.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.geekylab.general.R;

/**
 * Created by User on 11/11/15.
 */
public class GLDialog extends GLDialogFragment {

    private static final String PARAM_LAYOUT_ID = "layoutId";
    private static final String PARAM_HAS_HEADER= "has_header";
    private static final String PARAM_TITLE = "title";
    private static final String PARAM_CONTENT = "content";
    private static final String PARAM_BUTTON1_TITLE = "button1_title";
    private static final String PARAM_BUTTON2_TITLE = "button2_title";
    private View.OnClickListener clickListener;


    public GLDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.ThemeNoTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int dialogLayout = getArguments().getInt(PARAM_LAYOUT_ID, R.layout.dialog_default);
        View view = inflater.inflate(dialogLayout, container);
        createDialog(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.dismiss();
    }

    private void createDialog(View view) {
        Bundle args = getArguments();
        boolean hasHeader = args.getBoolean(PARAM_HAS_HEADER, true);
        String title = args.getString(PARAM_TITLE);
        String content = args.getString(PARAM_CONTENT);
        String buttonTitle1 = args.getString(PARAM_BUTTON1_TITLE);
        String buttonTitle2 = args.getString(PARAM_BUTTON2_TITLE);


        final View.OnClickListener listener = clickListener;
        final View.OnClickListener finalListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) {
                    listener.onClick(v);
                }
                dismiss();
            }
        };

        if(!hasHeader){
            view.findViewById(R.id.dialog_title).setVisibility(View.GONE);
            view.findViewById(R.id.separator).setVisibility(View.GONE);
        }
        ((TextView) view.findViewById(R.id.dialog_title)).setText(title);
        ((TextView) view.findViewById(R.id.dialog_text)).setText(content);
        ((Button) view.findViewById(R.id.dialog_button1)).setText(buttonTitle1);
        view.findViewById(R.id.dialog_button1).setOnClickListener(finalListener);

        if (TextUtils.isEmpty(buttonTitle2)) {
            view.findViewById(R.id.dialog_button2).setVisibility(View.GONE);
            View greyLineVertical = view.findViewById(R.id.grey_line_vertical);
            if(null != greyLineVertical){
                greyLineVertical.setVisibility(View.GONE);
            }
        }else {
            ((Button) view.findViewById(R.id.dialog_button2)).setText(buttonTitle2);
            view.findViewById(R.id.dialog_button2).setOnClickListener(finalListener);
        }
        setCancelable(false);
        getActivity().getWindow().getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
    }

    public static GLDialog newInstance(String title, String content, String button1_title, String button2_title) {
        return newInstance(true,
                title,
                content,
                button1_title,
                button2_title, null);
    }

    public static GLDialog newInstance(Boolean has_header, String title, String content, String button1_title, String button2_title, android.view.View.OnClickListener click) {
        return newInstance(has_header,
                title,
                content,
                button1_title,
                button2_title,
                R.layout.dialog_default,
                click);
    }

    public static GLDialog newInstance(Boolean has_header,String title, String content, String button1_title,
                                       String button2_title, int layoutId, android.view.View.OnClickListener click) {
        GLDialog dialogGenericFragment = new GLDialog();
        Bundle args = new Bundle();
        args.putInt(PARAM_LAYOUT_ID, layoutId);
        args.putBoolean(PARAM_HAS_HEADER, has_header);
        args.putString(PARAM_TITLE, title);
        args.putString(PARAM_CONTENT, content);
        args.putString(PARAM_BUTTON1_TITLE, button1_title);
        args.putString(PARAM_BUTTON2_TITLE, button2_title);
        dialogGenericFragment.clickListener = click;
        dialogGenericFragment.setArguments(args);
        return dialogGenericFragment;
    }
}
