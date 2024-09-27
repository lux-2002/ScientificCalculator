import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator;
    private double num1, num2;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 10, 10));
        
        // Buttons for numbers and operations
        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+", 
            "sin", "cos", "tan", "log", 
            "C", "(", ")", "^"
        };

        // Create and add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle number and decimal point input
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            display.setText(display.getText() + command);
        } 
        // Clear display
        else if (command.equals("C")) {
            display.setText("");
            operator = "";
        } 
        // Calculate result
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = performOperation(num1, num2, operator);
            display.setText(String.valueOf(result));
            operator = "";
        } 
        // Store the operator
        else {
            if (!operator.isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                double result = performOperation(num1, num2, operator);
                display.setText(String.valueOf(result));
            }
            operator = command;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        }
    }

    private double performOperation(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero");
                    return 0;
                }
            case "sin":
                return Math.sin(Math.toRadians(num1));
            case "cos":
                return Math.cos(Math.toRadians(num1));
            case "tan":
                return Math.tan(Math.toRadians(num1));
            case "log":
                return Math.log(num1);
            case "^":
                return Math.pow(num1, num2);
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}