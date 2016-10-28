package bitcamp.java89.ems;
import java.util.Scanner;
public class EduApp {
  static ClassRoom[] classrooms = new ClassRoom[100];
  static int length = 0;
  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("비트캠프 관리 시스템에 오신 걸 환영합니다!");
    loop:
      while (true) {
        System.out.print("명령> ");
        String command = keyScan.nextLine().toLowerCase(); // 소문자로 값을 받는다.
        switch (command) {
          case "add" : doAdd(); break;
          case "list" : doList(); break;
          case "view" : doView(); break;
          case "quit" :
            System.out.println("Good Bye!");
            break loop;
          default :
            System.out.println("지원하지 않는 명령어입니다.");
        }
      }
    }

  static void doAdd() {
    while (length < classrooms.length) {
      ClassRoom classroom = new ClassRoom();
      System.out.print("강의실 번호(예: 302)? ");
      classroom.roomNo = Integer.parseInt(keyScan.nextLine());
      System.out.print("수용가능인원(예: 30)? ");
      classroom.capacity = Integer.parseInt(keyScan.nextLine());
      System.out.print("강의명(예: 자바개발자)? ");
      classroom.className = keyScan.nextLine();
      System.out.print("강의 시간(예: 09:00~17:00)? ");
      classroom.classTime = keyScan.nextLine();
      System.out.print("프로젝터 유무(y/n)? ");
      classroom.projector = (keyScan.nextLine().equals("y")) ? true : false;
      System.out.print("사물함 유무(y/n)? ");
      classroom.locker = (keyScan.nextLine().equals("y")) ? true : false;
      classrooms[length++] = classroom;

      System.out.print("계속 입력하시겠습니다? (y/n)");
      if (!keyScan.nextLine().equals("y"))
        break;
    }
  }

  static void doList() {
    for (int i = 0; i < length; i++) {
      ClassRoom classroom = classrooms[i];
      System.out.printf("%d %d %s %s %s %s\n",
      classroom.roomNo, classroom.capacity, classroom.className, classroom.classTime,
      ((classroom.projector) ? "Yes" : "No"), ((classroom.locker) ? "Yes" : "No"));
    }
  }

  static void doView() {
    System.out.print("강의실 번호를 입력하세요 : ");
    String num = keyScan.nextLine();
    for (int  i = 0; i < length; i++) {
      if (num.equals(Integer.toString(classrooms[i].roomNo))) {
        System.out.printf("강의실 번호 : %d\n", classrooms[i].roomNo);
        System.out.printf("수용인원 : %d\n", classrooms[i].capacity);
        System.out.printf("강의명 : %s\n", classrooms[i].className);
        System.out.printf("강의 시간 : %s\n", classrooms[i].classTime);
        System.out.printf("프로젝터 유무 : %s\n", (classrooms[i].projector) ? "YES" : "NO");
        System.out.printf("사물함 유무 : %s\n", (classrooms[i].locker) ? "YES" : "NO");
        break;
      }
    }
  }
}
