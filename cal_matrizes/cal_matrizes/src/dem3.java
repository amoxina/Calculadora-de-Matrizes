import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class dem3 extends JDialog{
    private JPanel dem3;
    private JTextField a00;
    private JTextField a01;
    private JTextField a10;
    private JTextField a11;
    private JTextField a20;
    private JTextField a21;
    private JButton VOLTARButton;
    private JButton SAIRButton;
    private JButton CALCULARButton;
    private JTextField a02;
    private JTextField a12;
    private JTextField a22;
    private JTextField d00;
    private JTextField d01;
    private JTextField d02;
    private JTextField d10;
    private JTextField d11;
    private JTextField d12;
    private JTextField d20;
    private JTextField d21;
    private JTextField d22;
    private JLabel _____________Label;
    private JLabel _____________Label1;
    private JLabel _____________Label2;
    private JLabel _____________Label3;
    private JLabel _____________Label4;
    private JLabel _____________Label5;
    private JLabel _____________Label6;
    private JLabel _____________Label7;
    private JLabel _____________Label8;

    public static int det2(int[][] m2, int [][] den2){
        int det;
        det = (den2[0][1]*den2[1][0])*m2[0][0]*m2[1][1]-m2[0][1]*m2[1][0]*(den2[0][0]*den2[1][1]);
        return det;
    }
    public static int denDeterminante(int [][] den2){
        int denDet;
        denDet = den2[0][1]*den2[1][0]*den2[0][0]*den2[1][1];
        return denDet;
    }
    public static int det3(int[][] m, int[][] den){
        int j, c, d, a=0, b=0;
        int det=0, denDet=1;
        int [][]m2= new int[2][2];
        int [][]mden= new int[2][2];
        for(j=0; j<3; j++){
            for(c=1; c<3; c++){
                for(d=0; d<3; d++){
                    if(d!=j){
                        m2[a][b]=m[c][d];
                        mden[a][b]=den[c][d];
                        b++;
                    }
                }
                a++;
                b=0;
            }
            a=0;
            b=0;
            if (j%2 == 0){
                det = den[0][j]*denDeterminante(mden)*det + denDet*m[0][j]*det2(m2, mden);
            }
            else{
                det = den[0][j]*denDeterminante(mden)*det + denDet*(-1)*m[0][j]*det2(m2, mden);
            }
            denDet = denDet*den[0][j]*denDeterminante(mden);
        }
        if(det%denDet==0){
            det = det/denDet;
            denDet=1;
        }
        else{
            if(det%2==0 && denDet%2==0){
                do {
                    det = det / 2;
                    denDet = denDet / 2;
                }while(det%2==0 && denDet%2==0);
            }
            if(det%3==0 && denDet%3==0){
                do {
                    det = det / 3;
                    denDet = denDet / 3;
                }while(det%3==0 && denDet%3==0);
            }
            if(det%5==0 && denDet%5==0){
                do {
                    det = det / 5;
                    denDet = denDet / 5;
                }while(det%5==0 && denDet%5==0);
            }
            if(det%7==0 && denDet%7==0){
                do {
                    det = det / 7;
                    denDet = denDet / 7;
                }while(det%7==0 && denDet%7==0);
            }
        }
        if(denDet!=1)
            JOptionPane.showMessageDialog(null, "Determinante = " + det + "/" + denDet, "Resultado", JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Determinante = " + det, "Resultado", JOptionPane.WARNING_MESSAGE);
        return 0;
    }
    public dem3(JFrame parent) {
        super(parent);
        int[][] m3num = new int[3][3];
        int[][] m3den = new int[3][3];
        setAutoRequestFocus(true);
        setTitle("Matriz 3x3");
        setContentPane(dem3);
        setMinimumSize(new Dimension(900, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        a00.setDocument(new dem3.mat());
        a01.setDocument(new dem3.mat());
        a10.setDocument(new dem3.mat());
        a11.setDocument(new dem3.mat());
        CALCULARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m3num[0][0] = Integer.parseInt(a00.getText());
                    m3num[0][1] = Integer.parseInt(a01.getText());
                    m3num[0][2] = Integer.parseInt(a02.getText());
                    m3num[1][0] = Integer.parseInt(a10.getText());
                    m3num[1][1] = Integer.parseInt(a11.getText());
                    m3num[1][2] = Integer.parseInt(a12.getText());
                    m3num[2][0] = Integer.parseInt(a20.getText());
                    m3num[2][1] = Integer.parseInt(a21.getText());
                    m3num[2][2] = Integer.parseInt(a22.getText());
                    m3den[0][0] = Integer.parseInt(d00.getText());
                    m3den[0][1] = Integer.parseInt(d01.getText());
                    m3den[0][2] = Integer.parseInt(d02.getText());
                    m3den[1][0] = Integer.parseInt(d10.getText());
                    m3den[1][1] = Integer.parseInt(d11.getText());
                    m3den[1][2] = Integer.parseInt(d12.getText());
                    m3den[2][0] = Integer.parseInt(d20.getText());
                    m3den[2][1] = Integer.parseInt(d21.getText());
                    m3den[2][2] = Integer.parseInt(d22.getText());
                } catch (NumberFormatException exception) {
                    //throw new RuntimeException(ex);
                }
                det3(m3num, m3den);
            }
        });
        SAIRButton.addActionListener(e -> System.exit(0));
        VOLTARButton.addActionListener(e -> {
            dispose();
            determinante voltar = new determinante(null);
        });
        setVisible(true);
    }
    public static class mat extends PlainDocument{
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            super.insertString(offs, str.replaceAll("[^0-9]", ""), a);

        }
    }
}
