package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class09ConsultasParametrizadas {

    public static void main(String[] args) throws SQLException {
        //vamos a realizar una aplicacion
        //donde buscaremos por oficio y departamento
        Scanner teclado = new Scanner(System.in);
        System.out.println("consultas parametrizadas");
        System.out.println("introduzca oficio");
        String oficio = teclado.nextLine();
        System.out.println("introduzca numero departamento");
        String datonumero = teclado.nextLine();
        int deptno = Integer.parseInt(datonumero);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consulta
                = "select * from emp where upper(oficio) = upper(?) and dept_no =?";
        PreparedStatement pst = cn.prepareStatement(consulta);
        pst.setString(1, oficio);
        pst.setInt(2, deptno);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String apellido = rs.getString("apellido");
            String datooficio = rs.getString("oficio");
            String dept = rs.getString("dept_no");
            System.out.println(apellido + "---" + oficio
                    + "-- " + dept);
        }
        rs.close();
        cn.close();

    }
}
