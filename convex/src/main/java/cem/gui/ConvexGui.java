/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cem.gui;

import cem.convex.ConvexPolygon;
import cem.convex.Point;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar
 */
public class ConvexGui extends javax.swing.JFrame {

    /**
     * Creates new form convex
     */
    public ConvexGui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jButton1.setText("generate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(729, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)
                                .addContainerGap(665, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void formMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        points.add(new Point(evt.getX(), evt.getY()));

        drawPoint(evt.getX(), evt.getY(), Color.red);
    }

    List<Point> points = new ArrayList<>();

    public void drawPoint(int x, int y, Color c) {
        Graphics graphics = this.getGraphics();
        graphics.setColor(c);
        int r = 5;
        int d = r * 2;

        graphics.fillOval(x - r, y - r, d, d);
    }

    public void drawPolygon(int x[], int y[], int n) {
        Graphics graphics = this.getGraphics();
        graphics.setColor(Color.black);

        graphics.drawPolygon(x, y, n);
    }

    private void formMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ConvexPolygon cp = new ConvexPolygon();
        List<Point> list = cp.create(points);

        int n = list.size();
        int []x = new int[n];
        int []y = new int[n];

        int i = 0;
        for (Point p : list) {
            x[i] = p.getX();
            y[i] = p.getY();

            i++;
        }

        drawPolygon(x, y, n);
        drawPoint(list.get(0).getX(), list.get(0).getY(), Color.blue);

        points.clear();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConvexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConvexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConvexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConvexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConvexGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    // End of variables declaration                   
}
