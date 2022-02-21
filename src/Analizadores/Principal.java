package Analizadores;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Scanner;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.LinkedList;

import java.io.BufferedReader;


/**
 *
 * @author gohan
 */
public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
    }
    
    public LinkedList lista_evaluar;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NRuta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        btnAnalizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        NRuta.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        NRuta.setText("Se está utilizando: ");

        txtArchivo.setColumns(20);
        txtArchivo.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        jButton1.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jButton1.setText("Generar Autómatas");

        btnAnalizar.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btnAnalizar.setText("Analizar Entradas");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        txtSalida.setEditable(false);
        txtSalida.setColumns(20);
        txtSalida.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        txtSalida.setRows(5);
        jScrollPane2.setViewportView(txtSalida);

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N

        jMenuItem1.setText("Nuevo Archivo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Abrir Archivo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Guardar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Guardar como");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(btnAnalizar)))
                        .addGap(0, 689, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NRuta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnAnalizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser guardarComo = new JFileChooser();
        guardarComo.setApproveButtonText("Nuevo");
        guardarComo.showSaveDialog(null);
        File archivo = new File(guardarComo.getSelectedFile() + ".exp");

        try {
            BufferedWriter salida = new BufferedWriter(new FileWriter(archivo));
            salida.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        NRuta.setText("Se está utilizando: " + guardarComo.getSelectedFile() + ".exp");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Scanner entrada = null;
        String contenido = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            File f = new File(ruta);
            entrada = new Scanner(f);
            while (entrada.hasNext()) {
                contenido += entrada.nextLine() + "\n";
                System.out.println();
            }
            txtArchivo.setText(contenido);
            NRuta.setText("Se está utilizando: " + ruta);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún fichero", "Alert", JOptionPane.WARNING_MESSAGE);
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        File archivo = new File(NRuta.getText().split(" ")[3]);
        //System.out.println(NRuta.getText().split(" ")[3]);
        try {
            BufferedWriter salida = new BufferedWriter(new FileWriter(archivo));
            salida.write(txtArchivo.getText());
            salida.close();
            JOptionPane.showMessageDialog(this, "Guardado con Exito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error.", "Alert", JOptionPane.WARNING_MESSAGE);
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JFileChooser guardarComo = new JFileChooser();
        guardarComo.setApproveButtonText("Guardar como");
        guardarComo.showSaveDialog(null);
        File archivo = new File(guardarComo.getSelectedFile() + ".exp");
        try {
            BufferedWriter salida = new BufferedWriter(new FileWriter(archivo));
            salida.write(txtArchivo.getText());
            salida.close();
            JOptionPane.showMessageDialog(this, "Guardado con Exito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error.", "Alert", JOptionPane.WARNING_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        txtSalida.setText("");        
        //String ST = txtArchivo.getText();
        //Sintax s = new Sintax(new LexC(new StringReader(ST)));
        Sintax sintactico = new Sintax(new LexC(new BufferedReader( new StringReader(txtArchivo.getText()))));
        try {
            
            LinkedList ER = new LinkedList();
            sintactico.parse();
            //LinkedList Conjuntos = sintactico.Conjuntos;
            //LinkedList a = (LinkedList) Conjuntos.getFirst();
            //System.out.println(a.getLast());
            
            //LinkedList Pruebas = sintactico.EProbar;
            //System.out.println(Pruebas);
            
            ER = sintactico.ExpT;
            LinkedList ERR=Metodos.ordenarExpresionRegular(ER);
            //System.out.println(ER);
            for (int i = 0; i < ER.size(); i++) {
                Metodos.TablaSig = new LinkedList();
                transiciones.Transiciones = new LinkedList();
                transiciones.Encabezados = new LinkedList();
                LinkedList Actual = (LinkedList) ERR.get(i);
                Metodos.Nodo ac = (Metodos.Nodo) Actual.getLast();
                //System.out.println("CONTENIDO "+Actual.getFirst()+Actual.get(1));
                Metodos.con=1;
                Metodos.noNodo=1;
                //***** VERIFICANDO ANULABLES *****
                Metodos.parametrosArbolito(ac);
                Metodos.parametros2Arbolito(ac);
                //*********************************
                Metodos.listaPrimeros(ac);
                Metodos.listaUltimos(ac);
                Metodos.grafica(ac,""+Actual.getFirst()+": "+Actual.get(1));
                //Metodos.recorrerArbolito(ac);
                //System.out.println(Metodos.dot);
                String cuadro = txtSalida.getText();
                txtSalida.setText(cuadro+Metodos.generarImagen(""+Actual.getFirst())+"\n");
                Metodos.dot="";
                
                cuadro = txtSalida.getText();
                txtSalida.setText(cuadro+Metodos.tablaSiguientes(ac,""+Actual.getFirst(),""+Actual.getFirst()+": "+Actual.get(1))+"\n");
                
                cuadro = txtSalida.getText();
                txtSalida.setText(cuadro+transiciones.tablaEstados(Metodos.TablaSig, ac,""+Actual.getFirst(),""+Actual.getFirst()+": "+Actual.get(1))+"\n");
                
                cuadro = txtSalida.getText();
                txtSalida.setText(cuadro+transiciones.Automata( ac,""+Actual.getFirst(),""+Actual.getFirst()+": "+Actual.get(1))+"\n");
            }
            
            String cuadro = txtSalida.getText();
            txtSalida.setText(cuadro+"Analisis realizado correctamente\n");
            txtSalida.setForeground(new Color(25, 111, 61));
            sintactico.ExpT=new LinkedList();
            //String cadena = Metodos.obtener(ST);
            //txtSalida.setText(cadena);
        } catch (Exception ex) {
            ex.printStackTrace();
            //Symbol sym = sintactico.getS();
            //txtSalida.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
            //txtSalida.setForeground(Color.red);
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NRuta;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtArchivo;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}
