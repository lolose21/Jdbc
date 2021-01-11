package jdbc;
//<editor-fold defaultstate="collapsed" desc="procedimiento">

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

/*create or replace procedure borrarempleado
(p_num emp.emp_no%type)
as
begin
  delete from emp where
  emp_no = p_num;
  end;*/
//</editor-fold>
public class Class13ProcedimientosAlmacenados {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("app procedimiento");
        System.out.println("--------------------");
        System.out.println("id empleado a eliminar");
        String datoempno = teclado.nextLine();
        int empno = Integer.parseInt(datoempno);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consultaproc
                = "{ call borrarempleado(?)}";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setInt(1, empno);
        int eliminados = cst.executeUpdate();
        System.out.println("empleados eliminados " + eliminados);
        cn.close();

    }

}
