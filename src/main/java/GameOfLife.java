import javafx.scene.control.Cell;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.peer.SystemTrayPeer;

public class GameOfLife  implements MouseListener {

    public static int yvalue = 8;
    public static int xvalue = 4;
    public static int rows=3;
    public static int cols=7;
public static boolean isClicked=false;
    public static int [][] existingLife = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}};

    public static int [][] futureLife = new int[xvalue][yvalue];

    JFrame uiFrame  = new JFrame();
    static JButton[] buttons = new JButton[100];


    public GameOfLife() throws InterruptedException {

        uiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uiFrame.setTitle("Example GUI");
        uiFrame.setMinimumSize(new Dimension(400,400));
        uiFrame.setSize(400,400);
        uiFrame.setLocationRelativeTo(null);
        GridLayout experimentLayout = new GridLayout(0,yvalue,0,0);



       JPanel panel = new JPanel();
JButton start = new JButton("Start");
start.setName("start");
JOptionPane.showMessageDialog(null, "Please Select living cells and press start");

start.addMouseListener(this);

panel.add(start);

        int i=0;
        while (i<32) {
            buttons[i]=new JButton();
            buttons[i].setBackground(Color.black);
            buttons[i].setForeground(Color.black);
            buttons[i].setName("playbutton"+i);
            buttons[i].addMouseListener(this);
            buttons[i].setEnabled(true);
            uiFrame.add(buttons[i]);
            i++;
        }


        uiFrame.add(panel);
        uiFrame.setLayout(experimentLayout);
        uiFrame.setVisible(true);



    }


    public static void main(String args[]) throws InterruptedException {
        new GameOfLife();



        }

public void StartProgram() throws InterruptedException {


        System.out.println("test");
        for (int x = 0; x <= rows; x++) {
            for (int y = 0; y <= cols; y++) {
                int count = rowsToCheck(x, y);
                if (count < 2) {
                    underPopulated(x, y);
                } else if (count > 3) {
                    overPopulated(x, y);
                } else if (count == 3) {
                    birth(x, y);

                } else if (count == 2 && existingLife[x][y] == 1) {

                    birth(x, y);
                }
            }
        }

        existingLife = futureLife;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    Thread t1 = new Thread(new Runnable() {
        public void run() {
            try {
                updateData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    t1.start();





}

public void updateData() throws InterruptedException {

            for (int x = 0; x <= rows; x++) {

                for (int y = 0; y <= cols  ; y++) {

                    if (existingLife[x][y] == 1) {
                        int value = (x * yvalue) + (y);

                        buttons[value].setBackground(Color.WHITE);
                    } else if (existingLife[x][y] == 0) {
                        int value = (x * yvalue) + (y);

                        buttons[value].setBackground(Color.BLACK);
                    }
                    System.out.print(existingLife[x][y]);
                }

                System.out.println("");

            }


    try {
        StartProgram();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }


}

    public static void underPopulated(int x, int y){
    isDead(x,y);
    }

    public static void overPopulated(int x, int y){
    isDead(x,y);
    }

    public static void birth(int x, int y){
        futureLife[x][y]=1;
    }


    public static void isDead(int x, int y){
    futureLife[x][y]=0;
    }

public static int rowsToCheck(int x, int y) {

    int count = 0;
    if(x!=0 &&y!=0){
        if (existingLife[x - 1][y - 1] == 1 ) {
            count++;
        }
        if (existingLife[x - 1][y] == 1 && x != 0) {
            count++;
        }

        if (existingLife[x][y - 1] == 1 && y != 0) {
            count++;
        }

    }
    if (x<rows && y!=0){
        if (existingLife[x + 1][y - 1] == 1) {
            count++;
        }
    }
    if (x!=0 && y<cols){
        if (existingLife[x - 1][y + 1] == 1 && x != 0) {
            count++;
        }

    }

    if(x<rows && y<cols){
    if (existingLife[x][y + 1] == 1&& y!=cols) {
        count++;
    }
    if (existingLife[x+1][y] == 1&& x!=rows) {
        count++;
    }
    if (existingLife[x + 1][y + 1] == 1 && x!=rows && y!=cols) {
        count++;
    }
  }

return count;
    }


    public void mouseClicked(MouseEvent e) {

String buttonName =  e.getComponent().getName();
if (buttonName.contains("start")){
    try {

        StartProgram();

    } catch (InterruptedException e1) {
        e1.printStackTrace();
    }
}else if (buttonName.contains("playbutton")){
    float value = Integer.parseInt(buttonName.substring(10));
    int x = (int)value/yvalue;
    float yx = (float)x ;
    float calc = value/yvalue-yx;
    float calc2 = yvalue * calc;
    int y = (int)calc2;
    existingLife[x][y]=1;
    buttons[(int)value].setBackground(Color.WHITE);
}
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
