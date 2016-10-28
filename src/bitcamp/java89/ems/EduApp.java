package bitcamp.java89.ems;
import java.util.Scanner;
public class EduApp {
  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    ClassroomController classroomController = new ClassroomController(keyScan);
    System.out.println("비트캠프 관리 시스템에 오신 걸 환영합니다!");

    loop:
      while (true) {
        System.out.print("명령> ");
        String command = keyScan.nextLine().toLowerCase(); // 소문자로 값을 받는다.
        switch (command) {
          case "add" : classroomController.doAdd(); break;
          case "list" : classroomController.doList(); break;
          case "view" : classroomController.doView(); break;
          case "quit" :
            System.out.println("Good Bye!");
            break loop;
          default :
            System.out.println("지원하지 않는 명령어입니다.");
        }
      }
  }
}
