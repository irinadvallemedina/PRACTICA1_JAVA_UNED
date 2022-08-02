package es.uned.master.java.registrodeusuarios.modelo;
/**
 *         * @author: Irina Medina Sierra
 *         * @version: 15/06/2022
 *         * @Description:  Esta clase define  el objeto usuario
 *         */

public class Usuario {
    public int id;
    public String nombre;
    public String usuario;
    public String password;
    public String email;
    public int idEstado;
    public  int idDepartamento;

    public  String estado;
    public  String departamento;




    public Usuario(){

}

    /**
     * * Constructor de los datos del usuario      *
     * @param id
     * @param nombre del usuario
     * @param usuario es el que utiliza para hacer login
     * @param password contraseña utilizada para hacer login
     * @param email  del usuario
     *             */
    public Usuario(int id, String nombre, String usuario, String password, String email, int idEstado, int idDepartamento) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.idEstado = idEstado;
        this.idDepartamento = idDepartamento;
    }

    /**
     * * Constructor de los datos del usuario      *
     * @param nombre del usuario
     * @param usuario es el que utiliza para hacer login
     * @param password contraseña utilizada para hacer login
     * @param email  del usuario
     *             */


    public Usuario(String nombre, String usuario, String password, String email, int idEstado, int idDepartamento) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.idEstado = idEstado;
        this.idDepartamento = idDepartamento;
    }
    //Cierre del constructor

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodos Getter y Setter del objeto usuario
     */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Usuario(int id, String nombre, String usuario, String password, String email, String estado, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.estado = estado;
        this.departamento = departamento;
    }
}


