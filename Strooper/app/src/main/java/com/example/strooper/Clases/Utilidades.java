package com.example.strooper.Clases;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.strooper.Clases.Vo.AvatarVo;
import com.example.strooper.Clases.Vo.JugadorVO;
import com.example.strooper.R;

import java.util.ArrayList;

public class Utilidades {

    public static ArrayList<AvatarVo> listaAvatars;
    public static ArrayList<JugadorVO> listaJugadores;
    public static AvatarVo avatarSeleccion;
    public static int avatarIdSeleccion = 0;

    public static final String NOMBRE_DB ="stropeers_db";
    public static final String TABLA_JUGADOR = "jugador";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_GENERO = "genero";
    public static final String CAMPO_AVATAR = "avatar";

    public static final String CREAR_TABLA_JUGADOR = "CREATE TABLE ".concat(TABLA_JUGADOR).concat("(").concat(CAMPO_ID).concat(" INTEGER PRIMARY KEY, ")
            .concat(CAMPO_NOMBRE).concat(" TEXT, ").concat(CAMPO_GENERO).concat(" TEXT, ").concat(CAMPO_AVATAR).concat(" INTEGER)");

    public static void obtenerListaAvatars(){
        listaAvatars = new ArrayList<>();

        listaAvatars.add(new AvatarVo(1,R.drawable.avatar1,"Avatar1"));
        listaAvatars.add(new AvatarVo(2,R.drawable.avatar2,"Avatar2"));
        listaAvatars.add(new AvatarVo(3,R.drawable.avatar3,"Avatar3"));
        listaAvatars.add(new AvatarVo(4,R.drawable.avatar4,"Avatar4"));
        listaAvatars.add(new AvatarVo(5,R.drawable.avatar5,"Avatar5"));
        listaAvatars.add(new AvatarVo(6,R.drawable.avatar6,"Avatar6"));
        listaAvatars.add(new AvatarVo(7,R.drawable.avatar7,"Avatar7"));
        listaAvatars.add(new AvatarVo(8,R.drawable.avatar8,"Avatar8"));
        listaAvatars.add(new AvatarVo(9,R.drawable.avatar9,"Avatar9"));
        listaAvatars.add(new AvatarVo(10,R.drawable.avatar10,"Avatar10"));
        listaAvatars.add(new AvatarVo(11,R.drawable.avatar11,"Avatar11"));
        listaAvatars.add(new AvatarVo(12,R.drawable.avatar12,"Avatar12"));
        listaAvatars.add(new AvatarVo(13,R.drawable.avatar13,"Avatar13"));
        listaAvatars.add(new AvatarVo(14,R.drawable.avatar14,"Avatar14"));
        listaAvatars.add(new AvatarVo(15,R.drawable.avatar15,"Avatar15"));
        listaAvatars.add(new AvatarVo(16,R.drawable.avatar16,"Avatar16"));
        listaAvatars.add(new AvatarVo(17,R.drawable.avatar17,"Avatar17"));
        listaAvatars.add(new AvatarVo(18,R.drawable.avatar18,"Avatar18"));
        listaAvatars.add(new AvatarVo(19,R.drawable.avatar19,"Avatar19"));
        listaAvatars.add(new AvatarVo(20,R.drawable.avatar20,"Avatar20"));
        listaAvatars.add(new AvatarVo(21,R.drawable.avatar21,"Avatar21"));
        listaAvatars.add(new AvatarVo(22,R.drawable.avatar22,"Avatar22"));
        listaAvatars.add(new AvatarVo(23,R.drawable.avatar23,"Avatar23"));
        listaAvatars.add(new AvatarVo(24,R.drawable.avatar24,"Avatar24"));
        listaAvatars.add(new AvatarVo(25,R.drawable.avatar25,"Avatar25"));
        listaAvatars.add(new AvatarVo(26,R.drawable.avatar26,"Avatar26"));
        listaAvatars.add(new AvatarVo(27,R.drawable.avatar27,"Avatar27"));

        avatarSeleccion = listaAvatars.get(0);
    }

    public static void consultarListaJugadores(Activity activity){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(activity,NOMBRE_DB,null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        JugadorVO jugadorVO;
        listaJugadores = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_JUGADOR,null);

        while (cursor.moveToNext()){
            jugadorVO = new JugadorVO();
            jugadorVO.setId(cursor.getInt(0));
            jugadorVO.setNombre(cursor.getString(1));
            jugadorVO.setGenero(cursor.getString(2));
            jugadorVO.setAvatar(cursor.getInt(3));

            listaJugadores.add(jugadorVO);
        }

        db.close();
    }


}
