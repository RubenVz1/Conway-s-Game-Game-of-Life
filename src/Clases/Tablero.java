package Clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Tablero extends javax.swing.JPanel implements MouseListener
{

    private Vida[][] tablero_1;
    private Vida[][] tablero_2;
    private int contador;
    private boolean iniciador;
    private int vidas;
    private int generaciones;

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
        jLabel_Vidas.setText(""+vidas);
    }

    public int getGeneraciones() {
        return generaciones;
    }

    public void setGeneraciones(int generaciones) {
        this.generaciones = generaciones;
        jLabel_Generaciones.setText(""+this.generaciones);
    }
    
    public Tablero()
    {
        initComponents();
        tablero_1 = new Vida[28][28];
        tablero_2 = new Vida[28][28];
        for(int i = 0 ; i < 28 ; i++)
            for(int j = 0 ; j < 28 ; j++)
            {
                tablero_1[i][j] = new Vida();
                tablero_2[i][j] = new Vida();
            }
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
                if(tablero_1[i][j].isVive())
                {
                     g.setColor(Color.BLUE);
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
        if(coordenada_x < 10 && coordenada_x > 571  && coordenada_y < 10 && coordenada_y > 571)
        {
            return;
        }
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
        if(tablero_1[fila][columna].isVive())
        {
            tablero_1[fila][columna].setVive(false);
            setVidas(vidas-1);
            
        }
        else
        {
            tablero_1[fila][columna].setVive(true);
            setVidas(vidas+1);
        }
    }
    
    public void limpiarTablero()
    {
        for(int i = 0 ; i < 28 ; i++)
            for(int j = 0 ; j < 28 ; j++)
                tablero_1[i][j].setVive(false);
        setGeneraciones(0);
        setVidas(0);
    }
    
    public void actualizarTablero()
    {
        setVidas(0);
        for(int i = 0 ; i < 28 ; i++)
        {
            for(int j = 0 ; j < 28 ; j++)
            {
                if(i > 0 && j > 0)
                    if(tablero_1[i-1][j-1].isVive())
                        contador++;
                if(i > 0)
                    if(tablero_1[i-1][j].isVive())
                        contador++;
                if(i > 0 && j < 27)
                    if(tablero_1[i-1][j+1].isVive())
                        contador++;
                if(j > 0)
                    if(tablero_1[i][j-1].isVive())
                        contador++;
                if(j < 27)
                    if(tablero_1[i][j+1].isVive())
                        contador++;
                if(i < 27 && j > 0)
                    if(tablero_1[i+1][j-1].isVive())
                        contador++;
                if(i < 27)
                    if(tablero_1[i+1][j].isVive())
                        contador++;
                if(i < 27 && j < 27)
                    if(tablero_1[i+1][j+1].isVive())
                        contador++;
                if(tablero_1[i][j].isVive())
                    setVidas(vidas+1);
                if(contador == 3 && tablero_1[i][j].isVive() == false)
                {
                    tablero_2[i][j].setVive(true);
                    contador = 0;
                    continue;
                }
                if(contador > 1 && tablero_1[i][j].isVive() == true && contador < 4)
                {
                    tablero_2[i][j].setVive(true);
                }
                else
                {
                    tablero_2[i][j].setVive(false);
                }
                contador = 0;
            }
        }
        Vida[][] tablero_aux = tablero_1;
        tablero_1 = tablero_2;
        tablero_2 = tablero_aux;
        setGeneraciones(generaciones+1);
    }
    
    public void paintComponent( Graphics g )
    {
        dibujarContorno(g);
        dibujarTablero(g);
        if(iniciador)
        {
            actualizarTablero();
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
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

        jButton_Iniciar = new javax.swing.JButton();
        jButton_Parar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel_Vidas = new javax.swing.JLabel();
        jButton_Limpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel_Generaciones = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setForeground(new java.awt.Color(102, 102, 102));
        setMinimumSize(new java.awt.Dimension(800, 590));
        setPreferredSize(new java.awt.Dimension(800, 590));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_Iniciar.setText("Iniciar");
        jButton_Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IniciarActionPerformed(evt);
            }
        });
        add(jButton_Iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, 240, -1));

        jButton_Parar.setText("Parar");
        jButton_Parar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PararActionPerformed(evt);
            }
        });
        add(jButton_Parar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 480, 240, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Vida");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(583, 270, 250, -1));

        jLabel_Vidas.setFont(new java.awt.Font("Tahoma", 0, 80)); // NOI18N
        jLabel_Vidas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Vidas.setText("0");
        add(jLabel_Vidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, 240, 70));

        jButton_Limpiar.setText("Limpiar");
        jButton_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LimpiarActionPerformed(evt);
            }
        });
        add(jButton_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 520, 240, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Generacion");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 240, -1));

        jLabel_Generaciones.setFont(new java.awt.Font("Tahoma", 0, 80)); // NOI18N
        jLabel_Generaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Generaciones.setText("0");
        add(jLabel_Generaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 170, 250, 70));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("El juego de la vida");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 240, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Conway Game");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 240, -1));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setOpaque(true);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 260, 610));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_PararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PararActionPerformed
        iniciador = false;
    }//GEN-LAST:event_jButton_PararActionPerformed

    private void jButton_IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IniciarActionPerformed
       iniciador = true;
    }//GEN-LAST:event_jButton_IniciarActionPerformed

    private void jButton_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LimpiarActionPerformed
        limpiarTablero();
    }//GEN-LAST:event_jButton_LimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Iniciar;
    private javax.swing.JButton jButton_Limpiar;
    private javax.swing.JButton jButton_Parar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_Generaciones;
    private javax.swing.JLabel jLabel_Vidas;
    // End of variables declaration//GEN-END:variables

}
