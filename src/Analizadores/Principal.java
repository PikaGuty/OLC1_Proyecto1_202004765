package Analizadores;

import java.awt.Color;
import java.awt.Image;
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
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author gohan
 */
public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
    }
    
    public LinkedList lista_evaluar;
    public static LinkedList automatas = new LinkedList();
    Sintax sintactico;
    public static int cErrores = 0;
    public static String DotErrores;
    public static boolean existError=false;
    
    private ImageIcon image;
    private Icon icon;

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
        lblImagen = new javax.swing.JLabel();
        VerImagen = new javax.swing.JButton();
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
        jButton1.setText("Analizar Entradas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAnalizar.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btnAnalizar.setText("Generar Autómatas");
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

        lblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        VerImagen.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        VerImagen.setText("Ver Imagen");
        VerImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerImagenActionPerformed(evt);
            }
        });

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
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAnalizar)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(VerImagen))
                            .addComponent(jScrollPane1))
                        .addGap(18, 18, 18)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NRuta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(btnAnalizar)
                            .addComponent(VerImagen)))
                    .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    public static void NotErrorL(String tipo, String descripcion, int linea, int columna){
        cErrores++;
        existError=true;
        
        DotErrores+="\t<TR>\n" +
        "\t\t<TD border=\"3\" bgcolor=\"gold\">"+cErrores+"</TD>\n" +
        "\t\t<TD border=\"3\" bgcolor=\"gold\">"+tipo+"</TD>\n" +
        "\t\t<TD border=\"3\" bgcolor=\"gold\">Error en \""+descripcion.replace(">","").replace("\"","\\\"")+"\"</TD>\n" +
        "\t\t<TD border=\"3\" bgcolor=\"gold\">"+columna+"</TD>\n" +
        "\t\t<TD border=\"3\" bgcolor=\"gold\">"+linea+"</TD>\n" +
        "\t</TR>";
    }
    
    private void visualizarImagen(JLabel lbl,String ruta){
        lbl.setIcon(null);
        this.image = new ImageIcon(ruta);
        this.icon = new ImageIcon(
                this.image.getImage().getScaledInstance(
                        lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_DEFAULT)
        );
        lbl.setIcon(this.icon);
        this.repaint();
    }
    
    public static void noExisteConj(String Error){
        System.out.println("NO EXISTE EL CONJUNTO: "+Error);
    }
    
    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        txtSalida.setText("");   
        sintactico = new Sintax();
        automatas = new LinkedList();
        cErrores = 0;
        DotErrores = "<!DOCTYPE html>\n" +
"    <html lang=\"en\">\n" +
"        <head>\n" +
"            <meta charset=\"utf-8\" />\n" +
"            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\n" +
"            <meta name=\"description\" content=\"\" />\n" +
"            <meta name=\"author\" content=\"\" />\n" +
"            <title>Lista de Errores</title>\n" +
"            <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\n" +
"            <script src=\"https://use.fontawesome.com/releases/v5.15.3/js/all.js\" crossorigin=\"anonymous\"></script>\n" +
"            <link href=\"https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.5.5/css/simple-line-icons.min.css\" rel=\"stylesheet\" />\n" +
"            <link href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\" />\n" +
"            <link href=\"css/styles.css\" rel=\"stylesheet\" />\n" +
"        </head>\n" +
"\n" +
"        <body id=\"page-top\">\n" +
"            \n" +
"\n" +
"            <header class=\"masthead d-flex align-items-center\">\n" +
"                <div class=\"container px-4 px-lg-5 text-center\">\n" +
"                    <h1 class=\"mb-1\">Lista de Errores</h1>\n" +
"                </div>\n" +
"            </header>\n" +
"\n" +
"            <section class=\"content-section bg-light\" id=\"about\">\n" +
"                <div class=\"container px-4 px-lg-5 text-center\">\n" +
"                    <table class=\"table table-striped table-dark\">\n" +
"                        <thead>\n" +
"                            <tr>\n" +
"                            <th scope=\"col\">#</th>\n" +
"                            <th scope=\"col\">Tipo de error</th>\n" +
"                            <th scope=\"col\">Descripcion</th>\n" +
"                            <th scope=\"col\">Linea</th>\n" +
"                            <th scope=\"col\">Columna</th>\n" +
"                            </tr>\n" +
"                        </thead>\n" +
"                        <tbody>";
        //String ST = txtArchivo.getText();
        //Sintax s = new Sintax(new LexC(new StringReader(ST)));
        sintactico = new Sintax(new LexC(new BufferedReader( new StringReader(txtArchivo.getText()))));
        try {
            
            LinkedList ER = new LinkedList();
            sintactico.parse();
            //LinkedList Conjuntos = sintactico.Conjuntos;
            //LinkedList a = (LinkedList) Conjuntos.getFirst();
            //System.out.println(a.getLast());
            
            //LinkedList Pruebas = sintactico.EProbar;
            //System.out.println(Pruebas);
            
            String cuadro = txtSalida.getText();
            txtSalida.setText(cuadro+"Analisis realizado correctamente\n");
            txtSalida.setForeground(new Color(25, 111, 61));
            
            ER = sintactico.ExpT;
            //System.out.println("EXPRESION AAAA "+ER);
            LinkedList ERR=Metodos.ordenarExpresionRegular(ER);
            
            if(!existError){
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
                    cuadro = txtSalida.getText();
                    txtSalida.setText(cuadro+Metodos.generarImagen(""+Actual.getFirst())+"\n");
                    Metodos.dot="";

                    cuadro = txtSalida.getText();
                    txtSalida.setText(cuadro+Metodos.tablaSiguientes(ac,""+Actual.getFirst(),""+Actual.getFirst()+": "+Actual.get(1))+"\n");

                    cuadro = txtSalida.getText();
                    txtSalida.setText(cuadro+transicions.tablaEstados(Metodos.TablaSig, ac,""+Actual.getFirst(),""+Actual.getFirst()+": "+Actual.get(1))+"\n");

                    cuadro = txtSalida.getText();
                    txtSalida.setText(cuadro+transicions.Automata( ac,""+Actual.getFirst(),""+Actual.getFirst()+": "+Actual.get(1))+"\n");

                }
            }else{
                DotErrores+="<a class=\"scroll-to-top rounded\" href=\"#page-top\"><i class=\"fas fa-angle-up\"></i></a>\n" +
"                    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
"                    <script src=\"js/scripts.js\"></script>\n" +
"                </body>\n" +
"            </html>";
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
                //String nombre = dtf.format(LocalDateTime.now());
                try {
                    String ruta = "ERRORES_202004765\\ERRORES.html";

                    File file = new File(ruta);
                    // Si el archivo no existe es creado
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(DotErrores);
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Ocurrió un error al generar \"ERRORES.html\"");
                }

                
                txtSalida.setText("ERRORES LEXICOS y/o SINTACTICOS DETECTADOS, PORFAVOR REVISE EL REPORTE DE ERRORES");
                txtSalida.setForeground(Color.red);
                existError=false;
            }
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(automatas.size()!=0){
            try{
                LinkedList CONJ = sintactico.Conjuntos;
                LinkedList PRUEBA = sintactico.EProbar;
                String prueba = AnalizarCadena.analiza(CONJ, PRUEBA, automatas);
                txtSalida.setText(prueba+"\n"+AnalizarCadena.json);
                txtSalida.setForeground(new Color(25, 111, 61));
            }catch(Exception e){
                txtSalida.setText("HA OCURRIDO UN ERROR");
                txtSalida.setForeground(Color.red);
            }
        }else{
            txtSalida.setText("PORFAVOR GENERE AUTOMATAS");
            txtSalida.setForeground(Color.red);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void VerImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerImagenActionPerformed
        JFileChooser jfile = new JFileChooser();
        int resultado = jfile.showOpenDialog(this);
        if(resultado!=JFileChooser.CANCEL_OPTION){
            File fileName = jfile.getSelectedFile();
            this.visualizarImagen(lblImagen, fileName.getAbsolutePath());
        }
    }//GEN-LAST:event_VerImagenActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NRuta;
    private javax.swing.JButton VerImagen;
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
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTextArea txtArchivo;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}
