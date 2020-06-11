package com.example.strooper.actividades.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.strooper.R;
import com.example.strooper.fragments.InstruccionAjusteFragment;
import com.example.strooper.fragments.InstruccionAyudaFragment;
import com.example.strooper.fragments.InstruccionFinalFragment;
import com.example.strooper.fragments.InstruccionInformacionFragment;
import com.example.strooper.fragments.InstruccionIniciarFragment;
import com.example.strooper.fragments.InstruccionNickNameFragment;
import com.example.strooper.fragments.InstruccionRanckingFragment;
import com.example.strooper.fragments.IntroduccionFragment;


public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static Fragment newInstance(int index) {
        Fragment fragment = null;

        switch (index){
            case 1: fragment = new IntroduccionFragment(); break;
            case 2: fragment = new InstruccionIniciarFragment(); break;
            case 3: fragment = new InstruccionAjusteFragment(); break;
            case 4: fragment = new InstruccionRanckingFragment(); break;
            case 5: fragment = new InstruccionAyudaFragment(); break;
            case 6: fragment = new InstruccionNickNameFragment(); break;
            case 7: fragment = new InstruccionInformacionFragment(); break;
            case 8: fragment = new InstruccionFinalFragment(); break;
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contenedor_instrucciones, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        return root;
    }
}