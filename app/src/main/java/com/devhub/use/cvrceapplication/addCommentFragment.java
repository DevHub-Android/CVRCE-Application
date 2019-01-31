package com.devhub.use.cvrceapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class addCommentFragment extends DialogFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private EditText mEditText;
    private Button sendBtn;
    private ImageButton closeBtn;

    public addCommentFragment() {
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
                    mEditText.setError("Write Something!!");
                    mEditText.requestFocus();
                }
            }
        });
        return view;
    }
    public void postComment(String comment){
        ComplaintsActivity activity = (ComplaintsActivity) getActivity();
        activity.onFinishEditDialog(mEditText.getText().toString());
        this.dismiss();
    }





}
