 // Pedimos el Oficio y un incremento salarial
// Incrementamos mediante un paquete los empleados de dicho oficio.
// Mostramos los empleados del oficio.
package jdbc;
//<editor-fold defaultstate="collapsed" desc="procedimiento">
//create or replace procedure subsalario
//(  p_incremento int
// , p_nombre varchar2
//)as
// begin
//   update emp set salario = salario + p_incremento

//where lower(oficio) = p_nombre;
//end;
//</editor-fold>
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class16SubirSalarioPaquete {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("app incremento salarial");
        System.out.println("-----------------------");
        System.out.println("introduzca incremento");
        String datonumero = teclado.nextLine();
        int numero = Integer.parseInt(datonumero);
        System.out.println("introduzca oficio a incrementar");
        String oficio = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String subida
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(subida, "SYSTEM", "oracle");
        String consultaproc
                = "{call subsalario(? , ?)}";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setInt(1, numero);
        cst.setString(2, oficio);
        cst.executeUpdate();
        String consulta
                //" select salario , oficio \n" +
                // "  from emp\n" +
                // "  where oficio = 'director'"
                = "select salario , oficio from emp where lower(oficio) = lower(?)";
        PreparedStatement pst = cn.prepareStatement(consulta);
        pst.setString(1, oficio);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String sala = rs.getString("salario");
            String ofi = rs.getString("oficio");

            System.out.println(ofi + "-- " + sala);

        }
        cn.close();
        rs.close();
    }
}
