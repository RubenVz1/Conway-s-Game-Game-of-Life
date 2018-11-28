package Clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class Tablero extends javax.swing.JPanel implements MouseListener
{

    private Vida[][] tablero;
    
    public Tablero()
    {
        initComponents();
        tablero = new Vida[28][28];
        for(int i = 0 ; i < 28 ; i++)
            for(int j = 0 ; j < 28 ; j++)
                tablero[i][j] = new Vida();
        addMouseListener(this);
        setFocusable(true);
    }
    
    public void dibujarContorno(Graphics g)
    {     
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 580, 10);
        g.fillRect(0, 0, 10, 580);
        g.fillRect(570, 0, 10, 580);
        g.fillRect(0, 571, 580, 10);
    }
    
    public void dibujarTablero(Graphics g)
    {
        int coordenada_x = 10;
        int coordenada_y = 10;
        for(int i = 0 ; i < 28 ; i++)
        {
            for(int j = 0 ; j < 28 ; j++)
            {
                if(tablero[i][j].isVive())
                {
                     g.setColor(Color.RED);
                }
                else
                    g.setColor(Color.WHITE);
                g.fillRect(coordenada_x,coordenada_y,20,20);
                g.setColor(Color.BLACK);
                g.drawRect(coordenada_x,coordenada_y,20,20);
                coordenada_x += 20;
            }
            coordenada_x = 10;
            coordenada_y +=20;
        }
    }
    
    public void setVida(int coordenada_x, int coordenada_y)
    {
        int limite_superior = 11; 
        int limite_inferior = 28;
        int fila = 0;
        int columna = 0;
        for(int i = 0 ; i < 28 ; i++)
        {
            if(coordenada_y > limite_superior && coordenada_y < limite_inferior)
            {
                fila = i;
            }
            if(coordenada_x > limite_superior && coordenada_x < limite_inferior)
            {
                columna = i;
            }
            limite_superior += 20;
            limite_inferior += 20;
        }
        System.out.println("Fila: "+fila+" Columna: "+columna);
        if(tablero[fila][columna].isVive())
        {
            tablero[fila][columna].setVive(false);
        }
        else
        {
            tablero[fila][columna].setVive(true);
        }
    }
    
    public void paintComponent( Graphics g )
    {
        dibujarContorno(g);
        dibujarTablero(g);
    } 
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Se presiono en: X:"+e.getX()+" Y:"+e.getY());
        setVida(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setForeground(new java.awt.Color(102, 102, 102));
        setMinimumSize(new java.awt.Dimension(800, 590));
        setPreferredSize(new java.awt.Dimension(800, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
