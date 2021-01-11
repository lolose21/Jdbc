 //Mostrar por pantalla todos los departamentos
//10 - CONTABILIDAD - ELCHE
//20 - INVESTIGACION - MADRID
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OracleDriver;

public class Class04SelectDepartamentos {

    public static void main(String[] args) throws SQLException {
        //registrar el driver de acceso a oracle
        DriverManager.registerDriver(new OracleDriver());

        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consulta = "select * from dept";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            String loc = rs.getString("LOC");
            String DNOMBRE = rs.getString("DNOMBRE");
            System.out.println(loc + "  " + DNOMBRE);

        }
        rs.next();
        rs.next();
        rs.next();
        rs.close();
        cn.close();
        System.out.println("fin del programa");
    }
}
