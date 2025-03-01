import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class Calculator extends JFrame implements ActionListener {
    private DecimalFormat df = new DecimalFormat("#,###.00");

    private String [] symbols = {
            "AC", "+/-", "%", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "Sci","="
    };
    private String operator = "";
    private JPanel panel = new JPanel(new BorderLayout(5,5));
    private JPanel btnPanel = new JPanel(new GridLayout(5,4, 2, 2));
    private JButton[] btns = new JButton[20];
    private JTextArea screen = new JTextArea(1,10);
    private double firstNum = 0, secondNum = 0;
    public Calculator(){
        init();
    }
    private void init(){

        screen.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        screen.setFont(new Font("Helvetica", Font.PLAIN, 64));
        screen.setBackground(Color.BLACK);
        screen.setForeground(Color.WHITE);
        panel.setBackground(Color.BLACK);
        btnPanel.setBackground(Color.BLACK);

        for (int i=0; i< btns.length; i++){
            btns[i] = new JButton(symbols[i]);
            btns[i].setOpaque(true);
            btns[i].setBorderPainted(false);
            if ((i+1)%4 == 0){
                btns[i].setBackground(Color.ORANGE);
            } else if(i<3){
                btns[i].setBackground(Color.DARK_GRAY);
            } else {
                btns[i].setBackground(Color.GRAY);
            }
            btns[i].setFont(new Font("Helvetica", Font.PLAIN, 18));
            btns[i].setForeground(Color.WHITE);
            btns[i].addActionListener(this);
            btnPanel.add(btns[i]);
        }

        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(screen, BorderLayout.NORTH);
        add(panel);
        setSize(295, 355);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private double add (double first, double second) {return first + second;}
    private double subtract (double first, double second) {return first - second;}
    private double multiply (double first, double second) {return first * second;}
    private String divide (double first, double second) {
        String result;
        try {
            if (second == 0.0) throw new DivideZEROException();
            result =  String.valueOf(first / second);
        } catch (DivideZEROException ex) {
            result = "Err Divide by 0";
        }
        return result;
    }

    public static void main(String[] args){
        new Calculator();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand().toString();

        switch (cmd){
            case "." -> {
                if(!screen.getText().contains(".")){
                    screen.setText(screen.getText() + ".");
                }
            }
            case "0" ->{
                screen.setText(screen.getText() + "0");
            }
            case "1" ->{
                screen.setText(screen.getText() + "1");
            }
            case "2" ->{
                screen.setText(screen.getText() + "2");
            }
            case "3" ->{
                screen.setText(screen.getText() + "3");
            }
            case "4" ->{
                screen.setText(screen.getText() + "4");
            }
            case "5" ->{
                screen.setText(screen.getText() + "5");
            }
            case "6" ->{
                screen.setText(screen.getText() + "6");
            }
            case "7" ->{
                screen.setText(screen.getText() + "7");
            }
            case "8" ->{
                screen.setText(screen.getText() + "8");
            }
            case "9" ->{
                screen.setText(screen.getText() + "9");
            }
            case "+" ->{
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText());
                    operator = "+";
                    screen.setText("");
                }
            }
            case "-" ->{
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText());
                    operator = "-";
                    screen.setText("");
                }
            }
            case "x" ->{
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText());
                    operator = "x";
                    screen.setText("");
                }
            }
            case "/" ->{
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText());
                    operator = "/";
                    screen.setText("");
                }
            }
            case "+/-" ->{
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText())*-1.0;
                    screen.setText(String.valueOf(firstNum));
                }
            }
            case "%" ->{
                if (!screen.getText().isEmpty()){
                    firstNum = Double.parseDouble(screen.getText())*.01;
                    screen.setText(String.valueOf(firstNum));
                }
            }
            case "AC" ->{
                screen.setText("");
                firstNum = 0;
                secondNum = 0;
            }
        }
        if(cmd.equalsIgnoreCase("=")){
            if(!screen.getText().isEmpty()){
                secondNum = Double.parseDouble(screen.getText());

                switch (operator){
                    case "+" -> screen.setText(String.valueOf(add(firstNum,secondNum)));
                    case "-" -> screen.setText(String.valueOf(subtract(firstNum,secondNum)));
                    case "x" -> screen.setText(String.valueOf(multiply(firstNum,secondNum)));
                    case "/" -> screen.setText(divide(firstNum,secondNum));
                }

                operator = "";
            }
        }
    }
}
