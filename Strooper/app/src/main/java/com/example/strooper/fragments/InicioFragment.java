package com.example.strooper.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.strooper.Interfaces.IComunicaFragments;
import com.example.strooper.R;


public class InicioFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private CardView cardJugar;
    private CardView cardAjuste;
    private CardView cardRanking;
    private CardView cardAyuda;
    private CardView cardUser;
    private CardView cardInfo;

    private IComunicaFragments interfaceComunicaFragment;
    private View vista;
    private Activity actividad;

    public InicioFragment() {
    }

    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
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
        vista = inflater.inflate(R.layout.fragment_inicio, container, false);

        cardJugar = vista.findViewById(R.id.cardJugar);
        cardAjuste = vista.findViewById(R.id.cardAjustes);
        cardRanking = vista.findViewById(R.id.cardRanking);
        cardAyuda = vista.findViewById(R.id.cardInstrucciones);
        cardUser = vista.findViewById(R.id.cardUser);
        cardInfo = vista.findViewById(R.id.cardInfo);

        cardJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.iniciarJuego();
            }
        });

        cardAjuste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.llamarAjuste();
            }
        });

        cardRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.consultarRanking();
            }
        });

        cardAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.consultarInstrucciones();
            }
        });

        cardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.gestionarUsurio();
            }
        });

        cardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.consultarInformacion();
            }
        });

        return vista;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            actividad = (Activity) context;
            interfaceComunicaFragment = (IComunicaFragments) actividad;
        }
    }
}


