/*
 * A client program that exchanges messages with the server over a port.
 * IMPORTANT: make sure that you always start your server code before you
 * start your client code.
 */
import java.io.*;
import java.net.*;

public class GossipClient
{
  public static void main(String[] args) throws Exception
  {
     //create a socket to connect to the server. "127.0.0.1" refers to your
     //local ip address. The second input refers to the port number, in this
     // case 3002
     Socket sock = new Socket("127.0.0.1", 3002);
     
     // "BufferedReader" refers to a custom data type. It helps you take input
     // messages from the keyboard (console). When the user types a message into
     //the console, it will be stored in the keyread variable.
     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
     
     //Here we're creating an output stream (PrintWriter) variable and naming it "pwrite". 
     //You'll use the pwrite variable to send messages to the server later.
     OutputStream ostream = sock.getOutputStream(); 
     PrintWriter pwrite = new PrintWriter(ostream, true);

     // Here we're creating an inputstream, receiveRead, which we'll use to receive messages
     //from the server later.
     InputStream istream = sock.getInputStream();
     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

     //prompt the client for a message
     System.out.println("Start the chitchat, type and press Enter key");

     //create a string variable to store the message that you receive from the server.
     String receiveMessage;
     //create a string variable to store the message that the user wants to send to the server.
     String sendMessage;     
     
     //while(true) will create an infinite loop - it will run until you hit the red "stop" button
     //in the top right of your console. This allows the user to continuously send messages to the
     //server
     while(true)
     {
        //assign sendMessage to the message that the user types into the console
        sendMessage = keyRead.readLine();  
        //send the message to the server by "printing" it to the pwrite output stream
        pwrite.println(sendMessage);
        //flushing the data makes sure that your last message to the server doesn't conflict
        //with the next message that you send
        pwrite.flush();
        
        //When you receive a message from the server, this if statement will execute.
        if((receiveMessage = receiveRead.readLine()) != null) //receive from server
        {
            //display the message that you received from the server to the console
            //so that the user can see it
            System.out.println(receiveMessage); // displaying at DOS prompt
        }         
      }               
    }                    
}                        
