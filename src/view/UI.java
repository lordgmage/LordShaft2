package view;

import main.LordShaft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class UI {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI window = new UI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public UI() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 828, 616);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnStart = new JButton("Start");
        btnStart.setBounds(318, 249, 115, 29);
        frame.getContentPane().add(btnStart);


        final JCheckBox chckbxChopAndCut = new JCheckBox("Chop and Cut");
        chckbxChopAndCut.setBounds(310, 134, 139, 29);
        frame.getContentPane().add(chckbxChopAndCut);
        JLabel lblLordshaft = new JLabel("LordShaft");
        lblLordshaft.setFont(new Font("Times New Roman", Font.PLAIN, 41));
        lblLordshaft.setBounds(297, 0, 168, 71);
        frame.getContentPane().add(lblLordshaft);

        frame.getContentPane().add(btnStart);
        btnStart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LordShaft.bank = !chckbxChopAndCut.isSelected();
                LordShaft.start = true;
                frame.setVisible(false);
            }
        });

    }

}
