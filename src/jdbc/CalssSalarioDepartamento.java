package jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class CalssSalarioDepartamento {

    public static void main(String[] args) throws SQLException {

        Scanner teclado = new Scanner(System.in);
        System.out.println("introduzca numero departamento");
        String datodept = teclado.nextLine();
        int deptno = Integer.parseInt(datodept);
        System.out.println("introduzca incremento");
        String datoincremento = teclado.nextLine();
        int incremento = Integer.parseInt(datoincremento);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        Statement st = cn.createStatement();
        String consulta
                = "update emp set salario = salario + " + incremento
                + " where dept_no = " + deptno;
        int afectados = st.executeUpdate(consulta);
        System.out.println("empleados modificados" + afectados);
        System.out.println("-----------------------------");
        //leemos los registros
        //select apellido , salario from emp where dept_no =10
        String consultaselect
                = "select apellido , salario from emp where dept_no =" + deptno;
        ResultSet rs = st.executeQuery(consultaselect);
        while (rs.next()) {
            String apellido = rs.getString("apellido");
            String salario = rs.getString("salario");
            System.out.println(apellido + "--" + salario);

        }
        rs.close();
        cn.close();
        System.out.println("fin del programa");
    }
}
