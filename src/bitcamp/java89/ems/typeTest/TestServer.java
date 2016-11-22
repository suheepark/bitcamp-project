package bitcamp.java89.ems.typeTest;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import bitcamp.java89.ems.typeTest.StageDB.Stage;

public class TestServer {

  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(8888);
    System.out.println("TestServer started..");
    try {
      Socket socket = serverSocket.accept();
      System.out.println("Client is connected..");
      Scanner socketIn = new Scanner(socket.getInputStream());
      PrintStream socketOut = new PrintStream(socket.getOutputStream());
       StageDB stageDB = new StageDB();
       stageDB.getQue();
       Stage st = stageDB.stage1;
      String userName = socketIn.nextLine();
      socketOut.println(userName +"님 환영합니다!");
      
      while (true) {
        socketOut.println(st.question);
 
        String answer = socketIn.nextLine();
       
        if(st.yes == null || st.no == null)
        {
          socketOut.println("입력값이 올바르지 않습니다.");
          socketOut.println("y/n 로 입력해 주세요.");
          break;
        }
      
        if (answer.toLowerCase().equals("y")) {
          st = st.yes;
        } else {
          st = st.no;
        }
        
      }
      
      //socketOut.printf("당신의 매력은 %s입니다.\n", stage.readUTF());
      
      socketOut.close();
      socketIn.close();
      socket.close();
    } catch (Exception e) {}
  } //main
} //class
