 //REALIZAR UN PROCEDIMIENTO QUE INSERTARA
//UN NUEVO DEPARTAMENTO
//Y QUE MOSTRARA CON UN CURSOR LOS DATOS
//DE LOS DEPARTAMENTOS, INCLUIDO EL INSERTADO
//<editor-fold defaultstate="collapsed" desc="procedimiento y pakage">
/*
create or replace procedure selectdept
(p_nombre dept.dnombre%type
, p_loc dept.loc%type, consultadept out pqtipos.cursordept

)
as
v_deptno int;
begin
  select max(dept_no) + 1 into v_deptno
  from dept;
insert into dept values(
 v_deptno , p_nombre , p_loc
);
  commit;
  open consultadept for select * from dept;

end;

 */
 /*  create or replace package pqtipos
--tenemos q declarar un cursor con
--el tipo de consulta q devolvera
is type cursorempleados is ref cursor return emp%rowtype;
 type cursordept is ref cursor return dept%rowtype;
end pqtipos;*/
//</editor-fold>
package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleTypes;

public class Class21CursoresAccionDepertamentos {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("inserte un nombre de departamento");
        String nombre = teclado.nextLine();
        System.out.println("localidad del departamento");
        String localidad = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consultaproc = "{call  selectdept(?,?,?)}";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setString(1, nombre);
        cst.setString(2, localidad);
        cst.registerOutParameter(3, OracleTypes.CURSOR);
        cst.executeUpdate();
        ResultSet rs = (ResultSet) cst.getObject(3);
        while (rs.next()) {
            String deptno = rs.getString("dept_no");
            String nom = rs.getNString("dnombre");
            String loc = rs.getString("loc");
            System.out.println(deptno + "--- " + nom + "  --- " + loc);
        }
        cn.close();
        rs.close();

    }
}
