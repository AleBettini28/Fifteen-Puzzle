package javabettini.giocodel16;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class Frame extends JFrame {
    
    // OBJECTS
    protected JButton[][] matrice = new JButton[4][4];
    private final JPanel gridPanel = new JPanel();
    private final JPanel flowPanel = new JPanel();
    private final JPanel southPanel = new JPanel();
    private final JLabel mosseTxt;
    private final Listener listener;
    
    // VARIABLES
    private float tempo = 0;
    private int mosse = 0;
    private final JLabel tempoLabel; 
    private Timer timer; 
    
    public Frame() {
        super("Gioco del 15");
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        c.setBackground(Color.white);
        
        gridPanel.setLayout(new GridLayout(4, 4));
        gridPanel.setBackground(Color.white);
        flowPanel.setLayout(new FlowLayout());
        flowPanel.setBackground(Color.white);
        
        timer = new Timer(100, new TimerListener(this));
        
        ArrayList<Integer> numeri = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numeri.add(i);
        }
        
        Collections.shuffle(numeri);

        while(!isWinnable(numeri)){
            Collections.shuffle(numeri);
        }
        
        int cont = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) {
                    matrice[i][j] = new JButton("");
                    matrice[i][j].setVisible(false);
                } else {
                    cont++;
                    matrice[i][j] = new JButton("" + numeri.get(cont - 1));
                }
                matrice[i][j].setBackground(Color.LIGHT_GRAY);
                gridPanel.add(matrice[i][j]);
            }
        }
        c.add(gridPanel, BorderLayout.CENTER);
        
        
        //label sopra
        tempoLabel = new JLabel("Tempo: " + tempo);
        flowPanel.add(tempoLabel);
        mosseTxt = new JLabel("Mosse: " + mosse);
        flowPanel.add(mosseTxt);
        
        JPanel title = new JPanel();
        title.setLayout(new FlowLayout());
        title.setBackground(Color.white);
        Font  f1  = new Font(Font.SERIF, Font.PLAIN,  24);
        JLabel titoloTxt = new JLabel("GIOCO DEL 15");
        titoloTxt.setFont(f1);
        title.add(titoloTxt);
        JPanel tempGrid = new JPanel();
        tempGrid.setLayout(new GridLayout(2, 1));
        tempGrid.add(title);
        tempGrid.add(flowPanel);
        
        c.add(tempGrid, BorderLayout.NORTH);
        
        listener = new Listener(this);
        for (JButton[] matrice1 : matrice) {
            for (JButton matrice11 : matrice1) {
                matrice11.addActionListener(listener);
                matrice11.setActionCommand("Button");
            }
        }
        
        JButton reShuffle = new JButton("Reset");
        reShuffle.setActionCommand("RESHUFFLE");
        reShuffle.addActionListener(listener);
        reShuffle.setBackground(Color.LIGHT_GRAY);
        
        JButton exit = new JButton("Exit");
        exit.setActionCommand("EXIT");
        exit.addActionListener(listener);
        exit.setBackground(Color.LIGHT_GRAY);
        
        southPanel.setLayout(new FlowLayout());
        southPanel.setBackground(Color.white);
        southPanel.add(reShuffle);
        southPanel.add(exit);
        c.add(southPanel, BorderLayout.SOUTH);
        
        c.add(new JLabel("       "), BorderLayout.EAST);
        c.add(new JLabel("       "), BorderLayout.WEST);
        
        //END
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void startTimer() {
        timer.start();
    }
    
    public void incrementTempo() {
        tempo += 0.1;
        tempoLabel.setText(String.format("Tempo: %.1f", tempo));
    }
    
    public JButton[][] getMatrice(){
        return matrice;
    }
    
    public void aumentaMosse(){
        mosse++;
        mosseTxt.setText("Mosse: " + mosse);
    }
    
    public int getMosse(){
        return mosse;
    }
    
    public void restart(){
        ArrayList<Integer> numeri = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numeri.add(i);
        }
        Collections.shuffle(numeri);
        
        while(!isWinnable(numeri)){
            Collections.shuffle(numeri);
        }
        
        int cont = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) {
                    matrice[i][j].setText("");
                    matrice[i][j].setVisible(false);
                } else {
                    cont++;
                    matrice[i][j].setText("" + numeri.get(cont - 1));
                    matrice[i][j].setVisible(true);
                }
            }
        }
        
        //azzera timer e mosse
        listener.setFlag();
        tempo = 0;
        mosse = 0;
        tempoLabel.setText(String.format("Tempo: %.1f", tempo));
        timer.stop();
        mosseTxt.setText("Mosse: " + mosse);
    }
    
    public void stopTimer(){
        timer.stop();
    }
    
    public String getTempo(){
        return String.format("Tempo: %.1f", tempo);
    }
    
    public boolean isWinnable(ArrayList<Integer> numeri) {
        int inversioni = 0;
        for (int i = 0; i < numeri.size(); i++) {
            for (int j = i + 1; j < numeri.size(); j++) {
                if (numeri.get(i) > numeri.get(j)) {
                    inversioni++;
                }
            }
        }
    
        return inversioni % 2 == 0;
    }
}