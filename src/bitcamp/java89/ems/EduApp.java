package bitcamp.java89.ems;
import java.util.Scanner;
public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리 시스템에 오신 걸 환영합니다!");
    ClassRoom[] classrooms = new ClassRoom[100];
    int length = 0;

    Scanner keyScan = new Scanner(System.in);
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
  printClassRoom(classrooms, length);
  }
  static void printClassRoom(ClassRoom[] classrooms, int length) {
    for (int i = 0; i < length; i++) {
      ClassRoom classroom = classrooms[i];
      System.out.printf("%d %d %s %s %s %s\n",
      classroom.roomNo, classroom.capacity, classroom.className, classroom.classTime,
      ((classroom.projector=true) ? "Yes" : "No"), ((classroom.locker=true) ? "Yes" : "No"));
    }
  }
}
