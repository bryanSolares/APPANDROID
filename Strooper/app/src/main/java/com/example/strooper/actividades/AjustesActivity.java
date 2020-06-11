package com.example.strooper.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.strooper.R;
import com.example.strooper.fragments.AjustesFragment;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorAjuste, new AjustesFragment()).commit();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIcoAtras:
                finish();
                break;
        }
    }
}
