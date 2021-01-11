//Eliminamos un empleado de la plantilla por su apellido.
//Debemos mostrar en nuestra app, el número de personas que
//quedan en la plantilla y su suma salarial.
//Quedan 6 personas de la plantilla y cobran todos 555220
package jdbc;
//<editor-fold defaultstate="collapsed" desc="procedimiento">
/*create or replace procedure eliminarempleado
  (p_apellido plantilla.apellido%type
  , p_personas  out int
  , p_suma out int
  )as
  begin
    delete plantilla where lower(apellido) = lower(p_apellido);
  commit;
    select   count(empleado_no) as personas , sum(salario) as sumasalarial
     into p_personas , p_suma
    from plantilla;

  end;*/
//</editor-fold>
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class18EliminarPlantilla {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("app");
        System.out.println("----------------");
        System.out.println("apellido");
        String apellido = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());

    }
}
