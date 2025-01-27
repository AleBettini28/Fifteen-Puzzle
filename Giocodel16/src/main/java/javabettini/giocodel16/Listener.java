package javabettini.giocodel16;

import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class Listener implements ActionListener {

    private final Frame frame;
    private int flag = 0;
    private final Gioco gioco;
    private final String button = "Button";
    private final String reshuffle = "RESHUFFLE";
    private final String restart = "RESTART";
    private final String exit = "EXIT";
    
    public Listener(Frame frame) {
        this.frame = frame;
        gioco = new Gioco(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String txt = e.getActionCommand();
        if (txt.equals(button)) {
            if (flag == 0) {
                frame.startTimer();
                flag = 1;
            }
            if (gioco.checkMossa((JButton) e.getSource())) {
                gioco.muovi();
            }
        } 
        else if (txt.equals(reshuffle)) {
            frame.restart();
        }
        else if (txt.equals(exit)){
            System.exit(0);
        }

        if (gioco.controllaVittoria()) {
            //STOP TIMER AND WIN FRAME
            JOptionPane.showMessageDialog(null, "Hai vinto! " + frame.getTempo() + " Mosse: " + frame.getMosse(), "Vittoria", INFORMATION_MESSAGE);
            frame.restart();
        }
    }
    
    public void setFlag(){
        flag = 0;
    }
}