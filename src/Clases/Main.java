package Clases;

import javax.swing.JFrame;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        JFrame aplicacion = new JFrame("El juego de la vida");
        aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        aplicacion.setSize(840,610);
        aplicacion.setResizable(false);
        aplicacion.setLocationRelativeTo(null);
        
        Tablero panel = new Tablero();
        aplicacion.add(panel);
        aplicacion.setVisible(true);

        while(true)
        {
            aplicacion.repaint();
            Thread.sleep(100);
        }
    }
}
