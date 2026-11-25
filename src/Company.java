import java.io.*;
import java.util.HashMap;

public class Company<T extends Employee> {
    private HashMap<String, T> employeeHashMap;

    public Company(){
        this.employeeHashMap = new HashMap<>();
    }

    public HashMap<String, T> getEmployeeHashMap(){
        return employeeHashMap;
    }

    public void addEmployee(T employee) throws DuplicateIdException{
        if (employeeHashMap.containsKey(employee.getEmployeeId())){
            throw new DuplicateIdException("The employee with employee ID " +employee.getEmployeeId()+" is already added");
        }
        employeeHashMap.put(employee.getEmployeeId(), employee);
    }

    public void removeEmployee(String employeeId) throws InvalidOperationException{
        if (!employeeHashMap.containsKey(employeeId)){
            throw new InvalidOperationException("The employee with employee id " + employeeId+" doesn't exist");
        }
        employeeHashMap.remove(employeeId);
    }

    public void displayEmployees(){
        for (T employee : employeeHashMap.values()){
            System.out.println("ID: " + employee.getEmployeeId() + " ,Name: "+ employee.getName() + " ,Salary: "+employee.getSalary()+"\n");
        }
    }

    public void displayOneEmployee(Employee employee){
        System.out.println("ID: " + employee.getEmployeeId() + " ,Name: "+ employee.getName() + " ,Salary: "+employee.getSalary()+"\n");
    }

    public String saveState(String prefix)  {
        try{
            if (prefix.isEmpty()){
                throw new InvalidOperationException("Enter a prefix");
            }
            PrintWriter employeeWriter = new PrintWriter(new FileWriter(prefix+ ".csv"));
            for (Employee employee: employeeHashMap.values()){
                String line = employee.getName() + ","+employee.getEmployeeId() + "," + employee.getSalary();
                employeeWriter.println(line);
            }
            employeeWriter.close();
            return ("State saved");
        }
        catch (InvalidOperationException e){
            return (e.getMessage());
        }
        catch (IOException e){
            return ("Failed to save state");
        }
    }

    public String loadState(String prefix){
        HashMap<String, T> map = new HashMap<>();
        try {
            if (prefix.isEmpty()){
                throw new InvalidOperationException("Enter a prefix");
            }

            BufferedReader employeeReader = new BufferedReader(new FileReader(prefix + ".csv"));
            String line;
            while ((line = employeeReader.readLine()) != null){
                if (line.trim().isEmpty()){
                    continue;
                }
                try {
                    String[] emp = line.split(",");
                    String name = emp[0];
                    String id = emp[1];
                    double salary = Double.parseDouble(emp[2]);
                    Employee employee = new Employee(name,id,salary);
                    T e = (T) employee;
                    map.put(id, e);

                }
                catch (Exception e){
                    return (e.getMessage());
                }
            }
            employeeReader.close();
            employeeHashMap.clear();
            employeeHashMap.putAll(map);
            return ("File loaded");
        }
        catch (InvalidOperationException e){
            return (e.getMessage());
        }

        catch (IOException e){
            return ("Failed to load state");
        }
    }
}
