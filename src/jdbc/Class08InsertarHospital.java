/*Pedir todos los datos para insertar un hospital
    Insertamos el hospital
    Mostramos todos los hospitales
    Fin de programa
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class08InsertarHospital {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("inserte  nuevo hospital ");
        System.out.println("------------------------");
        System.out.println("introduzca codigo del hospital");
        String datocodigo = teclado.nextLine();
        int codigo = Integer.parseInt(datocodigo);
        System.out.println("introduzca nombre del hospital");
        String nombre = teclado.nextLine();
        System.out.println("introduzca direccion ");
        String direccion = teclado.nextLine();
        System.out.println("introduzca un telefono");
        String telefono = teclado.nextLine();
        System.out.println("introduzca numero de camas total");
        String datonumeroc = teclado.nextLine();
        int numeroc = Integer.parseInt(datonumeroc);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        Statement st = cn.createStatement();
        String consulta
                //insert into hospital values(46 , 'mio' , 'micasa', '215564' , 450);
                = "insert into hospital values(" + codigo + " , '" + nombre + "' ,'"
                + direccion + "' , '" + telefono + "' , " + numeroc + ")";
        int insertado = st.executeUpdate(consulta);
        System.out.println(" hospital insertado " + insertado);
        String consultaselect = "select * from hospital";
        ResultSet rs = st.executeQuery(consultaselect);
        System.out.println("hospitales");
        while (rs.next()) {
            String name = rs.getNString("Nombre");
            String dir = rs.getString("Direccion");
            System.out.println(name + "---" + dir);
        }

        cn.close();

    }
}
