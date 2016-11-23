package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class RequestThread extends Thread {
  private Socket socket;
  private Scanner in;
  private PrintStream out;
  private HashMap<String,Command> commandMap;
  
  public RequestThread(Socket socket, HashMap<String,Command> commandMap) {
    this.socket = socket;
    this.commandMap = commandMap;
  }
  
  @Override
  public void run() {
    try {
      in = new Scanner(new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
      
      out.println("비트캠프 관리시스템에 오신걸 환영합니다.");
      
      while (true) {
        out.println("명령> ");
        out.println();
        
        String[] command = in.nextLine().split("\\?");

        HashMap<String,String> paramMap = new HashMap<>();
        if (command.length == 2) {
          String[] params = command[1].split("&");
          for (String value : params) {
            String[] kv = value.split("=");
            paramMap.put(kv[0], kv[1]);
          }
        }
        
        Command commandHandler = commandMap.get(command[0]);
        
        if (commandHandler == null) {
          if (command[0].equals("quit")) {
            doQuit();
            break;
          }
          out.println("지원하지 않는 명령어입니다.");
          continue;
        }
        commandHandler.service(paramMap, out);
      }
    } catch (Exception e) {
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }
  }
  
  private boolean doQuit() {
    doSave();
    System.out.println("클라이언트 연결 종료");
    return true;
  }
  private void doSave() {
    try {
      //classroomController.save();
      out.println("강의실 정보를 저장하였습니다.");
    } catch (Exception e) {
      System.out.println("강의실 정보 저장 중 오류가 발생하였습니다.");
    }
    try {
      //contactController.save();
      out.println("연락처 정보를 저장하였습니다.");
    } catch (Exception e) {
      System.out.println("연락처 정보 저장 중 오류가 발생하였습니다.");
    }
  }
  private void doMenu() {
    out.println("[메뉴]");
    out.println("1.강의실관리");
    out.println("5.연락처관리");
    out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
    out.println("save --- 데이터 저장");
    out.println("quit --- 프로그램 종료");
  }
}
