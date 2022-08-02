package es.uned.master.java.registrodeusuarios.modelo;
        /**
        * @author: Irina Medina Sierra
        * @version: 15/06/2022
        * @Description:  La clase Conexión establece los parámetros necesarios para realizar la conexion de MySQL
        */
import java.sql.*;

public class Conexion {
    public Conexion(){}
    /**
     * Este método se encarga de conectar con la Base de Datos
     * En caso de existir algún error, envia un mensaje a la clse LibreriaException
     * Para realizar la conexión con la Base de Datos, utilizamos el driver y la url con el nombre de la bbdd
     * Para capturar los datos llama la clase Usuario
     * @return la conexión - variable con
     */
    public Connection Conectar() {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancaOnline?serverTimezone=UTC", "uned", "uned");

        }  catch (SQLException e){
            //System.out.println(e);
            throw new UsuarioException("018: Existe una incidencia en la Base de Datos |"+e);

        } catch (ClassNotFoundException e) {
            throw new UsuarioException("019: Existe una incidencia en la Base de Datos | "+e);

        } finally {
                try {
                    //Cuando cierro la conexion no hace bien la consulta,
               //  con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            return con;
    }
}