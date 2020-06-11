package com.example.strooper.Clases.Vo;

public class JugadorVO {

    private int id, avatar;
    private String nombre, genero;

    public JugadorVO() {
    }

    public JugadorVO(int id, int avatar, String nombre, String genero) {
        this.id = id;
        this.avatar = avatar;
        this.nombre = nombre;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
