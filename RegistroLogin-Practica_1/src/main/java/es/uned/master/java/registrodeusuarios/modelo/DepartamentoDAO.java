package es.uned.master.java.registrodeusuarios.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    public static Connection con;
    public static Conexion cn = new Conexion();
    public static PreparedStatement ps;
    public static ResultSet rs;

    public DepartamentoDAO(){
    }

    public static List<Departamento> listarDep() {
        List<Departamento> dep = new ArrayList<>();
        String sqlDep ="select * from departamento";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sqlDep);
            rs = ps.executeQuery();
            //Bucle while para recorrer los registros de los usuarios en la bbdd
            while (rs.next()) {
                int id = rs.getInt("idDepartamento");
                String departamento = rs.getString("departamento");
                //     System.out.println(id + "-"+departamento);
                dep.add(new Departamento(id, departamento));
            }
        } catch(SQLException err){
                //throw new LibreriaException("Existe  una incidencia al realizar la Consulta."+err);
                throw new UsuarioException("016: Existe  una incidencia al realizar la Consulta.");
            }catch(Exception err){
                //throw new LibreriaException("Error indefinido."+err);
                throw new UsuarioException("017: Existe una incidencia en la Base de Datos.");
            }finally{
                if (con!=null){
                    try{
                        con.close();
                    }catch(Exception e){}
                }
            }
        return dep;
    }
}

