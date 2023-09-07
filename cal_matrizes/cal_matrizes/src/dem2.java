import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dem2 extends JDialog {
    private JPanel dem2;
    private JPanel d;
    private JButton VOLTARButton;
    private JButton SAIRButton;
    private JTextField a00;
    private JTextField a01;
    private JTextField a10;
    private JTextField a11;
    private JButton CALCULARButton;
    private JTextField d00;
    private JTextField d01;
    private JTextField d10;
    private JTextField d11;

    /*public float det2(float[][] m) {
        float det;
        det = m[0][0] * m[1][1] - m[0][1] * m[1][0];
        return det;
    }*/

    public dem2(JFrame parent) {
        super(parent);
        //Locale pt = new Locale("pt", "PT");
        //NumberFormat a = NumberFormat.getInstance(pt);
        setAutoRequestFocus(true);
        setTitle("Matriz 2x2");
        setContentPane(dem2);
        setMinimumSize(new Dimension(950, 767));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        a00.setDocument(new dem3.mat());
        a01.setDocument(new dem3.mat());
        a10.setDocument(new dem3.mat());
        a11.setDocument(new dem3.mat());
        int[][] m2 = new int[2][2];
        int[][] m2den = new int[2][2];
        final int[] deter = new int[2];
        //a = textPane1.getText();
        CALCULARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m2[0][0] = Integer.parseInt(a00.getText());
                    m2[0][1] = Integer.parseInt(a01.getText());
                    m2[1][0] = Integer.parseInt(a10.getText());
                    m2[1][1] = Integer.parseInt(a11.getText());
                    m2den[0][0] = Integer.parseInt(d00.getText());
                    m2den[0][1] = Integer.parseInt(d01.getText());
                    m2den[1][0] = Integer.parseInt(d10.getText());
                    m2den[1][1] = Integer.parseInt(d11.getText());
                } catch (NumberFormatException exception) {
                    //throw new RuntimeException(ex);
                }
                deter[0] = dem3.det2(m2, m2den);
                deter[1] = dem3.denDeterminante(m2den);
                if(deter[0]%deter[1]==0){
                    deter[0] = deter[0]/deter[1];
                    deter[1]=1;
                }
                else{
                    if(deter[0]%2==0 && deter[1]%2==0){
                        do {
                            deter[0]= deter[0] / 2;
                            deter[1] = deter[1] / 2;
                        }while(deter[0]%2==0 && deter[1]%2==0);
                    }
                    if(deter[0]%3==0 && deter[1]%3==0){
                        do {
                            deter[0] = deter[0] / 3;
                            deter[1] = deter[1] / 3;
                        }while(deter[0]%3==0 && deter[1]%3==0);
                    }
                    if(deter[0]%5==0 && deter[1]%5==0){
                        do {
                            deter[0] = deter[0] / 5;
                            deter[1] = deter[1] / 5;
                        }while(deter[0]%5==0 && deter[1]%5==0);
                    }
                    if(deter[0]%7==0 && deter[1]%7==0){
                        do {
                            deter[0]= deter[0] / 7;
                            deter[1] = deter[1] / 7;
                        }while(deter[0]%7==0 && deter[1]%7==0);
                    }
                }
                if(deter[1]!=1)
                    JOptionPane.showMessageDialog(null, "Determinante = " + deter[0] + "/" + deter[1], "Resultado", JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Determinante = " + deter[0], "Resultado", JOptionPane.WARNING_MESSAGE);
            }
        });
        SAIRButton.addActionListener(e -> System.exit(0));
        VOLTARButton.addActionListener(e -> {
            dispose();
            determinante voltar = new determinante(null);
        });
        setVisible(true);
    }

}