/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesdes;

/**
 *
 * @author tonis
 */
//librerias wiiiii que hagan todo por mi *w*

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
//para poder hacer el cifrado

import javax.crypto.spec.SecretKeySpec;
//para poder realizar las subllaves del algoritmo

import java.util.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import sun.misc.BASE64Encoder;
public class AES {
    
    //ahora el objeto de cifrado
    Cipher cifrado;
    
    public void AES(){
        
    }
    
    public void Cifrar(String contra, String texto) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException{
        String llavesimetrica = /*entrada.nextLine()*/contra;
        
        //ahora vamos a crear las subllaves a traves del uso de la clase
        //SecretKeySpec
        
        SecretKeySpec key = new SecretKeySpec(llavesimetrica.getBytes(), "AES");
        
        //se debe de tener el tipo de instancia del cifrado que en este caso es AES
        cifrado = Cipher.getInstance("AES");
            
        //comenzar por el metodo de cifrado
        cifrado.init(Cipher.ENCRYPT_MODE, key);
            
            
        System.out.println("Ingresa el mensaje a cifrar: ");
            
        String mensaje = /*entrada.nextLine()*/texto;
            
        //ahora necesitamos almacenar el mensaje en un bloque de bytes
            
        byte[] campoCifrado = cifrado.doFinal(mensaje.getBytes());
            
        //vamos a visualizar el mensaje cifrado
            
        System.out.println("Mensaje cifrado: " + campoCifrado);
            
            
        //vamos a transformarlo en una cadena
            
        System.out.println("Mensaje en cadena: " + new String(campoCifrado));
            
        //para poder entender el mensaje cifrado en caracteres es necesario codificarlo
            
        String base64 = new BASE64Encoder().encode(campoCifrado);
            
        System.out.println("Mensaje cifrado entendible: " + base64);
            
        byte[] buffer = new byte[1000];
        
        byte[] bufferCifrado;


        //vamos a generar el archivo

        FileInputStream in = new FileInputStream("AES.txt");
        FileOutputStream out = new FileOutputStream("AES.txt"+".cifrado");

        //tenemos que empezar por la lectura del archivo y converlo en bytes

        int bytesleidos = in.read(buffer, 0, 1000);
        while (bytesleidos != -1) {
            bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
            out.write(bufferCifrado);
            bytesleidos = in.read(buffer, 0, bytesleidos);
        }

        //cuando termine de leer el archivo
        bufferCifrado = cifrado.doFinal();
        //escribir el archivo de salida
        out.write(bufferCifrado);

        in.close();
        out.close();
    }
    
    public void Descifrar(String contra)throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchPaddingException{
        String llavesimetrica = /*entrada.nextLine()*/ contra;

        //ahora vamos a crear las subllaves a traves del uso de la clase
        //SecretKeySpec
        SecretKeySpec key = new SecretKeySpec(llavesimetrica.getBytes(), "AES");

        //String mensaje = /*entrada.nextLine()*/texto;
        /*byte[] campoCifrado = cifrado.doFinal(mensaje.getBytes());
        //ahora vamos a decifrar
         */
        cifrado = Cipher.getInstance("AES");
        cifrado.init(Cipher.DECRYPT_MODE, key);
        /*
         //necesito un arreglo que se encargue de almacenar el mensaje cifrado
         //a descifrar
            
         byte[] mensajeDescifrado = cifrado.doFinal(campoCifrado);
            
         System.out.println("Mensaje descifrado: " + mensajeDescifrado);
            
         //dandole formato de cadena
            
         String mensaje_claro = new String(mensajeDescifrado);
            
         System.out.println("Mensaje descifrado en cadena: " + mensaje_claro);*/

        byte[] buffer = new byte[1000];

        byte[] bufferPlano;

            //vamos a generar el archivo
        FileInputStream in = new FileInputStream(/*args[0]*/"AES.txt" + ".cifrado");
        FileOutputStream out = new FileOutputStream(/*args[0]*/"AES.txt" + ".descifrado");

            //tenemos que empezar por la lectura del archivo y converlo en bytes
        int bytesleidos = in.read(buffer, 0, 1000);
        while (bytesleidos != -1) {
            bufferPlano = cifrado.update(buffer, 0, bytesleidos);
            out.write(bufferPlano);
            bytesleidos = in.read(buffer, 0, bytesleidos);
        }

        //cuando termine de leer el archivo
        bufferPlano = cifrado.doFinal();
        //escribir el archivo de salida
        out.write(bufferPlano);

        in.close();
        out.close();
    }
    
}
