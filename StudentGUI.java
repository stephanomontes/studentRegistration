import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGUI {
    private StudentApp studentApp;
    private JFrame frame;

    // GUI components
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField addressField;
    private JButton registerButton;
    private JButton loginButton;

    public StudentGUI(StudentApp studentApp) {
        this.studentApp = studentApp;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Student App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);

        // Initialize GUI components
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        addressField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        // Add action listeners
        registerButton.addActionListener(e -> handleRegisterButtonClick());
        loginButton.addActionListener(e -> handleLoginButtonClick());

        // Layout components in the frame
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(loginButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void handleRegisterButtonClick() {
        // Get user input from GUI components and create a new Student object
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String password = new String(passwordField.getPassword());
        
        // Perform the registration operation
        studentApp.registerStudent(firstName, lastName, email, address, password);
        

        // Show the success page
        showSuccessPage();
    }

    private void handleLoginButtonClick() {
        // Get user input from GUI components
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Perform the login operation
        Student student = studentApp.loginStudent(email, password);

        if (student != null) {
            JOptionPane.showMessageDialog(frame, "Login successful.");
        } else {
            JOptionPane.showMessageDialog(frame, "Login failed. Please check your credentials and try again.");
        }
    }

    private void showSuccessPage() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel successLabel = new JLabel("Student successfully registered!", SwingConstants.CENTER);
        JButton backButton = new JButton("Go back");

        backButton.addActionListener(e -> recreateInitialGUI());

        frame.add(successLabel, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void recreateInitialGUI() {
        frame.getContentPane().removeAll();
        createAndShowGUI();
    }
}
