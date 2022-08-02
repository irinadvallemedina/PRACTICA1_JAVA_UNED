package es.uned.master.java.registrodeusuarios.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDeLaCuentaDAO {
    public static Connection con;
    public static Conexion cn = new Conexion();
    public static PreparedStatement ps;
    public static ResultSet rs;

    public EstadoDeLaCuentaDAO(){
    }

    public static List<EstadoDeLaCuenta> listarEstado() {
        List<EstadoDeLaCuenta> status = new ArrayList<>();
        String sqlDep ="select * from Estado";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sqlDep);
            rs = ps.executeQuery();
            //Bucle while para recorrer los registros de los usuarios en la bbdd
            while (rs.next()) {
                int id = rs.getInt("id");
                String statusCta=rs.getString("estado");
              //  System.out.println(id + "-"+statusCta);
                status.add(new EstadoDeLaCuenta(id,statusCta));
            }

        }catch(SQLException err){
            //throw new LibreriaException("Existe  una incidencia al realizar la Consulta."+err);
            throw new UsuarioException("020: Existe  una incidencia al realizar la Consulta.");
        }catch(Exception err){
            //throw new LibreriaException("Error indefinido."+err);
            throw new UsuarioException("021: Existe una incidencia en la Base de Datos.");
        }finally{
            if (con!=null){
                try{
                    con.close();
                }catch(Exception e){}
            }
        }
        return status;
    }

}
