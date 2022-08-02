package es.uned.master.java.registrodeusuarios.modelo;
/**
 * Esta clase define el Data Access Objet (DAO)
 * @author: Irina Medina Sierra
 * @version: 15/06/2022
 * @description: Clase que permite validar, registrar, editar y borrar los datos en la base de datos.
 * Ésta clase es llamada desde los Controladores:
 * Controlador: para validar el inicio de sesión
 * Editar: para capturar el id de la vista y poder consultar en la bbdd el resto de datos y mostrarlo en la vista 'registrar.jsp'
 * Eliminar: para capturar el id desde el Crud y realizar la consulta y eliminar el registro seleccionado
 * ListarCRUD: donde se crea una colección de datos consultados en la Base de datos para mostrar en la vista 'clientesCRUD'
 * Registrador: para validar los datos introducidos por el usuario tanto en una alta nueva como en la actualización de datos
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static Connection con;
    public static Conexion cn = new Conexion();
    public static PreparedStatement ps;
    public static ResultSet rs;

    public UserDAO() {
    }

    /**
     * Este método se encarga de validar que el usuario y la contraseña son correcta
     * En caso de existir algun error, devuelve 0 al controlador y vuelve a cargar la pagina de inicio.
     * Para realizar la conexión con la Base de Datos, llama a la clase Conexion
     * Para capturar los datos llama la clase Usuario
     * Realiza la Query para comprobar que los datos son correctos.
     *
     * @return 1 si almacena correctamente y
     * @return 0 si existe un error
     */
    public static boolean validar(Usuario user) {
        //variable del metodo que controla si la validacion es correcta(true) o incorrecta(false)
        boolean comprueba = false;
        //variable de tipo String(sql) que almacena el query para luego realizar la consulta preparada
        String sql = "SELECT  * FROM BancaOnline.usuarios WHERE usuario=? AND password=?;";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            //Bucle while que hace la comprobación de que haya alguna coincidencia entre lo insertado por el usuario
            // y la base de datos. si no hay nada, no entra al bucle.
            while (rs.next()) {
                comprueba=true;//cambiar por enum de estado
                user.setUsuario(rs.getString("usuario"));
                user.setNombre(rs.getString("nombres"));
            }
            rs.close();
        } catch(SQLException err){
            //throw new LibreriaException("Existe  una incidencia al realizar la Consulta."+err);
            throw new UsuarioException("000: Existe  una incidencia al realizar la Consulta -validar-.");
        }catch(Exception err){
            //throw new LibreriaException("Error indefinido."+err);
            throw new UsuarioException("001: Existe una incidencia en la Base de Datos -validar-.");
        }
        return comprueba;
    }


    /**
     * Este método se encarga de validar que el usuario no exista en la base de datos
     * para evitar duplicados de usuarios en la bbdd
     *
     * @param user
     * @return true no Existe 
     * @return false si existe
     */
    public static boolean validarUsuario(String user) {
     //   System.out.println(user);
        String sql_comparador = "select usuario from BancaOnline.usuarios where usuario=?";
        boolean resultado = true;
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql_comparador);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
             //   System.out.println("El usuario existe");
                resultado = false;
            }
            rs.close();
        } catch(SQLException err){
            //throw new LibreriaException("Existe  una incidencia al realizar la Consulta."+err);
            throw new UsuarioException("002: Existe  una incidencia al realizar la Consulta -validar usuario-.");
        }catch(Exception err){
            //throw new LibreriaException("Error indefinido."+err);
            throw new UsuarioException("003: Existe una incidencia en la Base de Datos -validar usuairo-.");
        }finally{
            if (con!=null){
                try{
                    con.close();
                }catch(Exception e){}
            }
        }
     //   System.out.println("usuario es:" + resultado);
        return resultado;
    }

    /**
     * Este método se encarga de validar que el email del registro no exista en la BBDD
     *
     * @param email
     * @return true no existe
     * @return false existe en la bbdd
     */
    public static boolean validarEmail(String email) {
    //    System.out.println(email);
        String sql_comparador = "select usuario from BancaOnline.usuarios where email=?";
        boolean resultado = true;
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql_comparador);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
             //   System.out.println("El email existe");
                resultado = false;
            }
            rs.close();
        } catch(SQLException err){
            //throw new LibreriaException("Existe  una incidencia al realizar la Consulta."+err);
            throw new UsuarioException("004: Existe  una incidencia al realizar la Consulta -validar email-.");
        }catch(Exception err){
            //throw new LibreriaException("Error indefinido."+err);
            throw new UsuarioException("005: Existe una incidencia en la Base de Datos -validar email-.");
        }finally{
            if (con!=null){
                try{
                    con.close();
                }catch(Exception e){}
            }
        }
        return resultado;
    }

        /**
         * Este método se encarga de validar que la contraseña cumpla con los requisitos establecidos por la aplicacion:
         * 2 Mayusculas, 2 minusculas, 2 numericos, 2 caracteres especiales
         *
         * @param pass
         * @return true si es correcto
         * @return false porque no cumple con los requisitos
         */
    public static boolean validarPass(String pass) {
        //Especifica el maximo de caracteres del password
        int max = 8;
        //Especifica el minimo de mayusculas del password
        int minUppercase = 2;
        //Especifica el minimo de minusculas del password
        int minLowercase = 2;
        // Especifica el numero de digitos del password
        int numDigits = 2;
        // Especifica el numero de caracteres especiales
        int special = 2;
        // Contador de letras en mayusculas
        int uppercaseCounter = 0;
        // contador de letras en minusculas
        int lowercaseCounter = 0;
        // contador de digitos
        int digitCounter = 0;
        // contador de caracteres especiales
        int specialCounter = 0;
        //En el siguiente bucle, contamos cuantos caracteres tiene en mayusculas, minusculas, digitos y caracteres especiales
        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);
            if (Character.isUpperCase(c))
                uppercaseCounter++;
            else if (Character.isLowerCase(c))
                lowercaseCounter++;
            else if (Character.isDigit(c))
                digitCounter++;
            if (c >= 33 && c <= 46 || c == 64) {
                specialCounter++;
            }
        }
        return pass.length() >= max && uppercaseCounter >= minUppercase
                && lowercaseCounter >= minLowercase && digitCounter >= numDigits && specialCounter >= special;
    }

    /**
     * Este método se encarga de dar de alta en la Base de Datos los datos de los nuevos usuarios
     * Para realizar la conexión con la Base de Datos, llama a la clase Conexion
     * Para capturar los datos llama la clase Usuario
     * También valida que los datos no esten vacios
     *
     * @return 1 si almacena correctamente y
     * @return 0 si existe un error
     */
    public static int registrar(Usuario user) {
        int numeroRegistro = 0;
        //validamos del lado del servidor, que ninguno de los datos solicitados esten en blanco
        if (!user.getNombre().isEmpty() || !user.getEmail().isEmpty() || !user.getUsuario().isEmpty() || !user.getPassword().isEmpty()) {
            //en el siguiente if, llamamos al metodo que valida si el usuario ya existe,
            if (!validarEmail(user.getEmail())) {
                numeroRegistro=4;
              //  System.out.println(numeroRegistro);
            }
            if (!validarUsuario(user.getUsuario())) {
                numeroRegistro = 3;
             //   System.out.println(numeroRegistro);
                //en el siguiente if llamamos el metodo que valida que el password cumple con los requisitos
            } else if  (!validarPass(user.getPassword())) {
                numeroRegistro = 2;
            } else if (numeroRegistro == 0) {
              //  System.out.println("aqui entra");
                int ejecuta;
                String sql = "INSERT INTO BancaOnline.usuarios (nombres,email,usuario,password,idEstado,idDepartamento) VALUES (?,?,?,?,?,?);";
                try {
                  //  System.out.println("Estamos en el try");
                    con = cn.Conectar();
                    ps = con.prepareStatement(sql);
                    ps.setString(1, user.getNombre());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getUsuario());
                    ps.setString(4, user.getPassword());
                    ps.setInt(5,user.getIdEstado());
                    ps.setInt(6,user.getIdDepartamento());
                    ejecuta = ps.executeUpdate();
                  //  System.out.println(ejecuta);
                    numeroRegistro = 1;
                    rs.close();
                }
                catch(SQLException err){
                throw new UsuarioException("006: Existe  una incidencia al realizar la Consulta -registrar-.");
            }catch(Exception err){
                throw new UsuarioException("007: Existe una incidencia en la Base de Datos -registrar-.");
            }finally{
                if (con!=null){
                    try{
                        con.close();
                    }catch(Exception e){}
                }
            }
                }
            }
        return numeroRegistro;
    }

    public static List<Usuario> listar() {
        List<Usuario> list = new ArrayList<>();
        String sql = "SELECT  usuarios.idusuarios,usuarios.nombres,usuarios.usuario,usuarios.email,usuarios.password, estado.estado,departamento.departamento" +
                " FROM (BancaOnline.usuarios inner join  BancaOnline.estado on estado.id=usuarios.idEstado) inner join  BancaOnline.Departamento " +
                "on departamento.idDepartamento=usuarios.idDepartamento order by nombres";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //Bucle while para recorrer los registros de los usuarios en la bbdd
            while (rs.next()) {
              //  Usuario u = new Usuario();
                int id = rs.getInt("idusuarios");
                String nombre = rs.getString("nombres");
                String email = rs.getString("email");
                String usuario = rs.getString("usuario");
                String pass = rs.getString("password");
                String estado = rs.getString("estado");
                String departamento = rs.getString("departamento");
              //System.out.println(id + "-" + nombre + "-" + email + "-" + usuario + "-" + pass + "-" + estado + "-" + departamento);
                list.add(new Usuario(id,nombre,usuario,pass,email,estado, departamento));
            }
            rs.close();
        }     catch(SQLException err){
        throw new UsuarioException("008: Existe  una incidencia al realizar la Consulta -listar-.");
    }catch(Exception err){
        throw new UsuarioException("009: Existe una incidencia en la Base de Datos -listar-.");
    }finally{
        if (con!=null){
            try{
                con.close();
            }catch(Exception e){}
        }
    }
        return list;
    }

    /**
     * Este método se encarga de capturar el id del usuario seleccionado en el CRUD
     * ya sea para editar o eliminar
     *
     * @param id
     * @return objeto user con los datos extraidos de la bbdd
     * @return null en caso de error
     */
    public Usuario  seleccioanrUsuario(int id) {
        Usuario user=null;
        System.out.println("el id desde dao es:"+id);
        String sql_comparador = "select * from BancaOnline.usuarios where idusuarios=?";
        boolean resultado = true;
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql_comparador);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("usuario capturado");
                String nombre=rs.getString("nombres");
                String usuario=rs.getString("usuario");
                String email=rs.getString("email");
                String pass=rs.getString("password");//prueba
                int idEstado=rs.getInt("idEstado");
                int idDepartamento=rs.getInt("idDepartamento");
                System.out.println(id+" " +nombre+" " +email+" " +usuario+" " +pass+" " +idEstado+" " +idDepartamento);
                user=new Usuario(id,nombre,usuario,pass,email,idEstado,idDepartamento);

            }
            rs.close();
        } catch(SQLException err){
            //throw new LibreriaException("Existe  una incidencia al realizar la Consulta."+err);
            throw new UsuarioException("010: Existe  una incidencia al realizar la Consulta -seleccionar usuario-.");
        }catch(Exception err){
            //throw new LibreriaException("Error indefinido."+err);
            throw new UsuarioException("011: Existe una incidencia en la Base de Datos -seleccionar usuario-.");
        }finally{
            if (con!=null){
                try{
                    con.close();
                }catch(Exception e){}
            }

        }
        return user;
    }

    /**
     * Este método se encarga de capturar el id del usuario seleccionado en el CRUD
     * ya sea para editar o eliminar
     *
     * @param id
     * @return colmBorrada = true si se borra un registro
     * @return colmBorrada = false si hay algun error o el id no existe en la bbdd
     */
    public static boolean borrar(String id) {
        boolean colmBorrada = false;
        System.out.println(id);
        String sql_comparador = "delete  from BancaOnline.usuarios where idusuarios=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql_comparador);
            ps.setInt(1, Integer.parseInt(id));
            colmBorrada = ps.executeUpdate() > 0;
            con.close();

    } catch(SQLException err){
        //throw new LibreriaException("Existe  una incidencia al realizar la Consulta."+err);
        throw new UsuarioException("012: Existe  una incidencia al realizar la Consulta -borrar-.");
    }catch(Exception err){
        //throw new LibreriaException("Error indefinido."+err);
        throw new UsuarioException("013: Existe una incidencia en la Base de Datos -borrar-.");
    }finally{
        if (con!=null){
            try{
                con.close();
            }catch(Exception e){}
        }

            }
        return colmBorrada;
    }
    /**
     * Este método se encarga de dar de alta en la Base de Datos los datos de los nuevos usuarios
     * Para realizar la conexión con la Base de Datos, llama a la clase Conexion
     * Para capturar los datos llama la clase Usuario
     * También valida que los datos no esten vacios
     *
     * @return 1 si almacena correctamente y
     * @return 0 si existe un error
     */
    public static int actualizarUsuario(Usuario user) {
        int numeroRegistro = 0;
        //validamos del lado del servidor, que ninguno de los datos solicitados esten en blanco
        if (!user.getNombre().isEmpty() || !user.getEmail().isEmpty() || !user.getUsuario().isEmpty() || !user.getPassword().isEmpty()) {
            //en el siguiente if, llamamos al metodo que valida si el usuario ya existe,
            if (!validarPass(user.getPassword())) {
                numeroRegistro = 2;
            }
            if (numeroRegistro == 0) {
                System.out.println("aqui entra");
                int ejecuta;
                String sql = "UPDATE BancaOnline.usuarios SET nombres=?,email=?,usuario=?,password=?,idEstado=?,idDepartamento=?  WHERE idusuarios=?;";
                try {
                    System.out.println("Estamos en el try");
                    con = cn.Conectar();
                    ps = con.prepareStatement(sql);
                    ps.setString(1, user.getNombre());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getUsuario());
                    ps.setString(4, user.getPassword());
                    ps.setInt(5,user.getIdEstado());
                    ps.setInt(6,user.getIdDepartamento());
                    ps.setInt(7, user.getId());
                    ps.executeUpdate();
                    numeroRegistro = 1;
                    rs.close();
                } catch(SQLException err){
                    //throw new LibreriaException("Existe  una incidencia al realizar la Consulta."+err);
                    throw new UsuarioException("014: Existe  una incidencia al realizar la Consulta -actualizar-.");
                }catch(Exception err){
                    //throw new LibreriaException("Error indefinido."+err);
                    throw new UsuarioException("015: Existe una incidencia en la Base de Datos -actualizar-.");
                }finally{
                    if (con!=null){
                        try{
                            con.close();
                        }catch(Exception e){}
                    }
                }
            }
        }
        return numeroRegistro;
    }

}



