import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class escalonar extends JDialog {
    private JPanel escalonar;
    private JButton VOLTARButton;
    private JButton SAIRButton;
    private JButton OKButton;
    private JPanel opcoes;
    private JPanel mInt;
    private JPanel ant;
    private JSpinner dim1;
    private JSpinner dim2;
    private JSpinner troca1;
    private JButton CALCULARButton;
    private JSpinner troca2;
    private JTextField mult1;
    private JTextField mult2;
    private JCheckBox trocarCheckBox;
    private JCheckBox somarCheckBox;
    private JPanel infos;
    private JPanel mat1;
    private JPanel mat2;
    private JPanel mat3;
    private JPanel mat4;
    private JPanel mat5;
    public escalonar(JFrame parent){
        super(parent);
        final int[] cont = {1};
        final int[] d1 = new int[1];
        final int[] d2 = new int[1];
        final int[] linha1 = new int[1];
        final int[] linha2 = new int[1];
        final int[] linha3 = new int[1];
        final int[] linha4 = new int[1];
        int[][] mNumerador = new int[10][10];
        int [][] mDenominador = new int[10][10];
        String [][] incognita = new String[10][10];
        String [][] mStr = new String[10][10];
        Bloco [][] blocos = new Bloco[10][10];
        nums [][] antnum = new nums[10][10];
        setFocusableWindowState(true);
        setTitle("Escalonamento");
        setContentPane(escalonar);
        setMinimumSize(new Dimension(1404, 936));
        setModal(true);
        setLocationRelativeTo(parent);
        Border raise;
        TitledBorder titulo1,titulo2;
        raise = BorderFactory.createLineBorder(Color.white);
        titulo1=BorderFactory.createTitledBorder(raise, "OPÇÕES",1,0,null,Color.white);
        titulo2=BorderFactory.createTitledBorder(raise,"ANTERIORES",1,0,null,Color.white);
        titulo2.setTitleFont(new Font("Agency FB",Font.BOLD,20));
        titulo1.setTitleFont(new Font("Agency FB", Font.BOLD,20));
        titulo1.setTitleJustification(TitledBorder.CENTER);
        opcoes.setBorder(titulo1);
        ant.setBorder(titulo2);
        CALCULARButton.setBackground(Color.white);
        VOLTARButton.setBackground(Color.white);
        SAIRButton.setBackground(Color.white);
        OKButton.setBackground(Color.white);
        opcoes.setMinimumSize(new Dimension(50,50));
        dim1.setModel(new SpinnerNumberModel(2,2,10,1));
        dim2.setModel(new SpinnerNumberModel(2,2,10,1));
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mInt.removeAll();
                mInt.repaint();
                try {
                    d1[0] = (int) dim1.getValue();
                    d2[0] = (int) dim2.getValue();
                    mInt.setMaximumSize(new Dimension(5, 5));
                    mInt.setLayout(new GridLayout(d1[0], d2[0], 40, 40));
                    for (int i = 0; i < d1[0]; i++) {
                        for (int j = 0; j < d2[0]; j++) {
                            Bloco bloco = new Bloco();
                            blocos[i][j] = bloco;
                            bloco.setBackground(new Color(117,129,148));
                            mInt.add(bloco);
                        }
                    }
                    troca1.setModel(new SpinnerNumberModel(0,0, d1[0],1));
                    troca2.setModel(new SpinnerNumberModel(0,0, d1[0],1));
                } catch (NumberFormatException exception){
                    //throw new RuntimeException(ex);
                }
                setVisible(true);
            }
        });
        CALCULARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < d1[0]; i++) {
                        for (int j = 0; j < d2[0]; j++) {
                            try {
                                mStr[i][j] = blocos[i][j].getText();
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
                                incognita[i][j] = mStr[i][j];
                                mNumerador[i][j] = 0;
                                mDenominador[i][j] = 0;
                            }
                        }
                    }
                    int [] multFinal = new int[4];
                    String[] multStr = new String[4];
                        linha1[0]=(int) troca1.getValue();
                        linha2[0]=(int) troca2.getValue();
                        multStr[0] = mult2.getText();
                        multStr[3] = mult1.getText();
                        int auxNum;
                        int auxDen;
                        if(trocarCheckBox.isSelected()){
                                if(multStr[0].contains("/")) { //mult da linha 2
                                    String[] separado = multStr[0].split("/");
                                    multFinal[0] = Integer.parseInt(separado[0]);
                                    multFinal[1] = Integer.parseInt(separado[1]);
                                }
                                else{
                                    multFinal[0] = Integer.parseInt(multStr[0]);
                                    multFinal[1] = 1;
                                }
                                if(multStr[3].contains("/")){ //mult da linha 1
                                    String[] separado2 = multStr[3].split("/");
                                    multFinal[2] = Integer.parseInt(separado2[0]);
                                    multFinal[3] = Integer.parseInt(separado2[1]);
                                }
                                else{
                                    multFinal[2] = Integer.parseInt(multStr[3]);
                                    multFinal[3] = 1;
                                }
                                for(int i=0;i<d2[0];i++) {
                                    if(incognita[linha1[0]-1][i]==null && incognita[linha2[0]-1][i]==null){
                                        auxNum = mNumerador[linha1[0]-1][i]*multFinal[2];
                                        mNumerador[linha1[0]-1][i] = mNumerador[linha2[0]-1][i] * multFinal[0];
                                        if(linha1[0]!=linha2[0]) mNumerador[linha2[0]-1][i] = auxNum;
                                    }
                                    else{
                                        if(incognita[linha1[0]-1][i]!=null && incognita[linha2[0]-1][i]!=null){
                                            String auxIn1;
                                            if(!multStr[3].equals("1")) {
                                                auxIn1 = multStr[3]+"*("+String.copyValueOf(incognita[linha1[0]-1][i].toCharArray())+")";
                                            }
                                            else{
                                                auxIn1 = String.copyValueOf(incognita[linha1[0]-1][i].toCharArray());
                                            }
                                            if(!multStr[0].equals("1")){
                                                incognita[linha1[0]-1][i] = multStr[0]+"*("+String.copyValueOf(incognita[linha2[0]-1][i].toCharArray())+")";
                                            }
                                            else{
                                                incognita[linha1[0]-1][i] = String.copyValueOf(incognita[linha2[0]-1][i].toCharArray());
                                            }
                                            incognita[linha2[0]-1][i] = String.copyValueOf(auxIn1.toCharArray());
                                            auxNum = mNumerador[linha1[0]-1][i]*multFinal[2];
                                            mNumerador[linha1[0]-1][i] = mNumerador[linha2[0]-1][i] * multFinal[0];
                                            if(linha1[0]!=linha2[0])mNumerador[linha2[0]-1][i] = auxNum;
                                        }
                                        else {
                                            if(incognita[linha2[0]-1][i]!=null) {
                                                if(!multStr[0].equals("1")) {
                                                    incognita[linha1[0]-1][i] = multStr[0] +"*("+String.copyValueOf(incognita[linha2[0] - 1][i].toCharArray())+")";
                                                }
                                                else{
                                                    incognita[linha1[0]-1][i] = String.copyValueOf(incognita[linha2[0] - 1][i].toCharArray());
                                                }
                                                incognita[linha2[0]-1][i] = null;
                                                mNumerador[linha2[0]-1][i] = 1;
                                                mNumerador[linha2[0]-1][i] = mNumerador[linha1[0] - 1][i] * multFinal[2];
                                                mNumerador[linha1[0]-1][i] = 0;
                                                mDenominador[linha2[0]-1][i]=multFinal[1]*multFinal[3]*mDenominador[linha1[0]-1][i];
                                                mDenominador[linha1[0]-1][i]=0;
                                            }
                                            else{
                                                if(!multStr[3].equals("1")) {
                                                    incognita[linha2[0]-1][i] = multStr[3]+"*("+String.copyValueOf(incognita[linha1[0]-1][i].toCharArray())+")";
                                                }
                                                else{
                                                    incognita[linha2[0]-1][i] = String.copyValueOf(incognita[linha1[0]-1][i].toCharArray());
                                                }
                                                incognita[linha1[0]-1][i] = null;
                                                mNumerador[linha1[0]-1][i] = 1;
                                                mNumerador[linha1[0]-1][i] = mNumerador[linha2[0]-1][i] * multFinal[0];
                                                mNumerador[linha2[0]-1][i] = 0;
                                                mDenominador[linha1[0]-1][i]=multFinal[1]*multFinal[3]*mDenominador[linha2[0]-1][i];
                                                mDenominador[linha2[0]-1][i]=0;
                                            }
                                        }
                                    }
                                    if(mDenominador[linha1[0]-1][i]!=0 && mDenominador[linha2[0]-1][i]!=0){
                                        auxDen = mDenominador[linha1[0]-1][i]*multFinal[3];
                                        mDenominador[linha1[0]-1][i] = mDenominador[linha2[0]-1][i] * multFinal[1];
                                        if(linha1[0]!=linha2[0])mDenominador[linha2[0]-1][i] = auxDen;
                                    }
                                }

                            }
                        else {
                            if (somarCheckBox.isSelected()) {
                                linha3[0]=linha1[0];
                                linha4[0]=linha2[0];
                                    if(multStr[3].contains("/")) { //multiplicador da linha 3
                                        String[] separado = multStr[3].split("/");
                                        multFinal[0] = Integer.parseInt(separado[0]);
                                        multFinal[1] = Integer.parseInt(separado[1]);
                                    }
                                    else{
                                        multFinal[0] = Integer.parseInt(multStr[3]);
                                        multFinal[1] = 1;
                                    }
                                    if(multStr[0].contains("/")){ //multiplicador da linha 4
                                        String[] separado2 = multStr[0].split("/");
                                        multFinal[2] = Integer.parseInt(separado2[0]);
                                        multFinal[3] = Integer.parseInt(separado2[1]);
                                    }
                                    else{
                                        multFinal[2] = Integer.parseInt(multStr[0]);
                                        multFinal[3] = 1;
                                    }
                                    for (int i = 0; i < d2[0]; i++) {
                                        if (incognita[linha3[0] - 1][i] != null) {
                                            mDenominador[linha3[0] - 1][i]=1;
                                            if(incognita[linha4[0] - 1][i] != null) {
                                                if(!multStr[3].equals("1") && !multStr[0].equals("1")) {
                                                    incognita[linha3[0]-1][i]=multStr[3]+"*("+String.copyValueOf(incognita[linha3[0] - 1][i].toCharArray()) + ")+" + multStr[0]+"*("+ String.copyValueOf(incognita[linha4[0] - 1][i].toCharArray())+")";
                                                }
                                                else{
                                                    if(!multStr[3].equals("1")) {
                                                        incognita[linha3[0]-1][i]=multStr[3]+"*("+String.copyValueOf(incognita[linha3[0] - 1][i].toCharArray()) + ")+" + String.copyValueOf(incognita[linha4[0] - 1][i].toCharArray());
                                                    }
                                                    else{
                                                        incognita[linha3[0]-1][i]=String.copyValueOf(incognita[linha3[0] - 1][i].toCharArray()) + "+" + multStr[0]+"*("+ String.copyValueOf(incognita[linha4[0] - 1][i].toCharArray())+")";
                                                    }
                                                }
                                            }
                                            else {
                                                if(!multStr[3].equals("1")) {
                                                    incognita[linha3[0]-1][i]=multStr[3]+"*("+String.copyValueOf(incognita[linha3[0] - 1][i].toCharArray())+")";
                                                }
                                                else{
                                                    incognita[linha3[0]-1][i]=String.copyValueOf(incognita[linha3[0] - 1][i].toCharArray());
                                                }
                                            }
                                        }
                                        else{
                                            if(!multStr[0].equals("1") && incognita[linha4[0] - 1][i] != null) {
                                                incognita[linha3[0]-1][i]= multStr[0]+"*("+ String.copyValueOf(incognita[linha4[0] - 1][i].toCharArray())+")";
                                            }
                                            else{
                                                if(incognita[linha4[0] - 1][i] != null) incognita[linha3[0]-1][i]= String.copyValueOf(incognita[linha4[0] - 1][i].toCharArray());
                                            }
                                        }
                                        if(incognita[linha4[0] - 1][i] != null){
                                            mDenominador[linha4[0] - 1][i]=1;
                                        }
                                        mNumerador[linha3[0] - 1][i] = mDenominador[linha4[0] - 1][i] * multFinal[3] * mNumerador[linha3[0] - 1][i] * multFinal[0] + mNumerador[linha4[0] - 1][i] * multFinal[2] * mDenominador[linha3[0] - 1][i] * multFinal[1];
                                        mDenominador[linha3[0] - 1][i] = mDenominador[linha3[0] - 1][i] * multFinal[1] * mDenominador[linha4[0] - 1][i] * multFinal[3];
                                        if(incognita[linha4[0] - 1][i] != null){
                                            mDenominador[linha4[0] - 1][i]=0;
                                        }
                                    }
                            }
                        }
                    mInt.setMaximumSize(new Dimension(100, 100));
                    for (int i = 0; i < d1[0]; i++) {
                        for (int j = 0; j < d2[0]; j++) {
                            if(mDenominador[i][j]!=0){
                                int i1 = simplificar(mNumerador[i][j], mDenominador[i][j])[0];
                                int i2 = simplificar(mNumerador[i][j], mDenominador[i][j])[1];
                                mNumerador[i][j] = i1;
                                mDenominador[i][j]= i2;
                            }
                            if(mDenominador[i][j]!=1 && mDenominador[i][j]!=0) {
                                if(incognita[i][j]==null){
                                    if(mNumerador[i][j]==0) blocos[i][j].setText("0");
                                    else blocos[i][j].setText(String.valueOf(mNumerador[i][j] + "/" + mDenominador[i][j]));
                                }
                                else{
                                    if(mNumerador[i][j]==0) blocos[i][j].setText(incognita[i][j] +String.valueOf(mDenominador[i][j]));
                                    else blocos[i][j].setText(String.valueOf( incognita[i][j]+ "+" + mNumerador[i][j] + "/" + mDenominador[i][j]));

                                }
                            }
                            else{
                                if(incognita[i][j]==null)  {
                                    if(mNumerador[i][j]==0) blocos[i][j].setText("0");
                                    else blocos[i][j].setText(String.valueOf(mNumerador[i][j]));
                                }
                                else{
                                        if(mNumerador[i][j]==0) blocos[i][j].setText(incognita[i][j]);
                                        else
                                            blocos[i][j].setText(String.valueOf(incognita[i][j]+ "+" + mNumerador[i][j]));
                                    }
                            }
                            mInt.add(blocos[i][j]);
                        }
                    }
                    troca1.setModel(new SpinnerNumberModel(0,0, d1[0],1));
                    troca2.setModel(new SpinnerNumberModel(0,0, d1[0],1));
                    mult1.setText("1");
                    mult2.setText("1");
                    ant.setMaximumSize(new Dimension(900, 1600));
                    mat1.setLayout(new GridLayout(d1[0],d2[0],1,1));
                    mat2.setLayout(new GridLayout(d1[0],d2[0],1,1));
                    mat3.setLayout(new GridLayout(d1[0],d2[0],1,1));
                    mat4.setLayout(new GridLayout(d1[0],d2[0],1,1));
                    mat5.setLayout(new GridLayout(d1[0],d2[0],1,1));
                    if(cont[0]==5){
                        mat1.removeAll();
                        mat1.repaint();
                        mat2.removeAll();
                        mat2.repaint();
                        mat3.removeAll();
                        mat3.repaint();
                        mat4.removeAll();
                        mat4.repaint();
                        mat5.removeAll();
                        mat5.repaint();
                    }
                    for (int i = 0; i < d1[0]; i++) {
                        for (int j = 0; j < d2[0]; j++) {
                            nums num = new nums();
                            antnum[i][j] = num;
                            antnum[i][j].setText(mStr[i][j]);
                            antnum[i][j].setForeground(new Color(117,129,148));
                            if(cont[0]==1) {
                                mat1.add(antnum[i][j]);
                            }
                            if(cont[0]==2){
                                mat2.add(antnum[i][j]);
                            }
                            if(cont[0]==3){
                                mat3.add(antnum[i][j]);
                            }
                            if(cont[0]==4) {
                                mat4.add(antnum[i][j]);
                            }
                            if(cont[0]==5){
                                mat5.add(antnum[i][j]);
                            }

                        }
                    }
                    if(cont[0]==5){
                        cont[0] = 1;
                        infos.removeAll();
                        infos.repaint();

                    }
                    else cont[0]++;
                    infos.setLayout(new GridLayout(1,1,1,1));
                    Label infoLinhas = new Label();
                    if(trocarCheckBox.isSelected()) infoLinhas.setText("L"+linha1[0]+"*"+multStr[3]+"<-> L"+linha2[0]+"*"+multStr[0]);
                    if(somarCheckBox.isSelected()) infoLinhas.setText("L"+linha1[0]+"<-> L"+linha1[0]+"*"+multStr[3]+" + L"+ linha2[0]+"*"+multStr[0]);
                    infoLinhas.setForeground(Color.white);
                    Component add = infos.add(infoLinhas);
                    setVisible(true);
            }
        });
        VOLTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                menuprincipal voltar = new menuprincipal(null);
            }
        });
        SAIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
    public static class Bloco extends JTextField{
        public Bloco(){
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.white);
            setMaximumSize(new Dimension(2, 3));
            setHorizontalAlignment(0);

        }
    }
    public static class nums extends JLabel{
        public nums(){
            setBackground(Color.LIGHT_GRAY);
            setMaximumSize(new Dimension(2, 3));
            setHorizontalAlignment(0);
        }
    }
    public static int[] simplificar(int det, int denDet){
        int[] v = new int[2];
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
        v[0]= det;
        v[1] = denDet;
        return v;
    }
}
