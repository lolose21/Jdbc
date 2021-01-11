// Pedimos los datos para un nuevo departamento
//  Insertamos mediante un procedimiento
// Mostramos los departamentos después
//<editor-fold defaultstate="collapsed" desc="procedimiento">
/* create or replace procedure insertardept
  (p_dept_no dept.dept_no%type
  , p_dnombre dept.dnombre%type
  , p_loc dept.loc%type
  )as
  begin
    insert into dept values(
    p_dept_no , p_dnombre , p_loc
    );
     end;*/
//</editor-fold>
package jdbc;
//<editor-fold defaultstate="collapsed" desc="import">

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;
//</editor-fold>

public class Class14ProcedimientoInsertarDepartamento {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("app para insertar dept");
        System.out.println("-----------------------");
        System.out.println(" inserte numero de departamento");
        String datonumero = teclado.nextLine();
        int numero = Integer.parseInt(datonumero);
        System.out.println("inserte nombre del departamento");
        String nombre = teclado.nextLine();
        System.out.println("inserte localidad");
        String localidad = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String procint
                = "{call insertardept(?,?,?)}";
        CallableStatement cst = cn.prepareCall(procint);
        cst.setInt(1, numero);
        cst.setString(2, nombre);
        cst.setString(3, localidad);
        cst.executeUpdate();

        String consulta
                = "select * from dept";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            String dept = rs.getString("dept_no");
            String Dnom = rs.getString("dnombre");
            String local = rs.getString("loc");
            System.out.println(dept + "-- " + Dnom + "---" + local);

        }
        rs.close();
        cn.close();
    }
}
