/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman2;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Leonardo Araujo
 */
public class Recebedor implements Runnable {
 
   private InputStream servidor;
   private char pacman_command;
   private char ghost_command;
   public Recebedor(InputStream servidor) {
     this.servidor = servidor;
   }
 
   public void run() {
     // recebe msgs do servidor e imprime na tela
     Scanner s = new Scanner(this.servidor);
     String str;
     char aux;
     while (s.hasNextLine()) {
         str = s.nextLine();
       aux = str.toCharArray()[0];
       if((aux + "192.168.0.23" ).equals(str)){
           pacman_command = aux;
       }
       else if((aux + "192.168.0.25" ).equals(str)){
           ghost_command = aux;
       }
     }
   }
   
   public char getPacmanCommand(){
       return pacman_command;
   }
   public char getGhostCommand(){
       return ghost_command;
   }
 }
