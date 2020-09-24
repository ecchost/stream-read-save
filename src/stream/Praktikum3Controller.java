/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Ecchost
 */
public class Praktikum3Controller {
    private Praktikum3 view;
    
    public Praktikum3Controller (Praktikum3 view){
        this.view=view;
        this.view.getBtnRead().addActionListener((e) -> {
            try {
                read();
            } catch (BadLocationException ex) {
                Logger.getLogger(Praktikum3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.view.getBtnSave().addActionListener((e) -> {
            save();
        });
    }
    
     private void save() {
         JFileChooser loadFile = view.getLoadFile();
         if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
             BufferedWriter writer = null;
             try {
                 String contents = view.getTxtArea().getText();
                 if (contents != null && !contents.isEmpty()) {
                     writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                     writer.write(contents);
                 }

             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Praktikum3Controller.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(Praktikum3Controller.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 if (writer != null) {
                     try {
                         writer.flush();
                         writer.close();
                         view.getTxtArea().setText("");
                         JOptionPane.showMessageDialog(view, "The file have been saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                     } catch (IOException ex) {
                         Logger.getLogger(Praktikum3Controller.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
      private void read() throws BadLocationException {
         JFileChooser loadFile = view.getLoadFile();
         Document doc = view.getTxtArea().getDocument();
         if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
             BufferedReader reader = null;
             try {
                 reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
                 String data = null;
                 doc.insertString(0, "", null);
                 while ((data = reader.readLine()) != null) {
                     doc.insertString(doc.getLength(), data+"\n", null);
                 }
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Praktikum3Controller.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException | BadLocationException ex) {
                 Logger.getLogger(Praktikum3Controller.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 if (reader != null) {
                     try {
                         reader.close();
                     } catch (IOException ex) {
                         Logger.getLogger(Praktikum3Controller.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
}
