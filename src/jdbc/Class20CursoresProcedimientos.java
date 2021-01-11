package jdbc;
//<editor-fold defaultstate="collapsed" desc="procedimiento paketes">

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleTypes;

/*  create or replace package pqtipos
--tenemos q declarar un cursor con
--el tipo de consulta q devolvera
is type cursorempleados is ref cursor return emp%rowtype;
end pqtipos;

create or replace procedure selectempleados
(consulta out pqtipos.cursorempleados)
as
begin
open consulta for
select * from emp;
end;*/
//</editor-fold>
public class Class20CursoresProcedimientos {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consulta = "{call selectempleados(?)}";
        //los cursores de salida son propios de oracle
        //necesitamos la enumeracion oracletypes.CURSOR
        CallableStatement cst = cn.prepareCall(consulta);
        cst.registerOutParameter(1, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs = (ResultSet) cst.getObject(1);
        while (rs.next()) {
            String ap = rs.getString("apellido");
            String ofi = rs.getString("oficio");
            String dept = rs.getString("dept_no");
            System.out.println(ap + " -- " + ofi + " --- " + dept);
        }
        cn.close();
        rs.close();
    }
}
