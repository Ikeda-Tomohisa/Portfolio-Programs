//
//MyCalculator.java(after)
//
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyCalculator extends JFrame {
    
    String digitButtonText[] = { "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "." };
    String operatorButtonText[] = { "C", "%", "±", "÷", "×", "-", "+", "=" };
    
    MyDigitButton digitButton[] = new MyDigitButton[digitButtonText.length];
    MyOperatorButton operatorButton[] = new MyOperatorButton[operatorButtonText.length];
    
    final int FRAME_WIDTH = 265;
    final int FRAME_HEIGHT = 385;
    final int BUTTON_WIDTH = 45;
    final int BUTTON_HEIGHT = 45;
    final int BUTTON_WIDTH_0 = 95;
    JLabel displayLabel = new JLabel("0", JLabel.RIGHT);
    
    String number = null;
    String op = null;
    boolean setClear = false;
    
    public static void main(String args[]) {
        new MyCalculator("Calculator");
    }
    MyCalculator(String frameTitle) {
        super(frameTitle);
        
        //表示の設定
        displayLabel.setLayout(null);
        displayLabel.setBounds(20, 20, 205, 40);
        displayLabel.setBackground(Color.BLUE);
        displayLabel.setOpaque(true);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        this.add(displayLabel);
        
        //数字ボタン(0は横長)
        for(int i = 0; i < digitButton.length; i++) {
            if(i == 9) {
                digitButton[i]  = new MyDigitButton(20+(i%3)*50, 130+((i+1)/3)*50, BUTTON_WIDTH_0, BUTTON_HEIGHT, digitButtonText[i], this);
            }else if(i == 10) {
                digitButton[i] = new MyDigitButton(20+((i+1)%3)*50, 130+((i+1)/3)*50, BUTTON_WIDTH, BUTTON_HEIGHT, digitButtonText[i], this);
            }else {
                digitButton[i] = new MyDigitButton(20+(i%3)*50, 130+(i/3)*50, BUTTON_WIDTH, BUTTON_HEIGHT, digitButtonText[i], this);
            }
            digitButton[i].setFont(new Font(Font.DIALOG,Font.PLAIN,16));
            digitButton[i].setBackground(Color.WHITE);
            this.add(digitButton[i]);
        }
        
        //C, %, ±ボタン
        for(int i = 0; i < 3; i++) {
            operatorButton[i] = new MyOperatorButton(20+(i%3)*50, 80+(i/3)*50, BUTTON_WIDTH, BUTTON_HEIGHT, operatorButtonText[i], this);
            if(i == 0) {
                operatorButton[i].setFont(new Font(Font.DIALOG,Font.PLAIN,15));
            }else if(i == 1) {
                operatorButton[i].setFont(new Font(Font.DIALOG,Font.PLAIN,12));
            }else if(i == 2) {
                operatorButton[i].setFont(new Font(Font.DIALOG,Font.PLAIN,17));
            }
            operatorButton[i].setBackground(Color.WHITE);
            this.add(operatorButton[i]);
        }
        //上の3つ以外のボタン
        for(int i = 3; i < operatorButton.length; i++) {
            operatorButton[i] = new MyOperatorButton(180, 80+(i-3)*50, BUTTON_WIDTH, BUTTON_HEIGHT, operatorButtonText[i], this);
            operatorButton[i].setFont(new Font(Font.DIALOG,Font.PLAIN,17));
            operatorButton[i].setBackground(Color.WHITE);
            this.add(operatorButton[i]);
        }
        
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyDigitButton extends JButton implements ActionListener {
    
    MyCalculator cl;
    
    MyDigitButton(int x, int y, int w, int h, String cap, MyCalculator clc) {
        super(cap);
        this.setLayout(null);
        this.setBounds(x, y, w ,h);
        this.cl = clc;
        this.cl.add(this);
        this.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ev) {
        
        if(this.cl.setClear == true && this.cl.displayLabel.getText() != "ERROR"){
            this.cl.number = this.cl.displayLabel.getText();
            this.cl.displayLabel.setText("0");
            this.cl.setClear = false;
        }
        if(this.cl.displayLabel.getText() == "ERROR") {
            this.cl.displayLabel.setText(this.getText());
            this.cl.setClear = false;
        }else if(this.cl.displayLabel.getText() == "0" && this.getText() != ".") {
            this.cl.displayLabel.setText(this.getText());
        }else {
            String show = this.cl.displayLabel.getText() + this.getText();
            this.cl.displayLabel.setText(show);
        }
    }
}

class MyOperatorButton extends JButton implements ActionListener {
    
    MyCalculator cl;
    
    MyOperatorButton(int x, int y, int w, int h, String cap, MyCalculator clc) {
        super(cap);
        this.setLayout(null);
        this.setBounds(x, y, w ,h);
        this.cl = clc;
        this.cl.add(this);
        this.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ev) {
        if(this.getText() == "C") {
            this.cl.displayLabel.setText("0");
        }else if(this.getText() == "%") {
            double percent = Double.parseDouble(this.cl.displayLabel.getText());
            percent /= 100;
            String resText = "" + percent;
            this.cl.displayLabel.setText(resText);
        }else if(this.getText() == "±") {
            BigDecimal plusminus = new BigDecimal(this.cl.displayLabel.getText());
            BigDecimal resNum = plusminus.negate();
            String resText = resNum.toString();
            this.cl.displayLabel.setText(resText);
        }
        
        if(this.getText() == "=" && this.cl.number != "ERROR") {
            BigDecimal num1 = new BigDecimal(this.cl.number);
            BigDecimal num2 = new BigDecimal(this.cl.displayLabel.getText());
            BigDecimal temp = BigDecimal.ZERO;
            try {
                if(this.cl.op == "+") {
                    temp = num1.add(num2);
                }else if(this.cl.op == "-") {
                    temp = num1.subtract(num2);
                }else if(this.cl.op == "×") {
                    temp = num1.multiply(num2);
                }else if(this.cl.op == "÷") {
                    temp = num1.divide(num2, 16, RoundingMode.HALF_UP);
                    temp = temp.stripTrailingZeros();
                }
                String resText = temp.toString();
                this.cl.displayLabel.setText(resText);
                //System.out.println(num1);
                //System.out.println(num2);
                
            }catch(ArithmeticException e) {
                String resText = "ERROR";
                this.cl.number = "0";
                this.cl.displayLabel.setText(resText);
            }
        }else if(this.getText() == "=" && this.cl.displayLabel.getText() == "ERROR") {
            String resText = "ERROR";
            this.cl.number = "0";
            this.cl.displayLabel.setText(resText);
        }
        this.cl.setClear = true;
        this.cl.op = this.getText();
    }
}