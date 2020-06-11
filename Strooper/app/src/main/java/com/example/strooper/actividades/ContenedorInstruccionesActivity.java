package com.example.strooper.actividades;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.strooper.R;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.example.strooper.actividades.ui.main.SectionsPagerAdapter;

public class ContenedorInstruccionesActivity extends AppCompatActivity {

    private LinearLayout linearPuntos;
    private TextView[] puntosSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_instrucciones);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        linearPuntos = findViewById(R.id.linearPuntos);
        agregarIndicadorPuntos(0);

        viewPager.addOnPageChangeListener(viewListener);
    }

    private void agregarIndicadorPuntos(int position) {
        puntosSlide = new TextView[8];
        linearPuntos.removeAllViews();

        int tam = puntosSlide.length;

        for(int i = 0; i < tam; i++){
            puntosSlide[i] = new TextView(this);
            puntosSlide[i].setText(Html.fromHtml("&#8226;"));
            puntosSlide[i].setTextSize(35);
            puntosSlide[i].setTextColor(getResources().getColor(R.color.colorBlancoTransparente));
            linearPuntos.addView(puntosSlide[i]);
        }

        if(tam > 0){
            puntosSlide[position].setTextColor(getResources().getColor(R.color.colorBlanco));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            agregarIndicadorPuntos(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}