import java.util.HashMap;

public class Company<T extends Employee> {
    private HashMap<String, T> employeeHashMap;

    public Company(){
        this.employeeHashMap = new HashMap<>();
    }

    public void addEmployee(T employee) throws DuplicateIdException{
        if (employeeHashMap.containsKey(employee.getEmployeeId())){
            throw new DuplicateIdException("The employee with employee ID " +employee.getEmployeeId()+" is already added");
        }
        employeeHashMap.put(employee.getEmployeeId(), employee);
    }

    public void removeEmployee(String employeeId) throws InvalidOperationException{
        if (!employeeHashMap.containsKey(employeeId)){
            throw new InvalidOperationException("The employee with employee id" + employeeId+" doesn't exist");
        }
        employeeHashMap.remove(employeeId);
    }

    public void displayEmployees(){
        for (T employee : employeeHashMap.values()){
            System.out.println("ID: " + employee.getEmployeeId() + " Name: "+ employee.getName() + " Salary: "+employee.getSalary()+"\n");
        }
    }
}
