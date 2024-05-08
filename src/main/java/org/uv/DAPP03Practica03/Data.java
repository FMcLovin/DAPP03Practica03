package org.uv.DAPP03Practica03;

public class Data {

    private String clave;
    private String nombre;
    private String descripcion;

    public Data(String clave, String nombre, String descripcion) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
