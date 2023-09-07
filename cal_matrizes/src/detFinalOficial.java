import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class detFinalOficial extends JDialog{
    private JPanel detFinal;
    private JButton CALCULARButton;
    private JButton SAIRButton;
    private JButton VOLTARButton;
    private JPanel matriz;

    public detFinalOficial(JFrame parent, int d) {
        super(parent);
        int i, j;
        final int[] nume = new int[1];
        final int[] den = new int[1];
        int[][] mNumerador = new int[10][10];
        int [][] mDenominador = new int[10][10];
        String [][] incognita = new String[10][10];
        String [][] mStr = new String[10][10];
        escalonar.Bloco[][] bloc = new escalonar.Bloco[5][5];
        setFocusableWindowState(true);
        setTitle("Determinante");
        setContentPane(detFinal);
        setMinimumSize(new Dimension(1404, 936));
        setModal(true);
        setLocationRelativeTo(parent);
        CALCULARButton.setBackground(Color.white);
        VOLTARButton.setBackground(Color.white);
        SAIRButton.setBackground(Color.white);
        matriz.setMaximumSize(new Dimension(5, 5));
        matriz.setLayout(new GridLayout(d, d, 90, 90));
        for (i = 0; i < d; i++) {
            for (j = 0; j < d; j++) {
                escalonar.Bloco num = new escalonar.Bloco();
                bloc[i][j] = num;
                num.setBackground(new Color(117,129,148));
                matriz.add(num);
            }
        }
        CALCULARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < d; i++) {
                    for (int j = 0; j < d; j++) {
                        try {
                            mStr[i][j] = bloc[i][j].getText();
                            if (mStr[i][j].contains("/")) {
                                String[] separado = mStr[i][j].split("/");
                                mNumerador[i][j] = Integer.parseInt(separado[0]);
                                mDenominador[i][j] = Integer.parseInt(separado[1]);
                            } else {
                                mNumerador[i][j] = Integer.parseInt(mStr[i][j]);
                                mDenominador[i][j] = 1;
                            }
                        } catch (NumberFormatException exception) {
                            //throw new RuntimeException(ex);
                        }
                    }
                }
                if(d==2) {
                    nume[0] = det2(mNumerador, mDenominador);
                    den[0] = denDet2(mDenominador);

                }
                if(d==3) {
                    nume[0]=det3(mNumerador,mDenominador)[0];
                    den[0] = det3(mNumerador,mDenominador)[1];
                }
                if(d==4){
                    nume[0]=det4(mNumerador,mDenominador)[0];
                    den[0] =det4(mNumerador,mDenominador)[1];
                }
                if(d==5){
                    nume[0]=det5(mNumerador,mDenominador)[0];
                    den[0] =det5(mNumerador,mDenominador)[1];
                }
                if(escalonar.simplificar(nume[0],den[0])[1]!=1)
                    JOptionPane.showMessageDialog(null, "Determinante = " + escalonar.simplificar(nume[0],den[0])[0] + "/" + escalonar.simplificar(nume[0],den[0])[1], "Resultado", JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Determinante = " + escalonar.simplificar(nume[0],den[0])[0], "Resultado", JOptionPane.WARNING_MESSAGE);
            }
        });
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
                determinante voltar = new determinante(null);
            }
        });
        setVisible(true);
    }
    public static int det2(int[][] m2, int [][] den2){
        int det;
        det =  den2[0][1]*den2[1][0]*m2[0][0]*m2[1][1]-m2[0][1]*m2[1][0]*(den2[0][0]*den2[1][1]);
        return det;
    }
    public static int denDet2(int [][] den2){
        int denDet;
        denDet = den2[0][1]*den2[1][0]*den2[0][0]*den2[1][1];
        return denDet;
    }
    public static int[] det3(int[][] m, int[][] den){
        int j, c, d, a=0, b=0;
        int[] det = new int[2];
        det[1]=1;
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
                det[0] = den[0][j]*denDet2(mden)*det[0] + det[1]*m[0][j]*det2(m2, mden);
            }
            else{
                det[0] = den[0][j]*denDet2(mden)*det[0] + det[1]*(-1)*m[0][j]*det2(m2, mden);
            }
            det[1] = det[1]*den[0][j]*denDet2(mden);
        }
        return det;
    }
    public int[] det4(int[][] m, int[][]den){
        int k, c, d, a=0, b=0;
        int [] det=new int[2];
        det[1]=1;
        int [][]m3den= new int[3][3];
        int [][]m3 = new int[3][3];
        for (k=0; k<4; k++){
            for(c=1; c<4; c++){
                for(d=0; d<4; d++){
                    if(d!=k){
                        m3[a][b]=m[c][d];
                        m3den[a][b]=den[c][d];
                        b++;
                    }
                }
                a++;
                b=0;
            }
            a=0;
            b=0;
            if (k%2 == 0){
                det[0] = den[0][k]*det3(m3,m3den)[1]*det[0] + det[1]*m[0][k]*det3(m3, m3den)[0];
            }
            else{
                det[0] = den[0][k]*det3(m3,m3den)[1]*det[0] - det[1]*m[0][k]*det3(m3, m3den)[0];
            }
            det[1] = det[1]*den[0][k]*det3(m3,m3den)[1];
        }
        return det;
    }
    public int[] det5(int[][] m, int[][]den){
        int k, c, d, a=0, b=0;
        int[] det= new int[2];
        det[1]=1;
        int[][]m4den=new int[4][4];
        int[][]m4= new int[4][4];
        for (k=0; k<5; k++){
            for(c=1; c<5; c++){
                for(d=0; d<5; d++){
                    if(d!=k){
                        m4[a][b]=m[c][d];
                        m4den[a][b]=den[c][d];
                        b++;
                    }
                }
                a++;
                b=0;
            }
            a=0;
            b=0;
            if (k%2 == 0){
                det[0] = den[0][k]*det4(m4,m4den)[1]*det[0] + det[1]*m[0][k]*det4(m4, m4den)[0];
            }
            else{
                det[0] = den[0][k]*det4(m4,m4den)[1]*det[0] - det[1]*m[0][k]*det4(m4, m4den)[0];
            }
            det[1] = det[1]*den[0][k]*det4(m4,m4den)[1];
        }
        return det;
    }
}
