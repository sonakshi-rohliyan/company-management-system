public class Employee {
    private String name;
    private String employeeId;
    private double salary;

    public Employee(String name, String employeeId, double salary){
        this.name = name;
        this.employeeId = employeeId;
        this.salary = salary;
    }

    // getter methods
    public String getName(){
        return name;
    }
    public String getEmployeeId(){
        return employeeId;
    }
    public double getSalary(){
        return salary;
    }

    //setter methods
    public void setName(String new_name){
        this.name = new_name;
    }
    public void setEmployeeId(String new_id){
        this.employeeId = employeeId;
    }
    public void setSalary(double new_sal){
        this.salary = new_sal;
    }


}
