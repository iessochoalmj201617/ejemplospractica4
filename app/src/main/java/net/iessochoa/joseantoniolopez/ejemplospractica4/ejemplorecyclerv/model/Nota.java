package net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv.model;

import java.util.Objects;

/**
 * Esta clases representa el POJO de datos básicos  de la app.
 */
public class Nota {
    int id;
    int tipo;
    String descripcion;

    public Nota(int tipo, String descripcion) {
        //le asignamos un id único a la nueva nota. Como es una app de ejemplo, le asignamos un número aleatorio de 0 a 1000
        this.id = (int)(Math.random()*1000);
        //para no complicar la app, representamos 0->importante,1->Normal
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

    /**
     * Creamos el método equals que nos permitirá localizar fácilmente el objeto en un arraylist mediante indexOf
     * Dos notas son iguales si lo es su id
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return id == nota.id;
    }

}
