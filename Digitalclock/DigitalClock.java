//
//DigitalClock.java
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;

public class DigitalClock extends JFrame {
    
    public static void main(String[] args) {
        //サイズ変更しても文字を中央に
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        
        DigitalClock d = new DigitalClock();
        d.setTitle("デジタル時計");
        //閉じるボタンで終了
        d.setDefaultCloseOperation(EXIT_ON_CLOSE);
        d.setBackground(Color.white);
        d.setSize(260, 120);
        //d.setResizable(false);
        d.setVisible(true);
    }
    
    public DigitalClock() {
        DrawPanel panel = new DrawPanel();
        this.add(panel);
        new Timer(1000, panel).start();
    }
}

class DrawPanel extends JPanel implements ActionListener {
    Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    final String[] WEEK_NAME = {"日","月","火","水","木","金","土"}; 
    
    public DrawPanel() {
        
    }
    
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //Calendarでnewは使えない singletonパターン
        Calendar ca = Calendar.getInstance();
        String str1 = String.format("%d/%2d/%2d(%s)",
                                    ca.get(Calendar.YEAR),
                                    ca.get(Calendar.MONTH) + 1,
                                    ca.get(Calendar.DATE),
                                    WEEK_NAME[ca.get(Calendar.DAY_OF_WEEK)-1]
                                    );
        String str2 = String.format("%2d時%2d分%2d秒",
                                    ca.get(Calendar.HOUR_OF_DAY),
                                    ca.get(Calendar.MINUTE),
                                    ca.get(Calendar.SECOND)
                                    );
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(str1);
        int h1 = fm.getAscent();
        int h2 = fm.getDescent();
        //文字を左右上下中央に
        g.drawString(str1, (getWidth()-w)/2, (getHeight()+h1-h2)/2-15);
        g.drawString(str2, (getWidth()-w)/2, (getHeight()+h1-h2)/2+15);
    }
}