//
//MyCalculator.java(before)
//
import java.awt.*;
import java.awt.event.*;

public class MyCalculator extends Frame {

    String digitButtonText[] = { "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "+/-", "." };
    String operatorButtonText[] = { "/",  "*",  "-", "+", "=", "C" };

    MyDigitButton digitButton[] = new MyDigitButton[digitButtonText.length];
    MyOperatorButton operatorButton[] = new MyOperatorButton[operatorButtonText.length];
    
    final int FRAME_WIDTH = 325, FRAME_HEIGHT = 325;
    Label displayLabel = new Label("0", Label.RIGHT);
    
    String number = null;
    String op = null;
    boolean setClear = false;
    
    public static void main(String[] args) {
        new MyCalculator("Calculator");
    }
    
    MyCalculator(String frameText) {
        super(frameText);
        
        displayLabel.setBounds(33, 40, 255, 30);
        displayLabel.setBackground(Color.BLUE);
        displayLabel.setForeground(Color.WHITE);
        this.add(displayLabel);
        
        for(int i=0;i<digitButton.length;i++){
            digitButton[i] = new MyDigitButton(33+(i%3)*50, 95+(i/3)*50, 45, 45, digitButtonText[i], this);
            this.add(digitButton[i]);
        }
        
        for(int i=0;i<operatorButton.length;i++){
            operatorButton[i] = new MyOperatorButton(193+(i%2)*50, 95+(i/2)*50, 45, 45, operatorButtonText[i], this);
            this.add(operatorButton[i]);
        }
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setVisible(true);
    }

}

class MyDigitButton extends Button implements ActionListener{
    
    MyCalculator cl;
    
    MyDigitButton(int x, int y, int w, int h, String cap, MyCalculator clc) {
        super(cap);
        this.setBounds(x, y, w ,h);
        this.cl = clc;
        this.cl.add(this);
        
        this.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ev){
        //System.out.println(this.getLabel());
        
        if(this.cl.setClear == true){
            this.cl.number = this.cl.displayLabel.getText();
            this.cl.displayLabel.setText("0");
            this.cl.setClear = false;
        }
        
        if(this.cl.displayLabel.getText()=="0" && this.getLabel()!="."){
            this.cl.displayLabel.setText(this.getLabel());
        }else{
            String show = this.cl.displayLabel.getText()+this.getLabel();
            this.cl.displayLabel.setText(show);
        }
    }
}

class MyOperatorButton extends Button implements ActionListener{
    
    MyCalculator cl;
    
    MyOperatorButton(int x, int y, int w, int h, String cap, MyCalculator clc) {
        super(cap);
        this.setBounds(x, y, w ,h);
        this.cl = clc;
        this.cl.add(this);
        this.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ev){
        //System.out.println(this.getLabel());
        
        if(this.getLabel() == "C"){
            this.cl.displayLabel.setText("0");
        }else if(this.getLabel() == "="){
            
            double a = Double.parseDouble(this.cl.number);
            double b = Double.parseDouble(this.cl.displayLabel.getText());
            double temp = 0.0;
            if(this.cl.op == "+"){
                temp = a + b;
            }else if(this.cl.op == "-"){
                temp = a - b;
            }else if(this.cl.op == "*"){
                temp = a * b;
            }else if(this.cl.op == "/"){
                temp = a / b;
            }
            String resText = ""+temp;
            this.cl.displayLabel.setText(resText);
        }
        this.cl.setClear = true;
        this.cl.op = this.getLabel();
    }
}
