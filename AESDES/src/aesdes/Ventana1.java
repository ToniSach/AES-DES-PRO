/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesdes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author tonis
 */
public class Ventana1 extends JFrame {
    
    public Ventana1(){
        proceso();
        
        Panel1 obj = new Panel1();
        this.add(obj);
        this.pack();
    }
    
    private void proceso(){
        Image icon = new ImageIcon(getClass().getResource("/Img/imgzt.jpg")).getImage();
        this.setIconImage(icon);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (pantalla.height)/2;
        int width = (pantalla.width)/2;
        setSize(width,height);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AES/DES");
        setMinimumSize(new Dimension(800, 500));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }
    
}
