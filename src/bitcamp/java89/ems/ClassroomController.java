package bitcamp.java89.ems;
import java.util.Scanner;
import java.util.ArrayList;
public class ClassroomController {
  private Scanner keyScan;
  private ArrayList<Classroom> list;

  public ClassroomController(Scanner keyScan) {
    this.keyScan = keyScan;
    list = new ArrayList<Classroom>();
  }

  public void service() {
    loop:
      while (true) {
        System.out.print("강의실 관리> ");
        String command = keyScan.nextLine().toLowerCase();
        try {
          switch (command) {
            case "add" : this.doAdd(); break;
            case "list" : this.doList(); break;
            case "view" : this.doView(); break;
            case "delete" : this.doDelete(); break;
            case "update" : this.doUpdate(); break;
            case "main" : break loop;
            default :
              System.out.println("지원하지 않는 명령어입니다.");
          }
        } catch (IndexOutOfBoundsException e) {
          System.out.println("인덱스가 유효하지 않습니다.");
        } catch (Exception e) {
          System.out.println("인덱스 값이 잘못되었거나, 실행 중 오류가 발생했습니다.");
        }
      }
  }

  private void doAdd() {
    while (true) {
      Classroom classroom = new Classroom();
      while (true) {
        try { 
          System.out.print("강의실 번호(예: 302)? ");
          classroom.roomNo = Integer.parseInt(this.keyScan.nextLine());
          System.out.print("수용가능인원(예: 30)? ");
          classroom.capacity = Integer.parseInt(this.keyScan.nextLine());
          break;
        } catch (Exception e) {
          System.out.println("입력값이 잘못되었습니다.(숫자만 입력)");
        }
      }
      System.out.print("강의명(예: 자바개발자)? ");
      classroom.className = this.keyScan.nextLine();
      System.out.print("강의 시간(예: 09:00~17:00)? ");
      classroom.classTime = this.keyScan.nextLine();
      System.out.print("프로젝터 유무(y/n)? ");
      classroom.projector = (this.keyScan.nextLine().equals("y")) ? true : false;
      System.out.print("사물함 유무(y/n)? ");
      classroom.locker = (this.keyScan.nextLine().equals("y")) ? true : false;
      list.add(classroom);
    
      System.out.print("계속 입력하시겠습니다? (y/n)");
      if (!keyScan.nextLine().equals("y"))
        break;
    } //while
  }

  private void doList() {
    for (Classroom classroom : list) {
      System.out.printf("%d %d %s %s %s %s\n",
      classroom.roomNo, classroom.capacity,
      classroom.className, classroom.classTime,
      ((classroom.projector) ? "Yes" : "No"),
      ((classroom.locker) ? "Yes" : "No"));
    }
  }

  private void doView() {
    System.out.print("강의실 인덱스를 입력하세요 : ");
    int index = Integer.parseInt(keyScan.nextLine());
    Classroom classroom = list.get(index);
    System.out.printf("강의실 번호 : %d\n", classroom.roomNo);
    System.out.printf("수용인원 : %d\n", classroom.capacity);
    System.out.printf("강의명 : %s\n", classroom.className);
    System.out.printf("강의 시간 : %s\n", classroom.classTime);
    System.out.printf("프로젝터 유무 : %s\n", (classroom.projector) ? "YES" : "NO");
    System.out.printf("사물함 유무 : %s\n", (classroom.locker) ? "YES" : "NO");
  }

  private void doDelete() {
    System.out.print("삭제할 강의실 인덱스를 입력하세요 : ");
    int index = Integer.parseInt(keyScan.nextLine());
    Classroom deleteclassroom = list.remove(index);
    System.out.printf("%d 강의실 정보를 삭제하였습니다.\n", deleteclassroom.roomNo);
  }

  private void doUpdate() {
    System.out.print("변경할 강의실 인덱스를 입력하세요 : ");
    int index = Integer.parseInt(keyScan.nextLine());
    Classroom oldclassroom = list.get(index);
    Classroom classroom = new Classroom();
    System.out.print("강의실 번호(예: 302)? ");
    classroom.roomNo = Integer.parseInt(this.keyScan.nextLine());
    System.out.print("수용가능인원(예: 30)? ");
    classroom.capacity = Integer.parseInt(this.keyScan.nextLine());
    System.out.print("강의명(예: 자바개발자과정)? ");
    classroom.className = this.keyScan.nextLine();
    System.out.print("강의 시간(예: 09:00~17:00)? ");
    classroom.classTime = this.keyScan.nextLine();
    System.out.print("프로젝터 유무(y/n)? ");
    classroom.projector = (this.keyScan.nextLine().equals("y")) ? true : false;
    System.out.print("사물함 유무(y/n)? ");
    classroom.locker = (this.keyScan.nextLine().equals("y")) ? true : false;

    System.out.print("저장하시겠습니까 (y/n)? ");
    if (this.keyScan.nextLine().equals("y")) {
      list.set(index, classroom);
      System.out.println("저장하였습니다.");
    } else {
      System.out.println("변경을 취소하였습니다.");
    }
  }

}
