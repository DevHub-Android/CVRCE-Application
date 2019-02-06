package com.devhub.official.cvrceapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class addCommentAuthorityFragment  extends android.support.v4.app.DialogFragment  {
    private EditText mEditText;
    private Button sendBtn;
    private ImageButton closeBtn;
    public addCommentAuthorityFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static addCommentFragment newInstance() {
        addCommentFragment fragment = new addCommentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_comment, container, false);
        mEditText = (EditText) view.findViewById(R.id.entComment);
        sendBtn = view.findViewById(R.id.addComment);
        closeBtn = view.findViewById(R.id.btnclose);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(mEditText.getText()))
                {
                    postComment(mEditText.getText().toString());
                }else{
                    mEditText.setError("Write Something!");
                    mEditText.requestFocus();
                }
            }
        });
        return view;
    }

    public void postComment(String comment){
        ComplaintsAuthorityActivity activity = (ComplaintsAuthorityActivity) getActivity();
        activity.onFinishEditDialog(comment);
        this.dismiss();
    }
}