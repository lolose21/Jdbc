/*Pedimos la función y un incremento salarial.
    Incrementamos el salario de la plantilla de dicha función (Update)
    Mostramos la plantilla de dicha función.*/
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class10ModificarSalarioPlantilla {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("introduzca una funcion");
        String funcion = teclado.nextLine();
        System.out.println("introduzca incremento de salario");
        String datoincremento = teclado.nextLine();
        int incremento = Integer.parseInt(datoincremento);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LoCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String update
                //update plantilla set salario = salario + 1
                //where LOWER(funcion) = 'enfermero'
                = "update plantilla set salario = salario + ? where "
                + "lower(funcion) = ?";
        PreparedStatement pst = cn.prepareStatement(update);
        pst.setInt(1, incremento);
        pst.setString(2, funcion);
        int nuevosalario = pst.executeUpdate();
        System.out.println(nuevosalario + " salarios incrementados");
        String consulta
                = "select * from plantilla  where lower(funcion) =lower(?)";
        pst = cn.prepareStatement(consulta);
        pst.setString(1, funcion);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String dfuncion = rs.getString("funcion");
            String salario = rs.getString("salario");
            String ape = rs.getString("apellido");
            System.out.println(dfuncion + " -- " + salario
                    + "--" + ape);
        }
        rs.close();
        cn.close();
    }

}
