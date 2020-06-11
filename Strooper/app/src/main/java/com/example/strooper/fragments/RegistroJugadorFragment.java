package com.example.strooper.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strooper.Adapters.AdaptadorAvatar;
import com.example.strooper.Clases.ConexionSQLiteHelper;
import com.example.strooper.Clases.Utilidades;
import com.example.strooper.Interfaces.IComunicaFragments;
import com.example.strooper.R;
import com.getbase.floatingactionbutton.FloatingActionButton;


public class RegistroJugadorFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerAvatars;
    private View vista;
    private Activity actividad;
    private IComunicaFragments IComunicaFragments;
    private FloatingActionButton fabRegistro;
    private TextView campoNick;
    private RadioButton radioM,radioF;

    public RegistroJugadorFragment() {
    }


    public static RegistroJugadorFragment newInstance(String param1, String param2) {
        RegistroJugadorFragment fragment = new RegistroJugadorFragment();
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
        vista = inflater.inflate(R.layout.fragment_registro_jugador, container, false);

        recyclerAvatars = vista.findViewById(R.id.recyclerAvatarsId);
        fabRegistro = vista.findViewById(R.id.idFabRegistro);
        campoNick = vista.findViewById(R.id.campoNickName);
        radioM = vista.findViewById(R.id.radioMasculino);
        radioF = vista.findViewById(R.id.radioFemenino);

        recyclerAvatars.setLayoutManager(new GridLayoutManager(this.actividad,3));
        recyclerAvatars.setHasFixedSize(true);

        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarJugador();
            }
        });

        final AdaptadorAvatar adaptadorAvatar = new AdaptadorAvatar(Utilidades.listaAvatars);
        recyclerAvatars.setAdapter(adaptadorAvatar);
        return vista;
    }

    private void registrarJugador(){
        String genero = "";
        if(radioM.isChecked()){
            genero = "M";
        }else if(radioF.isChecked()){
            genero = "F";
        }else{
            genero = "No seleccionado";
        }

        if (!genero.equals("No seleccionado") && campoNick.getText().toString() != null && !campoNick.getText().toString().trim().equals("")){

            int avatarId = Utilidades.avatarSeleccion.getId();
            String nickName = campoNick.getText().toString();

            String registro = "Nombre: " + campoNick.getText().toString()+ "\n";
            registro+="Genero: " + genero+"\n";
            registro+="Avatar Id: "+Utilidades.avatarSeleccion.getId();

            ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(actividad.getApplicationContext(),Utilidades.NOMBRE_DB,null,1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE,nickName);
            values.put(Utilidades.CAMPO_GENERO,genero);
            values.put(Utilidades.CAMPO_AVATAR,avatarId);

            Long idResultante = db.insert(Utilidades.TABLA_JUGADOR,Utilidades.CAMPO_ID,values);

            if(idResultante != -1){
                Toast.makeText(actividad.getApplicationContext(),"Jugador Registrado Correctamente. \n ".concat(registro),Toast.LENGTH_LONG).show();
                campoNick.setText("");
            }else{
                Toast.makeText(actividad.getApplicationContext(),"No se puedo registrar el jugador con éxito. \n ".concat(registro),Toast.LENGTH_LONG).show();
            }

            db.close();

        }else{
            Toast.makeText(actividad.getApplicationContext(),"Debe verificar los datos de Registro!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            actividad = (Activity) context;
        }
    }
}
