import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class View extends JFrame {
    private final Company<Employee> company;
    private final Controller controller;

    private JTextArea outputArea;

    private JTextField addName;
    private JTextField addId;
    private JTextField addSalary;

    private JTextField removeId;

    private JTextField prefix;

    public View(Company<Employee> company){
        this.company = company;
        this.controller = new Controller(company);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setJMenuBar(buildMenuBar());

        setLayout(new BorderLayout());

        add(buildEmployee(), BorderLayout.WEST);
        add(buildOutputArea(), BorderLayout.CENTER);
        add(removeEmployee(), BorderLayout.NORTH);
    }

    // build a menu

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");

        prefix = new JTextField();

        loadItem.addActionListener(event -> loadFromMenu());
        saveItem.addActionListener(event -> saveFromMenu());
        exitItem.addActionListener(event -> System.exit(0));


        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        return menuBar;


    }

    private void saveFromMenu() {
        String pref = prefix.getText().trim();
        String string = controller.save(pref);
        appendOutput(string);

    }

    private void loadFromMenu() {
        String pref = prefix.getText().trim();
        String string = controller.load(pref);
        appendOutput(string);

    }

    // Output area

    private JScrollPane buildOutputArea() {
        outputArea = new JTextArea(20,60);
        outputArea.setEditable(false);

        JScrollPane pane = new JScrollPane(outputArea);
        pane.setBorder(BorderFactory.createTitledBorder("Output"));
        return pane;
    }

    public void appendOutput(String text){
        outputArea.append(text+"\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    // add employee

    private JPanel buildEmployee(){
        JPanel employeeAddition = new JPanel(new GridLayout(4, 2, 5, 5));

        addName = new JTextField(12);
        addId = new JTextField(12);
        addSalary = new JTextField(12);

        employeeAddition.add(new JLabel("Enter employee id."));
        employeeAddition.add(addId);
        employeeAddition.add(new JLabel("Enter employee name."));
        employeeAddition.add(addName);
        employeeAddition.add(new JLabel("Enter employee salary."));
        employeeAddition.add(addSalary);

        JButton button = new JButton("Add Employee");

        employeeAddition.add(button);

        button.addActionListener(event -> AddEmployeeListener());

        return employeeAddition;
    }

    private void AddEmployeeListener(){
        try {
            String name = addName.getText().trim();
            String id = addId.getText().trim();
            double salary = Double.parseDouble(addSalary.getText().trim());

            controller.addEmployee(name,id,salary);
            appendOutput("The employee with id "+id+" has been added.");
        }
        catch (NumberFormatException e){
            appendOutput("salary should be a double");
        }
        catch (Exception e){
            appendOutput(e.getMessage());
        }
    }

    // option to remove employee
    private JPanel removeEmployee(){
        JPanel row = new JPanel(new GridLayout(3,1));

        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row3 = new JPanel();

        removeId = new JTextField(12);

        row1.add(new JLabel("Enter the ID to be deleted: "));
        row1.add(removeId);

        JButton button = new JButton("Remove employee");
        row1.add(button);
        button.addActionListener(event-> removeEmployeee());

        JButton button2 = new JButton("Display all employees");
        row2.add(button2);
        row.add(row1);
        row.add(row2);
        row3.add(new JLabel("Enter the prefix for save/load: "));
        prefix = new JTextField("employees",10);
        row3.add(prefix);
        row.add(row3);

        button2.addActionListener(event -> displayEmployees());
        return row;
    }

    private void removeEmployeee(){
        try {
            String id = removeId.getText().trim();
            if (id.isEmpty()){
                throw new InvalidOperationException("Enter the id to delete");
            }
            controller.remove(id);
            appendOutput("The employee with id "+id+ " has been removed." );

        }
        catch (InvalidOperationException e){
            appendOutput(e.getMessage());
        }
    }

    // display

    private void displayEmployees(){
        String string = controller.display();
        appendOutput(string);
    }
}









