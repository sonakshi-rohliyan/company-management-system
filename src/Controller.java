import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class Controller {
    private final Company<Employee> company;

    public Controller(Company<Employee> company){
        this.company = company;
    }

    public void addEmployee(String name, String id, double salary) throws DuplicateIdException{
        Employee employee = new Employee(name,id,salary);
        company.addEmployee(employee);
    }

    public void remove(String id) throws InvalidOperationException{
        company.removeEmployee(id);
    }

    public String display(){
        HashMap<String, Employee> map = company.getEmployeeHashMap();
        String string = capture(()->{
            for (Employee employee : map.values()){
                company.displayOneEmployee(employee);
            }
        });
        return string;
    }

    public String capture(Runnable action){
        PrintStream original = System.out;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        try {
            action.run();
        }
        finally {
            System.out.flush();
            System.setOut(original);
        }
        return  stream.toString();
    }

    public String save(String prefix) {
        String string = company.saveState(prefix);
        return string;
    }

    public String load(String prefix) {
        String string = company.loadState(prefix);
        return string;

    }
}
