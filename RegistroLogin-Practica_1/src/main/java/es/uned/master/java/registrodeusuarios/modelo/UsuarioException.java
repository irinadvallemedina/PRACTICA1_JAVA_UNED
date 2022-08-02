package es.uned.master.java.registrodeusuarios.modelo;
/**
 *         * @author: Irina Medina Sierra
 *         * @version: 15/06/2022
 *         * @Description:  Esta clase define  el objeto UsuarioException donde captura los erroes de Excepcion
 *         y los muestra en la vista error.jsp
 *         */
public class UsuarioException extends RuntimeException{
    public UsuarioException(){
        super("BancaOnline Exception");
    }
    public UsuarioException(String err){
        super("ERROR "+ err);
    }
}