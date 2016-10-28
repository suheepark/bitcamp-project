package bitcamp.java89.ems;
import java.util.Scanner;
public class ClassroomController {
  ClassRoom[] classrooms = new ClassRoom[100];
  int length = 0;
  Scanner keyScan;

  public ClassroomController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void doAdd() {
    while (this.length < classrooms.length) {
      ClassRoom classroom = new ClassRoom();
      System.out.print("강의실 번호(예: 302)? ");
      classroom.roomNo = Integer.parseInt(this.keyScan.nextLine());
      System.out.print("수용가능인원(예: 30)? ");
      classroom.capacity = Integer.parseInt(this.keyScan.nextLine());
      System.out.print("강의명(예: 자바개발자)? ");
      classroom.className = this.keyScan.nextLine();
      System.out.print("강의 시간(예: 09:00~17:00)? ");
      classroom.classTime = this.keyScan.nextLine();
      System.out.print("프로젝터 유무(y/n)? ");
      classroom.projector = (this.keyScan.nextLine().equals("y")) ? true : false;
      System.out.print("사물함 유무(y/n)? ");
      classroom.locker = (this.keyScan.nextLine().equals("y")) ? true : false;
      this.classrooms[length++] = classroom;

      System.out.print("계속 입력하시겠습니다? (y/n)");
      if (!keyScan.nextLine().equals("y"))
        break;
    }
  }

  public void doList() {
    for (int i = 0; i < this.length; i++) {
      ClassRoom classroom = this.classrooms[i];
      System.out.printf("%d %d %s %s %s %s\n",
      classroom.roomNo, classroom.capacity, classroom.className, classroom.classTime,
      ((classroom.projector) ? "Yes" : "No"), ((classroom.locker) ? "Yes" : "No"));
    }
  }

  public void doView() {
    System.out.print("강의실 번호를 입력하세요 : ");
    String num = this.keyScan.nextLine();
    for (int  i = 0; i < this.length; i++) {
      if (num.equals(Integer.toString(this.classrooms[i].roomNo))) {
        System.out.printf("강의실 번호 : %d\n", this.classrooms[i].roomNo);
        System.out.printf("수용인원 : %d\n", this.classrooms[i].capacity);
        System.out.printf("강의명 : %s\n", this.classrooms[i].className);
        System.out.printf("강의 시간 : %s\n", this.classrooms[i].classTime);
        System.out.printf("프로젝터 유무 : %s\n", (this.classrooms[i].projector) ? "YES" : "NO");
        System.out.printf("사물함 유무 : %s\n", (this.classrooms[i].locker) ? "YES" : "NO");
        break;
      }
    }
  }
}
