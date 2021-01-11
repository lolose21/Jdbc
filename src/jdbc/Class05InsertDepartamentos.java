package jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class05InsertDepartamentos {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("app INsetar departamento");
        System.out.println("-------------------------");
        System.out.println("introduzca numero departamento;");
        String datonumero = teclado.nextLine();
        int deptno = Integer.parseInt(datonumero);
        System.out.println("introduzca nombre del departamento");
        String nombre = teclado.nextLine();
        System.out.println("introduzca localidad");
        String localidad = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        Statement st = cn.createStatement();
        String consulta
                = "insert into dept values(" + deptno + " , '" + nombre
                + "' , '" + localidad + "')";
        int insertados = st.executeUpdate(consulta);
        System.out.println("departamentos insertados" + insertados);
        cn.close();
    }
}
