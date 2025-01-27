package javabettini.giocodel16;

import java.awt.event.*;

public class TimerListener implements ActionListener {
    private Frame frame;
    
    public TimerListener(Frame frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.incrementTempo();
    }
}