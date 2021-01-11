/*   Vamos a incrementar el salario de los empleados
por su departamento.
    Pediremos el NOMBRE del departamento
    Actualizaremos los datos de los empleados del departamento.
Empleados modificados: 6
    Preguntaremos de nuevo si queremos modificar.
NO ES NECESARIO MOSTRAR LOS REGISTROS (opcional)
Versión 2: Mostramos los empleados modificados*/
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class12UpdateSalariosEmp {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String respuesta = "salir";

        while (respuesta.equalsIgnoreCase("salir")) {

            System.out.println("introduzca departamento a modificar");
            String nombre = teclado.nextLine();

            System.out.println("introduzca incremento salarial");
            String datosalario = teclado.nextLine();
            int incremento = Integer.parseInt(datosalario);

            String update
                    = "update emp" + " "
                    + "set salario = salario + ?" + " "
                    + "where" + " " + "dept_no = (select dept_no from dept"
                    + " " + "where lower(Dnombre) = lower(?))";
            PreparedStatement pst = cn.prepareStatement(update);
            pst.setInt(1, incremento);
            pst.setString(2, nombre);
            int modificados = pst.executeUpdate();
            System.out.println("salarios modificados" + "=  " + modificados
                    + " -- " + incremento + " -- " + nombre);

            String consulta
                    = "select from emp" + " "
                    + "salario  " + " "
                    + "where" + " " + "dept_no = (select dept_no from dept"
                    + " " + "where lower(Dnombre) = lower(?))";
            //PreparedStatement pst = cn.prepareStatement(consulta);
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("DNOMBRE");
                String sal = rs.getString("SALARIO");

            }
            System.out.println(" desea modificar ,o, salir");
            respuesta = teclado.nextLine();

        }
    }
}
