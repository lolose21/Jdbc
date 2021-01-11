package jdbc;

import java.sql.*;
import oracle.jdbc.OracleDriver;

public class Class01Jdbc {

    public static void main(String[] args) throws SQLException {
        //registrar el driver de acceso a oracle
        DriverManager.registerDriver(new OracleDriver());

        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        //a partir del driver,
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        //una vez que tenemos una conexion
        String consulta = "select * from emp";
        //para ejecutar sentencias se
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        //el metodo next() se mueve fila a fila
        //y Devuelve un boolean
        while (rs.next()) {
            String apellido = rs.getString("apellido");

            String fecha = rs.getString("fecha_alt");
            System.out.println(apellido + " " + fecha);
        }
        //una vez que hemos terminado de  ejecutar todo
        //debemos liberar los recursos
        rs.next();
        rs.next();
        rs.next();

        rs.close();
        cn.close();
        System.out.println("fin del programa");
    }

}
