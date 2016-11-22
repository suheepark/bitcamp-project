package bitcamp.java89.ems.server;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import bitcamp.java89.ems.server.controller.ClassroomController;
import bitcamp.java89.ems.server.controller.ContactController;

public class EduAppServer {
  static Scanner in;
  static PrintStream out;
  static ClassroomController classroomController;
  static ContactController contactController;

  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중..");
    while (true) {
      processRequest(ss.accept());
    }
  }
  private static void processRequest(Socket socket) {
    System.out.println("클라이언트 연결 시작");
    try {
      in = new Scanner(new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
      classroomController = new ClassroomController(in, out);
      contactController = new ContactController(in, out);
      
      out.println("비트캠프 관리시스템에 오신걸 환영합니다.");
      
      loop:
      while (true) {
        out.println("명령> ");
        out.println();
        String command = in.nextLine().toLowerCase();
        boolean running = true;
        switch (command) {
        case "menu": doMenu(); break;
        case "go 1": running = classroomController.service(); break;
        case "go 5": running = contactController.service(); break;
        case "save":
          try {
            doSave();
          } catch (Exception e) {
            out.println("파일 저장 중 오류가 발생하였습니다.");
          }
          break;
        case "quit":
          if (doQuit()) break loop;
          break;
        default:
          out.println("지원하지 않는 명령어입니다.");
        }
        if (!running) {
          doQuit();
          break;
        }
      }
    } catch (Exception e) {
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }
  }
  static boolean doQuit() {
    boolean changed = classroomController.isChanged();
    if (changed) {
      doSave();
    }
    System.out.println("클라이언트 연결 종료");
    return true;
  }
  static void doSave() {
    try {
      classroomController.save();
      out.println("강의실 정보를 저장하였습니다.");
    } catch (Exception e) {
      System.out.println("강의실 정보 저장 중 오류가 발생하였습니다.");
    }
  }
  static void doMenu() {
    out.println("[메뉴]");
    out.println("1.강의실관리");
    out.println("5.연락처관리");
    out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
    out.println("save --- 데이터 저장");
    out.println("quit --- 프로그램 종료");
  }
}
