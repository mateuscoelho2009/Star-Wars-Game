/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Leonardo Araujo
 */
public class Cliente {
    private String host;
   private int porta;
   private Socket cliente;
   private Recebedor r;
   private String control;
   
   public Cliente (String host, int porta) {
     this.host = host;
     this.porta = porta;
   }
   
   public void executa() throws UnknownHostException, IOException {
     cliente = new Socket(this.host, this.porta);
     System.out.println("O cliente se conectou ao servidor!");
     if(cliente.getLocalAddress().getHostAddress().equals("192.168.0.23")){
         control = "pacman";
     }
     else if(cliente.getLocalAddress().getHostAddress().equals("192.168.0.25")){
         control = "ghost";
     }
 
     // thread para receber mensagens do servidor
     r = new Recebedor(cliente.getInputStream());
     new Thread(r).start();
     
     // lê msgs do teclado e manda pro servidor
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(cliente.getOutputStream());
//     while (teclado.hasNextLine()) {
//       saida.println(teclado.nextLine());
//     }
//     
//     saida.close();
//     teclado.close();
//     cliente.close();    
   }
   
   public void Send(String msg) throws UnknownHostException, IOException{
       PrintStream saida = new PrintStream(cliente.getOutputStream());
       saida.println(msg + cliente.getLocalAddress().getHostAddress());
   }
   
   public char getPacManCommand(){
       return r.getPacmanCommand();
   }
   public char getGhostCommand(){
       return r.getGhostCommand();
   }
   public String getControl(){
       return control;
   }
 }
