import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
public class determinante extends JDialog{
    public JButton VOLTARButton;
    public JPanel determinante;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JButton SAIRButton;

    public float det2(float[][] m){
        float det;
        det = m[0][1]*m[1][1]-m[0][1]*m[1][0];
        return det;
    }
    public float det3(float[][] m){
        int j, c, d, a=0, b=0;
        float det=0;
        float [][]m2= new float[2][2];
        for(j=0; j<3; j++){
            for(c=1; c<3; c++){
                for(d=0; d<3; d++){
                    if(d!=j){
                        m2[a][b]=m[c][d];
                        b++;
                    }
                }
                a++;
                b=0;
            }
            a=0;
            b=0;
            det = (float) (det + Math.pow(-1,j)*m[0][j]*det2(m2));

        }
        return det;
        }
    public float det4(float[][] m){
        int k, c, d, a=0, b=0;
        float det=0;
        float [][]m3 = new float[3][3];
        for (k=0; k<4; k++){
            for(c=1; c<4; c++){
                for(d=0; d<4; d++){
                    if(d!=k){
                        m3[a][b]=m[c][d];
                        b++;
                    }
                }
                a++;
                b=0;
            }
            a=0;
            b=0;
            det = (float) (det + Math.pow(-1,k)*m[0][k]*det3(m3));
        }
        return det;
    }
    public float det5(float[][] m){
        int k, c, d, a=0, b=0;
        float det=0;
        float[][]mo4= new float[4][4];
        for (k=0; k<5; k++){
            for(c=1; c<5; c++){
                for(d=0; d<5; d++){
                    if(d!=k){
                        mo4[a][b]=m[c][d];
                        b++;
                    }
                }
                a++;
                b=0;
            }
            a=0;
            b=0;
            det = (float) (det + Math.pow(-1,k)*m[0][k]*det4(mo4));
        }
    return det;
    }
    public determinante(JFrame parent){
        super(parent);
        setAutoRequestFocus(true);
        setTitle("Determinante");
        setContentPane(determinante);
        setMinimumSize(new Dimension(950, 767));
        setModal(true);
        setLocationRelativeTo(parent);
        button1.setBackground(Color.darkGray);
        button2.setBackground(Color.darkGray);
        button3.setBackground(Color.darkGray);
        button4.setBackground(Color.darkGray);
        VOLTARButton.setBackground(Color.lightGray);
        SAIRButton.setBackground(Color.lightGray);
        SAIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        VOLTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                menuprincipal voltar = new menuprincipal(null);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                dem2 m2 = new dem2(null);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                dem3 m3 = new dem3(null);
            }
        });
        setVisible(true);

    }
}
