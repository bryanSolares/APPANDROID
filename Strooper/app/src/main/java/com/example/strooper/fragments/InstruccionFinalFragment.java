package com.example.strooper.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.strooper.R;

public class InstruccionFinalFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btnFinInstruccion;
    private View vista;
    private Activity actividad;

    public InstruccionFinalFragment() {
        // Required empty public constructor
    }

    public static InstruccionFinalFragment newInstance(String param1, String param2) {
        InstruccionFinalFragment fragment = new InstruccionFinalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_instruccion_final, container, false);
        btnFinInstruccion = vista.findViewById(R.id.btnFinInstruccion);

        btnFinInstruccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actividad.finish();
            }
        });
        return vista;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            actividad = (Activity) context;
        }
    }
}
