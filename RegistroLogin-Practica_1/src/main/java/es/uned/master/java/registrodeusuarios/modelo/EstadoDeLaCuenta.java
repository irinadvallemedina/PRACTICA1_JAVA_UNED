package es.uned.master.java.registrodeusuarios.modelo;
/**
 *         * @author: Irina Medina Sierra
 *         * @version: 15/06/2022
 *         * @Description:  Esta clase define  el objeto Estado
 *         */
public class EstadoDeLaCuenta {

   int id;
   String Estado;

    public EstadoDeLaCuenta() {
    }

    public EstadoDeLaCuenta(int id, String estado) {
        this.id = id;
        Estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
