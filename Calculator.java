import java.awt.*;
import java.awt.event.*;
public class Calculator extends Frame implements ActionListener {
    private TextField display;
    private Panel panel;
    private String operator;
    private double firstNumber;
    private boolean startNewNumber;
    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setLayout(new BorderLayout());
        display = new TextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        panel = new Panel();
        panel.setLayout(new GridLayout(4, 4));
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.addActionListener(this);
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);
        operator = "";
        firstNumber = 0;
        startNewNumber = true;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
    public static void main(String[] args) {
        new Calculator();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("0123456789.".contains(command)) {
            if (startNewNumber) {
                display.setText(command);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if ("+-*/".contains(command)) {
            try {
                firstNumber = Double.parseDouble(display.getText());
                operator = command;
                startNewNumber = true;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        } else if ("=".equals(command)) {
            try {
                double secondNumber = Double.parseDouble(display.getText());
                double result = 0;
                switch (operator) {
                    case "+": result = firstNumber + secondNumber; break;
                    case "-": result = firstNumber - secondNumber; break;
                    case "*": result = firstNumber * secondNumber; break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                startNewNumber = true;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
    }
}