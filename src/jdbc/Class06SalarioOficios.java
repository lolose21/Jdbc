   // Pedimos un oficio por teclado
// Pedimos un incremento salarial
// Modificar los empleados con un salario
//+ incremento del oficio introducido.
//6 empleados actualizados
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class06SalarioOficios {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("app modificar salario");
        System.out.println("------------------------");
        System.out.println("introduzca oficio ");
        String oficio = teclado.nextLine();
        System.out.println("introduzca el incremento");
        String salario = teclado.nextLine();
        int incremento = Integer.parseInt(salario);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        Statement st = cn.createStatement();
        String consulta
                //update emp set salario = salario + 1
                //where oficio = 'DIRECTOR';
                = "update emp set salario = salario +" + incremento + "where oficio ='"
                + oficio + "' ";
        int incrementos = st.executeUpdate(consulta);
        System.out.println(" salarios incrementados" + incrementos);
        cn.close();
    }
}
