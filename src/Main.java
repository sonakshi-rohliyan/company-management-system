import java.awt.*;

public class Main {

    public static void main(String[] args){
        EventQueue.invokeLater(()->{
            Company<Employee> company = new Company<>();
            View view = new View(company);;
            view.pack();
            view.setVisible(true);
        });

    }
}
