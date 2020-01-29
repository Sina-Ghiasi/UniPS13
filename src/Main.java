import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    @FunctionalInterface
    public interface Writer {
       void write(Employee employee);
    }
    public static void main(String[] args) {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        for(int i = 0;i<4;i++){
            Employee employee =new Employee();
            employee.setName("employee "+i);
            employee.setNationalId("NID"+i);
            employeeArrayList.add(employee);
        }
        Writer writer =(employee)->{
            try{
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sina","sg159753");
                PreparedStatement preparedStatement = connection.prepareStatement("insert into employee (name,nationalID) values (?,?,?,?)");
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, employee.getNationalId());
                System.out.println("Done");
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }catch (SQLException e){
            e.printStackTrace();
        }
        };
        for(Employee employee:employeeArrayList){
            writer.write(employee);
        }
    }
}
