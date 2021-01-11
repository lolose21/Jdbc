//<editor-fold defaultstate="collapsed" desc="procedimiento , pakage">
/*
 create or replace package pqtipos
--tenemos q declarar un cursor con
--el tipo de consulta q devolvera
is
type registroplantilla is record(
apellido plantilla.apellido%type
,nombre hospital.nombre%type
,funcion plantilla.funcion%type
, turno varchar2(30)
);
type cursorplantillahosp is ref cursor return registroplantilla;
 type cursorempleados is ref cursor return emp%rowtype;
 type cursordept is ref cursor return dept%rowtype;
end pqtipos;

create or replace procedure datosplantillahospital
(consultaplantilla out pqtipos.cursorplantillahosp)
as
begin
  open consultaplantilla for
  select plantilla.apellido , hospital.nombre,plantilla.funcion,decode(
  turno , 'M', 'mañana' , 't' , 'tarde' , 'noche') as turno
  from plantilla
  inner join hospital
  on plantilla.hospital_cod = hospital.hospital_cod;
end;
 */
//</editor-fold>
package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleTypes;

public class Class22CursorRegistroPlantilla {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consultaproc
                = "{call datosplantillahospital(?)}";
        CallableStatement cst
                = cn.prepareCall(consultaproc);
        cst.registerOutParameter(1, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs = (ResultSet) cst.getObject(1);
        while (rs.next()) {
            String ape = rs.getString("apellido");
            String nom = rs.getString("nombre");
            String fun = rs.getString("funcion");
            String turno = rs.getString("turno");
            System.out.println(ape + " -- " + nom + " - -" + fun + " - - " + turno);
        }
        cn.close();
        rs.close();
    }
}
