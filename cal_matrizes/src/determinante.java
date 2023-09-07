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
        VOLTARButton.setBackground(Color.white);
        SAIRButton.setBackground(Color.white);
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
                detFinalOficial m2 = new detFinalOficial(null,2);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                detFinalOficial m2 = new detFinalOficial(null,5);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                detFinalOficial m3 = new detFinalOficial(null,3);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                detFinalOficial m2 = new detFinalOficial(null,4);
            }
        });
        setVisible(true);

    }
}
