package com.example.strooper.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strooper.Adapters.AdaptadorAvatar;
import com.example.strooper.Adapters.AdaptadorJugador;
import com.example.strooper.Clases.ConexionSQLiteHelper;
import com.example.strooper.Clases.Utilidades;
import com.example.strooper.Clases.Vo.JugadorVO;
import com.example.strooper.Interfaces.IComunicaFragments;
import com.example.strooper.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;


public class GestionJugadorFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Activity actividad;
    private View vista;
    private IComunicaFragments IComunicaFragment;
    private RecyclerView recycleAvatars, recyclerJugadores;
    private JugadorVO jugadorSeleccionado;
    private ImageButton btnAtras, btnAyuda;
    private RadioButton radioM, radioF;
    private EditText campoNickName;
    private FloatingActionsMenu grupoBotones;
    private FloatingActionButton fabConfirmar, fabActualizar, fabEliminar;
    private TextView barraSeleccion;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GestionJugadorFragment() {
        // Required empty public constructor
    }

    public static GestionJugadorFragment newInstance(String param1, String param2) {
        GestionJugadorFragment fragment = new GestionJugadorFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_gestion_jugador, container, false);

        inicializarVariables();

        establecerLayouts();

        llenarAdaptarJugador();
        llenarAdaptorAvatars(0);

        establecerEventos();

        return vista;
    }

    private void inicializarVariables() {
        recycleAvatars = vista.findViewById(R.id.recyclerAvatarsId);
        recyclerJugadores = vista.findViewById(R.id.recyclerJugador);
        btnAtras = vista.findViewById(R.id.btnIcoAtras);
        btnAyuda = vista.findViewById(R.id.btnAyuda);
        campoNickName = vista.findViewById(R.id.campoNickName);
        radioF = vista.findViewById(R.id.radioFemenino);
        radioM = vista.findViewById(R.id.radioMasculino);
        grupoBotones = vista.findViewById(R.id.grupoFab);
        fabConfirmar = vista.findViewById(R.id.idFabConfirm);
        fabActualizar = vista.findViewById(R.id.idFabUpdate);
        fabEliminar = vista.findViewById(R.id.idFabDelete);
        barraSeleccion = vista.findViewById(R.id.barraSeleccionId);
    }

    private void establecerLayouts() {
        recyclerJugadores.setLayoutManager(new LinearLayoutManager(this.actividad));
        recyclerJugadores.setHasFixedSize(true);

        recycleAvatars.setLayoutManager(new GridLayoutManager(this.actividad,3));
        recycleAvatars.setHasFixedSize(true);
    }

    private void establecerEventos() {
        fabActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarJugador();
                llenarAdaptarJugador();
                grupoBotones.collapse();
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarJugador();
                grupoBotones.collapse();
            }
        });

        fabConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confimarJugador();
                grupoBotones.collapse();
            }
        });
    }

    /*EVENTOS PARA BOTONES FLOTANTES*/
    private void confimarJugador() {
        Toast.makeText(actividad,"Confirmar",Toast.LENGTH_SHORT).show();
    }

    private void eliminarJugador() {
        if (campoNickName.getText().toString() != null && !campoNickName.getText().toString().trim().equals("")){
            dialogoEliminar().show();
            grupoBotones.collapse();
        }else{
            Toast.makeText(actividad,"Debe seleccionar un usuario para eliminar!",Toast.LENGTH_LONG).show();
        }

    }

    public AlertDialog dialogoEliminar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
        builder.setTitle("Advertencia!!!!")
                .setMessage("Esta seguro de querer eliminar al Jugador Seleccionado? ".concat(jugadorSeleccionado.getNombre()))
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (campoNickName.getText().toString() != null && !campoNickName.getText().toString().trim().equals("")){
                            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(actividad,Utilidades.NOMBRE_DB,null,1);
                            SQLiteDatabase db = conn.getWritableDatabase();
                            int resultado = db.delete(Utilidades.TABLA_JUGADOR,Utilidades.CAMPO_ID.concat(" = ").concat(String.valueOf(jugadorSeleccionado.getId())),null);

                            if (resultado!=-1){
                                Toast.makeText(actividad,"Jugador Eliminado con Exito",Toast.LENGTH_SHORT).show();
                                campoNickName.setText("");
                                radioF.setChecked(false);
                                radioM.setChecked(false);
                                recyclerJugadores.scrollToPosition(jugadorSeleccionado.getId());
                                recycleAvatars.scrollToPosition(0);
                                Utilidades.consultarListaJugadores(actividad);
                            }
                            db.close();
                        }else{
                            Toast.makeText(actividad,"El jugador no se pudo eliminar",Toast.LENGTH_SHORT).show();
                        }

                        llenarAdaptarJugador();
                    }
                });
        return  builder.create();
    }

    private void actualizarJugador() {
        String genero = "";
        if(radioM.isChecked()){
            genero = "M";
        }else if(radioF.isChecked()){
            genero = "F";
        }else{
            genero = "No seleccionado";
        }

        if (!genero.equals("No seleccionado") && campoNickName.getText().toString() != null && !campoNickName.getText().toString().trim().equals("")){

            int avatarId = Utilidades.avatarSeleccion.getId();
            String nickName = campoNickName.getText().toString();

            ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(actividad.getApplicationContext(),Utilidades.NOMBRE_DB,null,1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE,nickName);
            values.put(Utilidades.CAMPO_GENERO,genero);
            values.put(Utilidades.CAMPO_AVATAR,avatarId);

            int idResultante = db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID.concat("=").concat(String.valueOf(jugadorSeleccionado.getId())),null);

            if(idResultante != -1){
                Toast.makeText(actividad.getApplicationContext(),"Jugador Actualizado Correctamente.",Toast.LENGTH_LONG).show();
                recyclerJugadores.scrollToPosition(jugadorSeleccionado.getId()-1);
                Utilidades.consultarListaJugadores(actividad);
                campoNickName.setText("");
            }else{
                Toast.makeText(actividad.getApplicationContext(),"No se puedo actualizar el jugador.",Toast.LENGTH_LONG).show();
            }

            db.close();

        }else{
            Toast.makeText(actividad.getApplicationContext(),"Debe verificar los datos para actualizar!",Toast.LENGTH_LONG).show();
        }
    }

    /*LLENAR COMBOS*/
    private void llenarAdaptarJugador() {
        AdaptadorJugador adaptadorJugador = new AdaptadorJugador(Utilidades.listaJugadores);
        adaptadorJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                //grupoBotones.collapse();
                grupoBotones.expand();
                jugadorSeleccionado = Utilidades.listaJugadores.get(recyclerJugadores.getChildAdapterPosition(vista));
                campoNickName.setText(jugadorSeleccionado.getNombre());
                if (jugadorSeleccionado.getGenero().equals("M")) {
                    radioM.setChecked(true);
                } else {
                    radioF.setChecked(true);
                }
                Utilidades.avatarSeleccion = Utilidades.listaAvatars.get(jugadorSeleccionado.getAvatar()-1);
                llenarAdaptorAvatars(jugadorSeleccionado.getAvatar());
            }
        });
        recyclerJugadores.setAdapter(adaptadorJugador);
    }

    private void llenarAdaptorAvatars(int avatarId) {
        Utilidades.avatarIdSeleccion = avatarId;
        AdaptadorAvatar miAdaptador = new AdaptadorAvatar(Utilidades.listaAvatars);
        recycleAvatars.scrollToPosition(avatarId-1);
        recycleAvatars.setAdapter(miAdaptador);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.actividad = (Activity) context;
            IComunicaFragment = (IComunicaFragments) this.actividad;
        }
    }
}
