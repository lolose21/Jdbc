//<editor-fold defaultstate="collapsed" desc="procedimiento">
/*create or replace procedure updatesalarioempleado
(p_empno emp.emp_no%type
, p_salario emp.salario%type
,p_comision emp.comision%type
, p_total out int)

as
begin
  update emp set salario = salario + p_salario
  , comision = comision + p_comision
  where emp_no = p_empno;
  commit;
  select salario + comision into p_total
  from emp
  where emp_no = p_empno;
  end;*/
//</editor-fold>
package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class17ProcedimientoDeSalida {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("app procedimiento salida");
        System.out.println("-------------------------");
        System.out.println("numero de empleado");
        String datoemp = teclado.nextLine();
        int empno = Integer.parseInt(datoemp);
        System.out.println("incremento salarial");
        String datosalario = teclado.nextLine();
        int salario = Integer.parseInt(datosalario);
        System.out.println("incremento comision ");
        String datocomision = teclado.nextLine();
        int comision = Integer.parseInt(datocomision);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consultaproc
                = "{call updatesalarioempleado(?,?,?,?)}";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setInt(1, empno);
        cst.setInt(2, salario);
        cst.setInt(3, comision);
        //la enumeracion de tipos es java.sql.types.TIPOS
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        //ahora ejecutamos el procedimiento
        cst.executeUpdate();
        //recuperamos el valor del parametro de salida
        int total = cst.getInt(4);
        System.out.println("la suma del salario y comision de"
                + empno + " es " + total);
        cn.close();
        System.out.println("fin del programa");

    }
}
