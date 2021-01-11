//<editor-fold defaultstate="collapsed" desc="pakete procedure">
/* create or replace package pqtipos
--tenemos q declarar un cursor con
--el tipo de consulta q devolvera
is
type registroplantilla is record(
apellido plantilla.apellido%type
,nombre hospital.nombre%type
,funcion plantilla.funcion%type
, turno varchar2(30)
);
type registroempleados is record(
apellido emp.apellido%type
, nombre   dept.dnombre%type
, localidad      dept.loc%type
, total int
);
type cursordeptemp is ref cursor return registroempleados;
type cursorplantillahosp is ref cursor return registroplantilla;
 type cursorempleados is ref cursor return emp%rowtype;
 type cursordept is ref cursor return dept%rowtype;
end pqtipos;
 -----------------
  select * from emp
   select * from dept
   ---------------------------------
   create or replace procedure datosdeptemp
   ( p_nombre dept.dnombre%type
   ,consultaemp out pqtipos.cursordeptemp
   )
   as
   begin
     open consultaemp for
     select emp.apellido , nvl(emp.salario, 0) +
     nvl(emp.comision, 0 ) as total
      , dept.dnombre , dept.loc
     from emp
     inner join dept
     on
     emp.dept_no = dept.dept_no
     where lower(dept.dnombre) = lower(p_nombre);

   end;
 */
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="con count">
/*create or replace procedure datosdeptemp
   ( p_nombre dept.dnombre%type
   ,consultaemp out pqtipos.cursordeptemp
   ,p_personas out int , p_sumasalarial out int)
   as
   begin
     select sum(emp.salario) , count(emp.emp_no)
     into p_sumasalarial , p_personas
     from emp
     inner join dept on emp.dept_no = dept.dept_no
     where lower(dept.dnombre)=lower(p_nombre);
     open consultaemp for
     select emp.apellido , nvl(emp.salario, 0) +
     nvl(emp.comision, 0 ) as total
      , dept.dnombre , dept.loc
     from emp
     inner join dept
     on
     emp.dept_no = dept.dept_no
     where lower(dept.dnombre) = lower(p_nombre);

   end;
 */
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="procedimiento para array">
// create or replace procedure insertemp
// (p_empno out emp.emp_no%type
// , p_incremento int
// )as
//begin
// update emp set salario = salario + p_incremento
// where emp_no = p_empno;
// end;
//</editor-fold>
package jdbc;
//<editor-fold defaultstate="collapsed" desc="import">

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.internal.OracleTypes;

//</editor-fold>
public class Class23CursorRegistroEmpleados {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Integer> empno = new ArrayList();
        //empno.addAll(insertemp);
        System.out.println("app para departamento");
        System.out.println("----------------------");
        System.out.println("nombre del departamento");
        String nombre = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consultaproc
                = "{call datosdeptemp(?, ? , ?,?)}";
        CallableStatement cst
                = cn.prepareCall(consultaproc);
        cst.setString(1, nombre);

        cst.registerOutParameter(2, OracleTypes.CURSOR);
        cst.registerOutParameter(3, java.sql.Types.INTEGER);
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        String consultaproc2
                = "{call insertemp (?,?)}";
        cst.execute();
        int personas = cst.getInt(3);
        int sumasalarial = cst.getInt(4);
        ResultSet rs = (ResultSet) cst.getObject(2);
        while (rs.next()) {
            String ape = rs.getString("apellido");
            String nom = rs.getString("dnombre");
            String loc = rs.getString("loc");
            String total = rs.getString("total");
            System.out.println(ape + " -- " + nom + " -- " + loc + " - - "
                    + total);
        }
        System.out.println("introduzca numero de empleado");
        String datonumero = teclado.nextLine();
        int numeroemp = Integer.parseInt(datonumero);
        System.out.println("introduzca incremen");

        cn.close();
        rs.close();
    }
}
