package Clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

public class Tablero extends javax.swing.JPanel 
{

    public Tablero()
    {
        initComponents();
        addEventos();
    }
    
    public void dibujarContorno(Graphics g)
    {     
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 580, 10);
        g.fillRect(0, 0, 10, 580);
        g.fillRect(570, 0, 10, 580);
        g.fillRect(0, 551, 580, 10);
    }
    
    public void dibujartablero(Graphics g)
    {
        int x = 10;
        int y = 10;
        for(int i = 1 ; i < 28 ; i++)
        {
            for(int j = 1 ; j < 29 ; j++)
            {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, 20, 20);
                x += 20;
            }
            x = 10;
            y +=20;
        }
    }
    
    public void paintComponent( Graphics g )
    {
        dibujarContorno(g);
        dibujartablero(g);
    } 
    
    public void addEventos()
    {
        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseMoved(MouseEvent evento)
            {
                System.out.println("Sus coordenadas son:"+evento.getX()+" "+evento.getY());
            }
        });
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
