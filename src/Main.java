public class Main {

    public static void main(String[] args){
        Company<Employee> company = new Company<>();

        Employee employee1 = new Employee("Meow", "1", 20000);
        Employee employee2 = new Employee("MeowMeow", "2", 40000);
        Employee employee3 = new Employee("MeowMeowMeow", "3", 60000);

        try {
            company.addEmployee(employee1);
            company.addEmployee(employee2);
            company.addEmployee(employee3);
            company.displayEmployees();

            company.removeEmployee("1");
            company.displayEmployees();
        }
        catch (DuplicateIdException e){
            System.out.println("There is already an employee with the same id");
        }
        catch (InvalidOperationException e){
            System.out.println("You cant remove an employee that isn't added");
        }
    }
}
