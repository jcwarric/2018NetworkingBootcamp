/*
 * A server program that exchanges messages with the server over a port.
 * IMPORTANT: make sure that you always start your server code before you
 * start your client code.
 */
import java.io.*;
import java.net.*;
public class GossipServer
{
  public static void main(String[] args) throws Exception
  {
      //declare a server socket to listen to for a client's message
      //its important that the server and client use the same
      //port numbers. If the server is listening on a port that
      //is different from the port that the client is sending data
      //to then they wont be able to communicate
      ServerSocket serverSocket = new ServerSocket(3002);
      
      System.out.println("Server  ready for chatting");
      
      //accepting the socket allows the server to "listen" to the port. ie, it will
      //keep checking for new messages from the client. 
      Socket sock = serverSocket.accept( );  
      
      // BufferedReader helps you read input from the server's console.When the server's
      //user enters text into the console, it will be stored in the keyRead variable.
      BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
      
      //Here we're creating an output stream (PrintWriter) variable and naming it "pwrite". 
      //You'll use the pwrite variable to send messages to the client later.
      OutputStream ostream = sock.getOutputStream(); 
      PrintWriter pwrite = new PrintWriter(ostream, true);

      // Here we're creating an inputstream, receiveRead, which we'll use to receive messages
      //from the client later.
      InputStream istream = sock.getInputStream();
      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
    
      //create a string variable to store the message that you receive from the client.
      String receiveMessage;
      //create a string variable to store the message that the server's user wants to send
      //to the client.
      String sendMessage; 
      
      //while(true) will create an infinite loop - it will run until you hit the red "stop" button
      //in the top right of your console. This allows the user to continuously send and receive
      //messages from the client.
      while(true)
      {
        //this if statement displays the message that you received from the client
        if((receiveMessage = receiveRead.readLine()) != null){
           System.out.println(receiveMessage);         
        }  
        
        //assign sendMessage to the message that the server's user wants to send to the client.
        sendMessage = keyRead.readLine();
        
        //send the message to the client by "printing" it to the pwrite variable (output
        //stream).
        pwrite.println(sendMessage);    
        //flushing the data makes sure that your last message to the client doesn't conflict
        //with the next message that you send
        pwrite.flush();
      }               
    }                    
}                        
