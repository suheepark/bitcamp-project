package bitcamp.java89.ems.typeTest;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("서버주소?");
    String severAddr = keyScan.nextLine();
    
    Socket socket = new Socket(severAddr, 8888);
    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream(), true, "UTF-8");
    
    String message = null;
    System.out.print("이름?");
    message = keyScan.nextLine();
    out.println(message);
    System.out.println(in.nextLine());
    do {
     System.out.print(in.nextLine());
     message = keyScan.nextLine();
     out.println(message);
   } while (!message.toLowerCase().equals("quit"));
    out.close();
    in.close();
    socket.close();
    keyScan.close();
  }
}
