package app.Fragments;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.R;
import app.Services.RadioService;


public class RadioFragment extends Fragment {
    //SERVICES
    public RadioService radioService;
    public boolean isBound = false;
    Activity mActivity;
    ImageView playBut ;
    ImageView pauseBut ;
    ImageView stopBut ;
    View v;

    public static RadioFragment newInstance() {
        RadioFragment fragment = new RadioFragment();
        return fragment;
    }

    public RadioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v = inflater.inflate(R.layout.fragment_radio, container, false);
       playBut = (ImageView) v.findViewById(R.id.playBut);
        pauseBut = (ImageView) v.findViewById(R.id.pausebut);
         stopBut = (ImageView) v.findViewById(R.id.stopBut);

        playBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioService != null) {
                    if (isBound = true) {
                        boolean radioState= radioService.playMyRadio();
                        if (radioState=true){
                            playBut.setVisibility(View.INVISIBLE);
                            pauseBut.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        pauseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioService != null) {
                    if (isBound = true) {
                        boolean radioState= radioService.pauseMyRadio();
                        if (radioState=true){
                            pauseBut.setVisibility(View.INVISIBLE);
                            playBut.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        stopBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioService != null) {
                    if (isBound = true) {
                        boolean radioState= radioService.stopMyRadio();
                        if (radioState=true){
                            pauseBut.setVisibility(View.INVISIBLE);
                            playBut.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
        //service declaration;

        ServiceConnection mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                RadioService.MyBinder binder = (RadioService.MyBinder) service;
                radioService = binder.getService();
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isBound = false;
            }
        };
        Intent intent = new Intent(getActivity(), RadioService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);


        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }




}