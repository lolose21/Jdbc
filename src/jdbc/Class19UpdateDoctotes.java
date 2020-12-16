// Incrementar el salario de los doctores de
//un hospital.
//    Pediremos el nombre del hospital y el incremento.
//Recuperamos la suma salarial y la media salarial
//de los doctores del hospital después de la subida.
//La media del hospital La Paz es 45678 y su suma es 785422
package jdbc;
//<editor-fold defaultstate="collapsed" desc="procedimiento">
/*create or replace procedure updatesalariodoctores
(p_nombre hospital.nombre%type
, p_incremento int
, p_suma out int
, p_media out int)
as
  v_codigo int;
begin
  --debemos guardar el codigo del hospital
  --para el update de los doctores
  select hospital_cod into v_codigo
  from hospital
  where lower(nombre)=lower(p_nombre);
  update doctor set salario = salario + p_incremento
  where hospital_cod=v_codigo;
  commit;
  select sum(salario), avg(salario) into p_suma, p_media
  from doctor
  where hospital_cod=v_codigo;
end;*/

//create or replace procedure updatesalario
import static com.oracle.jrockit.jfr.DataType.STRING;
import java.lang.ProcessBuilder.Redirect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.sql.Types.INTEGER;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

//(p_nombre  hospital.nombre%type
//, p_incremento int
//, p_avg  out int
//, p_suma out int
//)as
//begin
//update doctor set salario = salario + p_incremento
//where hospital_cod = (select hospital_cod from hospital
//where lower(nombre)= lower(p_nombre));
// commit;
//select avg(salario) as mediasalario , sum(salario) as sumasalarial
//into p_avg , p_suma
//from doctor
//where hospital_cod = (select hospital_cod from hospital
//where lower(nombre) = lower('la paz'));
// end;
//</editor-fold>
public class Class19UpdateDoctotes {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("app incrementar salarios");
        System.out.println("-------------------------");
        System.out.println("introduzca nombre del hospital");
        String nombre = teclado.nextLine();
        System.out.println("introduzca subida salarial");
        String datosalario = teclado.nextLine();
        int subida = Integer.parseInt(datosalario);
        DriverManager.registerDriver(new OracleDriver());
        String cadena
                = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "SYSTEM", "oracle");
        String consultaproc
                = "{call updatesalario(? , ? , ? ,?)}";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setString(1, nombre);
        cst.setInt(2, subida);

        cst.registerOutParameter(3, java.sql.Types.INTEGER);
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        cst.executeUpdate();
        int media = cst.getInt(3);
        int sumatotal = cst.getInt(4);
        System.out.println(nombre + "  salario medio =  " + media
                + "  --la suma total  ="
                + sumatotal);
        cn.close();

    }
}
