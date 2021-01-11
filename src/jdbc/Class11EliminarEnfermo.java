/*    Eliminamos un enfermo por su inscripción
    Mostrar los datos de los enfermos restantes.
Versión 2:
    Preguntar de nuevo si queremos eliminar más
enfermos.*/
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class11EliminarEnfermo {

    public static void main(String[] args) throws SQLException {

        Scanner teclado = new Scanner(System.in);
        String respuesta = "final";
        while (respuesta != "fin") {
            System.out.println("escriba fin para salir");
            respuesta = teclado.nextLine();

            System.out.println("introduzca inscipcion para borrar enfermo");
            String datoinscripcion = teclado.nextLine();
            int inscripcion = Integer.parseInt(datoinscripcion);
            DriverManager.registerDriver(new OracleDriver());
            String cadena
                    = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
            Connection cn
                    = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
            String delete
                    /*delete from enfermo2
                where lower(inscripcion) = 10995*/
                    = "delete from enfermo2 where lower(inscripcion) = ?";
            PreparedStatement pst = cn.prepareStatement(delete);
            pst.setInt(1, inscripcion);
            int borrados = pst.executeUpdate();
            System.out.println("enfermos borrados " + borrados);
            String consulta
                    = "select * from enfermo2";
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                String ins = rs.getString("inscripcion");
                String ape = rs.getString("apellido");
                String dir = rs.getString("direccion");
                String fe = rs.getString("fecha_nac");
                String se = rs.getString("sexo");
                String ns = rs.getString("nss");
                System.out.println(ins + " - " + ape + "- " + dir + " -"
                        + fe + " -" + se + " - " + ns);
            }

            rs.close();

            cn.close();

        }

    }

}
