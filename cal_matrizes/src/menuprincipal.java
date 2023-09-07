import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuprincipal extends JDialog{
    private JButton DETERMINANTEButton;
    private JButton ESCALONAMENTOButton;
    private JButton OPERAÇÕESButton;
    private JButton SAIRButton;
    private JPanel mPrincipal;

    public menuprincipal(JFrame parent){
        super(parent);
        setFocusableWindowState(true);
        setTitle("Menu Principal");
        setContentPane(mPrincipal);
        setMinimumSize(new Dimension(950, 767));
        setModal(true);
        setLocationRelativeTo(parent);
        DETERMINANTEButton.setBackground(Color.white);
        ESCALONAMENTOButton.setBackground(Color.white);
        OPERAÇÕESButton.setBackground(Color.white);
        SAIRButton.setBackground(Color.white);
        SAIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ESCALONAMENTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                escalonar esc = new escalonar(null);
            }
        });
        DETERMINANTEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                determinante dete = new determinante(null);

            }
        });
        OPERAÇÕESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Aluna: Sabrina Sousa Carvalho\nCurso: Matemática Aplicada a Negócios - USP", "Créditos", JOptionPane.WARNING_MESSAGE);
            }
        });
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        menuprincipal inicio = new menuprincipal(null);
    }
}

