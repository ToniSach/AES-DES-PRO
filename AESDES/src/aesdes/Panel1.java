/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesdes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.border.Border;

/**
 *
 * @author tonis
 */
public class Panel1 extends JPanel {

    JButton DEScifrar, DESdescifrar, AEScifrar, AESdescifrar;
    JRadioButton DESoption, DESoption2;
    ButtonGroup DESoptions;
    JComboBox DESAES;
    JLabel DEStexto, DESclave, AEStexto, AESclave, titulo;
    JTextField DESClave, AESClave;
    JTextArea DEStextoE, AEStextoE;

    public Panel1() {
        componentes();
        this.setBackground(Color.decode("#EFD6AC"));
        setLayout(null);
    }

    public void componentes() {
        titulo = new JLabel("DES/AES");
        titulo.setBounds(330, 10, 140, 30);
        titulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
        titulo.setForeground(Color.decode("#ffffff"));
        add(titulo);

        DESAES = new JComboBox();
        DESAES.addItem("Seleccionar DES/AES");
        DESAES.addItem("DES");
        DESAES.addItem("AES");
        DESAES.setBounds(275, 60, 250, 25);
        DESAES.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        //DESAES.setForeground(Color.decode("#ffffff"));
        DESAES.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (DESAES.getSelectedItem().equals("DES")) {
                    DESPart00();
                    AESPart01();
                } else if (DESAES.getSelectedItem().equals("AES")) {
                    DESPart01();
                    AESPart00();

                } else if (DESAES.getSelectedItem().equals("Seleccionar DES/AES")) {
                    DESPart01();
                    AESPart01();
                } else {
                    System.out.println("Error");
                }
            }
        });
        add(DESAES);
        DESPart();
        AESPart();

    }

    public void DESPart() {
        /*DESoption = new JRadioButton();
         DESoption.setText("Ingresar texto");
         DESoption.setBounds(20, 90, 130, 15);
         DESoption.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
         DESoption.setBackground(new Color(0,0,0,0));
         DESoption.setOpaque(false);
         DESoption.setContentAreaFilled(false);
         DESoption.setBorderPainted(false);
         DESoption.setContentAreaFilled(false);
         DESoption.setBorderPainted(false);
         add(DESoption);
        
         DESoption2 = new JRadioButton();
         DESoption2.setText("Subir archivo");
         DESoption2.setBounds(165, 90, 130, 15);
         DESoption2.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
         DESoption2.setBackground(new Color(0,0,0,0));
         DESoption2.setOpaque(false);
         DESoption2.setContentAreaFilled(false);
         DESoption2.setBorderPainted(false);
         DESoption2.setContentAreaFilled(false);
         DESoption2.setBorderPainted(false);
         add(DESoption2);
        
         DESoptions = new ButtonGroup();
         DESoptions.add(DESoption);
         DESoptions.add(DESoption2);**/

        DEStexto = new JLabel("Texto:");
        //DEStexto.setSize(60, 15);
        //DEStexto.setLocation(20, 115);
        //DEStexto.setBounds(1000, 1000, 60, 15);
        DEStexto.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        DEStexto.setForeground(Color.decode("#ffffff"));
        add(DEStexto);

        DEStextoE = new JTextArea();
        //DEStextoE.setSize(340, 250);
        //DEStextoE.setLocation(20, 140);
        //DEStexto.setBounds(1000, 1000, 340, 250);
        DEStextoE.setFont(new Font("Arial", Font.ITALIC, 15));
        DEStextoE.setBackground(Color.decode("#C5CAC9"));
        //DEStextoE.setForeground(/*Color.decode("#2D3A54")*/ new Color(0,0,0,0));
        DEStextoE.setLineWrap(true);
        DEStextoE.setWrapStyleWord(true);
        DEStextoE.setEditable(true);
        add(DEStextoE);

        DESclave = new JLabel("Clave:");
        //DESclave.setBounds(440, 115, 60, 15);
        DESclave.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        DESclave.setForeground(Color.decode("#ffffff"));
        add(DESclave);

        DESClave = new JTextField();
        //DESClave.setBounds(440, 130, 130, 30);
        DESClave.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        DESClave.setBackground(Color.decode("#C5CAC9"));
        add(DESClave);
        //remove(DESClave);

        DEScifrar = new JButton("Cifrar");
        DEScifrar.setBackground(Color.decode("#161569"));
        //DEScifrar.setBounds(520, 435, 120, 25);
        DEScifrar.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        DEScifrar.setForeground(Color.decode("#D3E7F3"));
        DEScifrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textocampo = DEStextoE.getText();
                if (textocampo.length() != 0) {
                    try {
                        FileOutputStream out = new FileOutputStream("DES.txt");
                        try {
                            out.write(DEStextoE.getText().getBytes());
                        } catch (IOException ex) {
                            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String exp = "^[a-zA-Z\\s\\u00f1\\u00d1]{8}";
                    Pattern p = Pattern.compile(exp);
                    DES o = new DES();
                    String clave = "";
                    clave = DESClave.getText();
                    if (clave.length() != 8) {
                        JOptionPane.showMessageDialog(null, "Ingresa una clave válida");
                    } else {
                        if (p.matcher(clave).matches()) {
                            try {
                                o.Cifrar(clave, "SOS");
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoSuchPaddingException ex) {
                                Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InvalidKeyException ex) {
                                Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalBlockSizeException ex) {
                                Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (BadPaddingException ex) {
                                Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            JOptionPane.showMessageDialog(null, "archivo DES.txt.cifrado creado con éxito");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingresa una clave válida, sólo letras");
                            
                        }
                            
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa algún texto a cifrar");
                }

            }
        });
        add(DEScifrar);

        DESdescifrar = new JButton("Descifrar");
        DESdescifrar.setBackground(Color.decode("#161569"));
        //DESdescifrar.setBounds(660, 435, 120, 25);
        DESdescifrar.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        DESdescifrar.setForeground(Color.decode("#D3E7F3"));
        DESdescifrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String exp = "^[a-zA-Z\\s\\u00f1\\u00d1]{8}";
                Pattern p = Pattern.compile(exp);
                DES o = new DES();
                String clave = "";
                clave = DESClave.getText();
                if (clave.length() != 8) {
                    JOptionPane.showMessageDialog(null, "Ingresa una clave válida");
                } else {
                    if (p.matcher(clave).matches()) {
                        try {
                            o.descifrar(clave, "SOS");
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchPaddingException ex) {
                            Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvalidKeyException ex) {
                            Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalBlockSizeException ex) {
                            Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (BadPaddingException ex) {
                            Logger.getLogger(AESDES.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "archivo DES.txt.descifrado creado con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingresa una clave válida, sólo letras");
                    }
                }

            }
        });
        add(DESdescifrar);
    }

    public void DESPart00() {
        DEStexto.setBounds(20, 115, 60, 15);
        DEStextoE.setBounds(20, 140, 340, 250);
        DESclave.setBounds(440, 115, 60, 15);
        DESClave.setBounds(440, 130, 130, 30);
        DEScifrar.setBounds(520, 435, 120, 25);
        DESdescifrar.setBounds(660, 435, 120, 25);
    }

    public void DESPart01() {
        //remove();remove uwu

        DEStexto.setBounds(1000, 1000, 60, 15);
        DEStextoE.setBounds(1000, 1000, 340, 250);
        DESclave.setBounds(1000, 1000, 60, 15);
        DESClave.setBounds(1000, 1000, 130, 30);
        DEScifrar.setBounds(1000, 1000, 120, 25);
        DESdescifrar.setBounds(1000, 1000, 120, 25);
        /*
         remove(DEStexto);
         remove(DEStextoE);
         remove(DESclave);
         remove(DESClave);
         remove(DEScifrar);
         remove(DESdescifrar);*/
    }

    public void AESPart() {
        AEStexto = new JLabel("Texto:");
        //AEStexto.setSize(60, 15);
        //AEStexto.setLocation(20, 115);
        //AEStexto.setBounds(1000, 1000, 60, 15);
        AEStexto.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        AEStexto.setForeground(Color.decode("#ffffff"));
        add(AEStexto);

        AEStextoE = new JTextArea();
        //AEStextoE.setSize(340, 250);
        //AEStextoE.setLocation(20, 140);
        //AEStexto.setBounds(1000, 1000, 340, 250);
        AEStextoE.setFont(new Font("Arial", Font.ITALIC, 15));
        AEStextoE.setBackground(Color.decode("#C5CAC9"));
        //AEStextoE.setForeground(/*Color.decode("#2D3A54")*/ new Color(0,0,0,0));
        AEStextoE.setLineWrap(true);
        AEStextoE.setWrapStyleWord(true);
        AEStextoE.setEditable(true);
        add(AEStextoE);

        AESclave = new JLabel("Clave:");
        //AESclave.setBounds(440, 115, 60, 15);
        AESclave.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        AESclave.setForeground(Color.decode("#ffffff"));
        add(AESclave);

        AESClave = new JTextField();
        //AESClave.setBounds(440, 130, 130, 30);
        AESClave.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        AESClave.setBackground(Color.decode("#C5CAC9"));
        add(AESClave);
        //remove(AESClave);

        AEScifrar = new JButton("Cifrar");
        AEScifrar.setBackground(Color.decode("#161569"));
        //AEScifrar.setBounds(520, 435, 120, 25);
        AEScifrar.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        AEScifrar.setForeground(Color.decode("#D3E7F3"));
        AEScifrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textocampo = AEStextoE.getText();
                if (textocampo.length() != 0) {
                    try {
                        FileOutputStream out = new FileOutputStream("AES.txt");
                        try {
                            out.write(AEStextoE.getText().getBytes());
                        } catch (IOException ex) {
                            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String exp = "^[a-zA-Z\\s\\u00f1\\u00d1]{0,}";
                    Pattern p = Pattern.compile(exp);
                    AES o = new AES();
                    String clave = "";
                    clave = AESClave.getText();
                    System.out.println(clave.length());
                    System.out.println(clave);
                    System.out.println("Hola aca atras");
                    if (clave.length() == 16 || clave.length() == 24 || clave.length() == 32) {
                        System.out.println("pues si entró bro");
                        if (p.matcher(clave).matches()) {
                            String heyyy = AEStextoE.getText();
                            try {
                                o.Cifrar(clave, heyyy);
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoSuchPaddingException ex) {
                                Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InvalidKeyException ex) {
                                Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalBlockSizeException ex) {
                                Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (BadPaddingException ex) {
                                Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            JOptionPane.showMessageDialog(null, "archivo AES.txt.cifrado creado con éxito");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingresa una clave válida, sólo letras");
                            
                        }
                            
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingresa una clave válida");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa algún texto a cifrar");
                }
            }
        });
        add(AEScifrar);

        AESdescifrar = new JButton("Descifrar");
        AESdescifrar.setBackground(Color.decode("#161569"));
        //AESdescifrar.setBounds(660, 435, 120, 25);
        AESdescifrar.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        AESdescifrar.setForeground(Color.decode("#D3E7F3"));
        AESdescifrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String exp = "^[a-zA-Z\\s\\u00f1\\u00d1]{0,}";
                Pattern p = Pattern.compile(exp);
                AES o = new AES();
                String clave = "";
                clave = AESClave.getText();
                if (clave.length() == 16 || clave.length() == 24 || clave.length() == 32) {
                    if (p.matcher(clave).matches()) {
                        try {
                            o.Descifrar(clave);
                        } catch (IllegalBlockSizeException ex) {
                            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (BadPaddingException ex) {
                            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvalidKeyException ex) {
                            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchPaddingException ex) {
                            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "archivo AES.txt.descifrado creado con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingresa una clave válida, sólo letras");
                        
                    }
                        
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa una clave válida, error de longitud");
                }
            }
        });
        add(AESdescifrar);
    }

    public void AESPart00() {
        AEStexto.setBounds(20, 115, 60, 15);
        AEStextoE.setBounds(20, 140, 340, 250);
        AESclave.setBounds(440, 115, 60, 15);
        AESClave.setBounds(440, 130, 130, 30);
        AEScifrar.setBounds(520, 435, 120, 25);
        AESdescifrar.setBounds(660, 435, 120, 25);
    }

    public void AESPart01() {
        //remove();remove uwu

        AEStexto.setBounds(1000, 1000, 60, 15);
        AEStextoE.setBounds(1000, 1000, 340, 250);
        AESclave.setBounds(1000, 1000, 60, 15);
        AESClave.setBounds(1000, 1000, 130, 30);
        AEScifrar.setBounds(1000, 1000, 120, 25);
        AESdescifrar.setBounds(1000, 1000, 120, 25);
        /*
         remove(AEStexto);
         remove(AEStextoE);
         remove(AESclave);
         remove(AESClave);
         remove(AEScifrar);
         remove(AESdescifrar);*/
    }

    private Image fondo;
    private Image rndm;

    @Override
    public void paint(Graphics g) {
        fondo = new ImageIcon(getClass().getResource("/Img/fondo02.jpeg")).getImage();
        g.drawImage(fondo, 0, 0, 800, 500, this);
        setOpaque(false);
        super.paint(g);

        rndm = new ImageIcon(getClass().getResource("/Img/imgrndm.jpg")).getImage();
        g.drawImage(rndm, 440, 170, 340, 220, this);
        setOpaque(false);
        super.paint(g);

    }

}
