# PRACTICA1_JAVA_UNED
PROYECTO DE CRUD PARA CONTROL DE USUARIOS
AUTOR: IRINA MEDINA SIERRA         
FECHA: 01/08/2022

Descripción: La aplicación tiene como finalidad crear nuevos usuarios  con el fin  de almacenar en una base de datos, los datos de los usuarios, los permisos y el departamento al cual pertenecen dentro de la Empresa. Se ha utilizado los patrones Modelo Vista Controlador (MVC), Data Acces Object (DAO),  utilizando la herramienta de software Maven. 

El proyecto esta formado por distintos archivos JSP utilizando TagLibs, Servlet, Clases y conexiones ODBC utilizando MySQL.

<H3>DESCRIPCIÓN MVC</H3>

<H3>MODELO:</H3>

•	Conexión: Crea el entorno para conectarse a la base de datos, utilizando los parámetros establecidos.

•	Departamento: Clase que crea el objeto Departamento

•	DepartamentoDAO: envia la consulta a la Base de datos, para crear la colección de los Departamentos y poderlos listar en la Vista.

•	EstadoDeLaCuenta: Clase que crea el objeto Estado.

•	EstadoDeLaCuentaDAO: envia la consulta a la Base de datos, para crear la colección de los Estados y poderlos listar en la Vista.

•	UserDAO:  DATA ACCESS OBJECT. Clase principal que crea las consultas a la base de datos que los diferentes servlet solicitas para enviar a la Vista.

•	Usuario: clase que crea el objeto Usuario.

•	UsuarioException: clase que capturan las excepciones dentro de la aplicación.



<H3>VISTA:</H3>


•	css/style.css:  Hoja de estilo que junto con el FrameWork Bootstrap le dan estilo a la aplicación.

•	img/*:  diferentes imagenes como logotipo y favicon utilizado en la aplicación.

•	js/js.js: script que controla que los campos no esten en blanco del lado del cliente.

•	restringido/crudClientes.jsp: vista que utilizando taglibs muestra los datos en forma de listado con paginación de 10  por página.

•	error.jsp: vista que se muestra cuando se lanza una excepción en la aplicación.

•	exportar.jsp: vista que genera un archivo con todos los datos en Excel para ser exportado.

•	header.jsp: cabecera que se muestra en todas las vistas de la aplicación. 

•	index.jsp: vista principal, donde se le solicita el login y password para entrar a la aplicación CRUD.

•	Registro.jsp: vista que se utiliza tanto para el registro de nuevos usuarios como para editar usuarios existentes, utilizando taglibs.


<H3>CONTROLADOR:</H3>


•	Controlador: Servlet  que controla el inicio de sesión del usuario. Captura el nombre del usuario logeado y lo muestra en la vista.

•	Editar: Servlet que captura el id del usuario a editar y envia al modelo para realizar la conexión y crear el entorno de datos para mostrarlo a la vista para proceder a  ser modificados.

•	Eliminar: Servlet que captura el id del usuario a eliminar y envia al modelo para realizar la consulta de eliminación.

•	ListarCRUD: Servlet  que lista los usuarios que existen en la Base de datos, enviando la consulta a UsuariosDAO.

•	Logout: Servlet que destruye la sesión que se ha creado.

•	MiFiltro: Servlet que a traver de doFilter controla la sesión y permitir mostrar el contenido restringido (crudClientes)

•	MyErrorServlet: Servlet que genera la información para ser enviada a la  vista Error.jsp de los posibles errores generados en la aplicación

•	Registrador: Servlet que se utiliza tanto para las altas como para la edición. Controla y valida que los datos estén insertados correctamente.

