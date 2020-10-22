package net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv.model;

public class nota {
    int id;
    int tipo;
    String descripcion;

    public nota(int tipo, String descripcion) {
        this.id = (int)Math.random()*1000;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
