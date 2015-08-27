/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stali;

import com.google.common.base.Strings;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 *
 * @author cesarmora
 */
public class Stali extends javax.swing.JFrame {

    /**
     * Creates new form StaliFrame
     */
    public Stali() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        textOrigin = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textFinal = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        idBeginText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        noQuote = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tabText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        transformUnitId = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        numericId = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textOrigin.setColumns(20);
        textOrigin.setRows(5);
        jScrollPane1.setViewportView(textOrigin);

        jButton1.setText("split into strings");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("row content");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        textFinal.setColumns(20);
        textFinal.setRows(5);
        jScrollPane2.setViewportView(textFinal);

        jButton4.setText("clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("no quote for columns: ");

        jLabel2.setText("tab");

        jLabel3.setText("with id");

        transformUnitId.setText("transform unitId");

        jButton3.setText("pre process");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("map");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("item_unit_supp");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        numericId.setText("numericId");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(transformUnitId))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(numericId)
                                                                        .addComponent(noQuote, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(52, 52, 52)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel3)
                                                                                .addGap(7, 7, 7)))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(idBeginText)
                                                                        .addComponent(tabText, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButton4))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(160, 160, 160)
                                                .addComponent(jButton3)
                                                .addGap(32, 32, 32)
                                                .addComponent(jButton1)
                                                .addGap(31, 31, 31)
                                                .addComponent(jButton5)
                                                .addGap(30, 30, 30)
                                                .addComponent(jButton6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                                .addComponent(jButton2)
                                                .addGap(130, 130, 130)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton4)
                                        .addComponent(idBeginText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(noQuote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(tabText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(transformUnitId)
                                                        .addComponent(numericId))))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3)
                                        .addComponent(jButton5)
                                        .addComponent(jButton6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>


    // preProcess Button
    private void jButton3ActionPerformed(ActionEvent evt) {

        StringBuilder dataDest = new StringBuilder("");

        String lines[] = preProcessAndGetLines();
        for (String line : lines) {
            boolean first = true;

            String tokens[] = line.split("\t");
            for (String token : tokens) {
                if (first) {
                    first = false;
                } else {
                    dataDest.append("\t");
                }

                dataDest.append(getUnitValue(token, transformUnitId.isSelected()));
            }
            dataDest.append("\n");

        }

        textFinal.setText(dataDest.toString());
    }

    // clear button
    private void jButton4ActionPerformed(ActionEvent evt) {
        textOrigin.setText("");
    }


    // Map button
    private void jButton5ActionPerformed(ActionEvent evt) {
        StringBuilder dataDest = new StringBuilder("");

        boolean transformUnit = transformUnitId.isSelected();

        String lines[] = preProcessAndGetLines();

        String var = "map";

        for (String line : lines) {
            String tokens[] = line.split("\t");
            int len = tokens.length;

            dataDest.append(var);
            dataDest.append(".put(\"");
            for (int i = 0; i<len-1; i++) {
                dataDest.append(tokens[i]);
            }
            dataDest.append("\", \"");
            dataDest.append(tokens[len-1]);
            dataDest.append("\");\n");
        }

        textFinal.setText(dataDest.toString());
    }

    // Split into strings
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String dataOrig = textOrigin.getText().replace("\"", "\\\"");
        StringBuilder dataDest = new StringBuilder("");

        String endLine = "\n";
        int len = endLine.length();
        boolean first = true;
        int old = 0;
        int pos = dataOrig.indexOf(endLine, 0);
        while (pos > -1) {
            addEndLine(dataDest, first);

            String line = dataOrig.substring(old, pos + len);
            if (line.equals("\n") || line.equals(System.lineSeparator())) {
                line = "";
            } else {
                line = line.substring(0, line.length() - len);
            }

            dataDest.append("\"");
            dataDest.append(line);
            dataDest.append("\"");

            old = pos+len;
            pos = dataOrig.indexOf(endLine, pos + len);
            first = false;
        }
        addEndLine(dataDest, first);

        dataDest.append("\"");
        dataDest.append(dataOrig.substring(old));
        dataDest.append("\"");

        textFinal.setText(dataDest.toString());
    }


    public static void addEndLine(StringBuilder st, boolean first) {
        if (!first) {
            st.append(" + ");
            st.append("\"\\n\" +");
            st.append("\n");
        }
    }

    // Item Unit Supplier
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        StringBuilder dataDest = new StringBuilder("");

        String lines[] = preProcessAndGetLines();

        for (String line : lines) {
            String tokens[] = line.split("\t");
            String itemUnit = ItemUnits.getItemUnitId(tokens[0], tokens[1]);
            if (!Strings.isNullOrEmpty(itemUnit)) {
                dataDest.append(tokens[0]);
                dataDest.append("\t");
                dataDest.append(tokens[2]);
                dataDest.append("\t");
                dataDest.append(itemUnit);
                dataDest.append("\n");
            }
        }

        textFinal.setText(dataDest.toString());
    }

    // Row Content
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        StringBuilder dataDest = new StringBuilder("");

        String noQuoteColumns[] = noQuote.getText().split(" ");
        java.util.List<Integer> noQuoteFor = new ArrayList<>();
        for (String column: noQuoteColumns) {
            if (Strings.isNullOrEmpty(column))
                continue;

            noQuoteFor.add(Integer.valueOf(column));
        }

        boolean transformUnit = transformUnitId.isSelected();

        int count = 1;
        String tab = tabText.getText();
        String idBegin = idBeginText.getText();
        String lines[] = preProcessAndGetLines();
        for (String line : lines) {
            boolean first = true;

            int tkNumber = 1;
            String tokens[] = line.split("\t");
            for (String token : tokens) {
                if (first) {
                    dataDest.append(tab);
                    dataDest.append("<Row>");

                    if (!Strings.isNullOrEmpty(idBegin)) {
                        dataDest.append("\"");
                        dataDest.append(idBegin);
                        dataDest.append(count);
                        dataDest.append("\"");
                        dataDest.append(",");
                    }
                    first = false;
                } else {
                    dataDest.append(",");
                }

                String par = "";
                if (!noQuoteFor.contains(tkNumber))
                    par = "\"";

                dataDest.append(par);
                dataDest.append(getUnitValue(token, transformUnit));
                dataDest.append(par);

                tkNumber++;
            }

            if (!first) {
                dataDest.append("</Row>\n");
            }
            count++;
        }

        textFinal.setText(dataDest.toString());
    }

    public String[] preProcessAndGetLines() {
        // TODO add your handling code here:
        String dataOrig = textOrigin.getText().replace("\"", "\\\"");
        dataOrig = dataOrig.replace("&", "&amp;");
        dataOrig = dataOrig.replace(",", ".");

        String endLine = "\n";
        String lines[] = dataOrig.split(endLine);

        return lines;
    }

    public static String getUnitValue(String word, boolean doIt) {
        String text = Units.getUnitValue(word);

        return Strings.isNullOrEmpty(text) || !doIt? word : text;
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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Stali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stali().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField idBeginText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField noQuote;
    private javax.swing.JCheckBox numericId;
    private javax.swing.JTextField tabText;
    private javax.swing.JTextArea textFinal;
    private javax.swing.JTextArea textOrigin;
    private javax.swing.JCheckBox transformUnitId;
    // End of variables declaration                   
}
