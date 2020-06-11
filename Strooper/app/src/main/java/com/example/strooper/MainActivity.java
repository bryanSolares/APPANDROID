package com.example.strooper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.strooper.Clases.ConexionSQLiteHelper;
import com.example.strooper.Clases.PreferenciasJuego;
import com.example.strooper.Clases.Utilidades;
import com.example.strooper.Interfaces.IComunicaFragments;
import com.example.strooper.actividades.AjustesActivity;
import com.example.strooper.actividades.ContenedorInstruccionesActivity;
import com.example.strooper.fragments.GestionJugadorFragment;
import com.example.strooper.fragments.InicioFragment;
import com.example.strooper.fragments.RegistroJugadorFragment;

public class MainActivity extends AppCompatActivity implements IComunicaFragments {

    private Fragment fragmentInicio,registroJugadorFragment,gestionJugadorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this,R.xml.preferencias,false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,this);

        Utilidades.obtenerListaAvatars();
        Utilidades.consultarListaJugadores(this);
        fragmentInicio = new InicioFragment();
        registroJugadorFragment = new RegistroJugadorFragment();
        gestionJugadorFragment = new GestionJugadorFragment();

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this,Utilidades.NOMBRE_DB,null,1);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }

    public AlertDialog dialogoGestionDeUsuario(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Gestionar Jugador")
                .setMessage("Indique si desea REGISTRAR un nuevo jugador o si desea seleccionar uno ya existente .\n\n "
                + "También podrá modificar un jugador desde la opción SELECCIONAR")
                .setNegativeButton("REGISTRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,registroJugadorFragment).commit();
                    }
                })
                .setPositiveButton("SELECCIONAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, gestionJugadorFragment).commit();
                    }
                });
        return  builder.create();
    }

    @Override
    public void iniciarJuego() {
        Toast.makeText(getApplicationContext(),"Iniciar Juego",Toast.LENGTH_LONG).show();
    }

    @Override
    public void llamarAjuste() {
        Intent intent = new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }

    @Override
    public void consultarRanking() {
        Toast.makeText(getApplicationContext(),"Iniciar Ranking",Toast.LENGTH_LONG).show();
    }

    @Override
    public void consultarInstrucciones() {
        Intent intent = new Intent(this, ContenedorInstruccionesActivity.class);
        startActivity(intent);
    }

    @Override
    public void gestionarUsurio() {
        dialogoGestionDeUsuario().show();
    }

    @Override
    public void consultarInformacion() {
        Toast.makeText(getApplicationContext(),"Iniciar Informacion",Toast.LENGTH_LONG).show();
    }
}
