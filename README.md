# Correccion de la Prueba con Conexión a SQL Server

## Integrante:
- Melany Sangucho
- Bryan Delgado

## Conceptualización del tema
La conexión a SQL Server es un tanto especial, pues, se necesita de una serie de configuraciones para acceder a la base de datos. Estas configuraciones se realizan para dar acceso
al programador a SQL Server por un puerto en específico, el 1433. Este debe concederse como permiso necesario o regla de entrada dentro del Firewall de Windows y dentro del SQL Server Configure Managment.
- **Configuración de Firewall de Windows**
![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/config_firewall.png)

## Creación de nuevo usuario
Dado que SQL Server permite el acceso total a la administración de la DB solamente al usuario que contiene Windows como master, se debe crear un nuevo usuario, al cual se le van a agregar permisos de manipulación y visualización de la base creada.
- **Creación usuario y contraseña "prueba"**
![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/new_login.png)
- **Configuración del usuario "prueba"**
![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/config_user.png)

## Configuración de IntelliJ para acceso a SQL Server
- **Instalación del JDBC de SQL Server para el IDE**
![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/instal_libreria_jdbc_sqlserver.png)
- **Acceso a la conexión por la pestaña DataBase y aceptación de conexión**
![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/conexion_aceptada.png)
- **Verificación de la tabla de datos PERSONAS en la base de datos**
![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/verif_tabla.png)
- **Modificación del DB URL para acceso a la base de datos**  
Dado que SQL Server es estrcito en cuanto a las conexiones que se realicen hacia su entorno, la URL que conece la dirección de la base de datos debe agregar los parámetros de certificación de servidor y de encriptación, pues, SQL Server encripta estos mismos para mayor seguridad.
![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/modif_dburl.png)
***
## Realización de Búsqueda y CRUD con SQL Server
Para realizar este CRUD, se van a tomar datos ingresador a la base, se ingresarán nuevos datos o se eliminara. También se realizará las buscqueda de estos por medio de items específicos ya obtenidos.

- **Limpiar Formulario**  
  ![](images/limpiar.png)

### CRUD
- **Buscar Registro**
  - **Buscar por Código**  
  ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/persona_encontrada.png)
  - **Buscar por Signo**  
  ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/buscar_signo.png)
  - **Persona no encontrada**  
  ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/persona_no_encontrada.png)
- **Ingresar Registro**
  - **Registro Nuevo**  
    ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/ingresar_reg.png)
  - **Evidencia en tabla de nuevo registro**  
    ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/ingresar_reg_tab.png)
- **Actualizar Registro**
  - **Dato Modificado**  
    ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/act_reg.png)
  - **Evidencia en tabla de datos**  
    ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/act_reg_tab.png)
- **Borrar Regitro**
  - **Registro a Borrar**  
     ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/borrar_reg.png)
  - **Evidencia en tabla**  
     ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/borrar_reg_tab.png)
  - **Persona no encontrada tras eliminación**  
     ![](https://github.com/SanguchoMela/CorreccionPrueba2_SQLServer/blob/ca42714fb380d61ad43918e1dbfbf9586dbaa479/images/evide_borrar.png)
