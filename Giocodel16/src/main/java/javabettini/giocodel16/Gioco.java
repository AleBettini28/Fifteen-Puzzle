package javabettini.giocodel16;

import javax.swing.*;

public class Gioco {

    private final Frame frame;
    private JButton[][] matrice;
    private int posX, posY;
    private int posVuota;
    
    public Gioco(Frame frame) {
        this.frame = frame;
    }

    //controlla se la mossa si può fare
    public boolean checkMossa(JButton button) {
        matrice = frame.getMatrice();
        String val = button.getText();

        // Trova le coordinate della casella premuta
        posX = -1;
        posY = -1;
        boolean found = false;

        for (int i = 0; i < 4 && !found; i++) {
            for (int j = 0; j < 4 && !found; j++) {
                if (matrice[i][j].getText().equals(val)) {
                    posX = i;
                    posY = j;
                    found = true;
                }
            }
        }

        if (!found) {
            return false;
        }
        
        //controllo caselle vuote
        if (posX > 0 && !matrice[posX - 1][posY].isVisible()) { //casella sopra
            posVuota = 1;   //1 per sopra
            return true;
        }
        if (posX < 3 && !matrice[posX + 1][posY].isVisible()) { //casella sotto
            posVuota = 2;   //2 per sotto
            return true;
        }
        if (posY > 0 && !matrice[posX][posY - 1].isVisible()) { //casella a sinistra
            posVuota = 3;   //3 per sinistra
            return true;
        }
        if (posY < 3 && !matrice[posX][posY + 1].isVisible()) { //casella a destra
            posVuota = 4;   //4 per destra
            return true;
        }

        // Nessuna casella vuota intorno
        return false;
    }

    //muove il bottone nella corretta casella
    public void muovi() {
        frame.aumentaMosse();
        String value;
        switch(posVuota){
            case 1:
                value = matrice[posX][posY].getText();  //prende il testo del bottone
                matrice[posX][posY].setText(matrice[posX-1][posY].getText());
                matrice[posX][posY].setVisible(false);
                matrice[posX-1][posY].setText(value);
                matrice[posX-1][posY].setVisible(true);
                break;
            
            case 2:
                value = matrice[posX][posY].getText();
                matrice[posX][posY].setText(matrice[posX + 1][posY].getText());
                matrice[posX][posY].setVisible(false);
                matrice[posX + 1][posY].setText(value);
                matrice[posX + 1][posY].setVisible(true);
                break;
              
            case 3:
                value = matrice[posX][posY].getText();
                matrice[posX][posY].setText(matrice[posX][posY - 1].getText());
                matrice[posX][posY].setVisible(false);
                matrice[posX][posY - 1].setText(value);
                matrice[posX][posY - 1].setVisible(true);
                break;
                
            case 4:
                value = matrice[posX][posY].getText();
                matrice[posX][posY].setText(matrice[posX][posY + 1].getText());
                matrice[posX][posY].setVisible(false);
                matrice[posX][posY + 1].setText(value);
                matrice[posX][posY + 1].setVisible(true);
                break;
        }
    }

    //controlla se i bottoni sono nell'ordine corretto
    public boolean controllaVittoria() {
    int cont = 1;
    matrice = frame.getMatrice();
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            String buttonText = matrice[i][j].getText();
            if (buttonText == null || buttonText.isEmpty()) {
                if (cont != 16) {
                    return false;
                }
            } 
            else {
                try {
                    int temp = Integer.parseInt(buttonText);
                    if (temp != cont) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            cont++;
        }
    }
    return true;
    }
}