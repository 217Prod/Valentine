package com.example.bryan.valentine.Modlue.Modlue;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryan.valentine.R;

public class QuestionFragment extends Fragment {

    public QuestionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_question, container, false);
        initView();
        mbnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation zoom_in_finish = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_finish);
                mHeartZoom.startAnimation(zoom_in_finish);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().finish();
                        Toast.makeText(getActivity(), "Mãi bên nhau em nhé!!!", Toast.LENGTH_SHORT).show();
                    }
                },2000);


            }
        });
        mbnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Em phải ấn Yes, vì em yêu anh !!!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void initView() {
        mbnNo =  view.findViewById(R.id.button_no);
        mbnYes =  view.findViewById(R.id.button_yes);
        mHeartZoom =  view.findViewById(R.id.image_heart_zoom);
    }


    private View view;
    private Button mbnYes;
    private Button mbnNo;
    private ImageView mHeartZoom;


}
