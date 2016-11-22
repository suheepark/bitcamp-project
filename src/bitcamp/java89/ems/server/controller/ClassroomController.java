package bitcamp.java89.ems.server.controller;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Classroom;
public class ClassroomController {
  private Scanner in;
  private PrintStream out;
  private ArrayList<Classroom> list;
  static boolean changed = false;
  private String filename = "classroom-v1.6.data";
  
  public ClassroomController(Scanner in, PrintStream out) throws IOException {
    this.in = in;
    this.out = out;
    list = new ArrayList<Classroom>();
    this.doLoad();
  }

  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void doLoad() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);
      list = (ArrayList<Classroom>)in.readObject();
    } catch (EOFException e) {
      // 파일을 모두 읽은 경우
    } catch (Exception e) {
      //out.println("강의실 데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {}
    }
  }

  public boolean service() throws IOException {
    while (true) {
      out.println("강의실관리> ");
      out.println();
      String[] command = in.nextLine().split("\\?");
      try {
        switch (command[0]) {
          case "add" : this.doAdd(command[1]); break;
          case "list" : this.doList(); break;
          case "view" : this.doView(command[1]); break;
          case "delete" : this.doDelete(command[1]); break;
          case "update" : this.doUpdate(command[1]); break;
          case "main" : return true;
          case "quit" : return false;
          default :
            out.println("지원하지 않는 명령어입니다.");
        }
      } catch (Exception e) {
        out.println("실행 중 오류가 발생했습니다.");
      }
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);
    out.writeObject(list);
    changed = false;
    out.close();
    out0.close();
  }
  
  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    Classroom classroom = new Classroom(); 
    classroom.setRoomNo(Integer.parseInt(paramMap.get("roomno")));
    classroom.setCapacity(Integer.parseInt(paramMap.get("capacity")));
    classroom.setClassName(paramMap.get("classname"));
    classroom.setClassTime(paramMap.get("classtime"));
    classroom.setProjector(paramMap.get("projector").equals("t") ? true : false );
    classroom.setLocker(paramMap.get("locker").equals("t") ? true : false );

    list.add(classroom);
    changed = true;
    out.println("저장하였습니다.");
  }

  private void doList() {
    for (Classroom classroom : list) {
      out.printf("%d %d %s %s %s %s\n",
      classroom.getRoomNo(), classroom.getCapacity(),
      classroom.getClassName(), classroom.getClassTime(),
      ((classroom.isProjector()) ? "Yes" : "No"),
      ((classroom.isLocker()) ? "Yes" : "No"));
    }
  }

  private void doView(String params) {
    String[] kv = params.split("=");
    for (Classroom classroom : list) {
      if (classroom.getRoomNo() == Integer.parseInt(kv[1])) {
        out.printf("강의실 번호 : %d\n", classroom.getRoomNo());
        out.printf("수용인원 : %d\n", classroom.getCapacity());
        out.printf("강의명 : %s\n", classroom.getClassName());
        out.printf("강의 시간 : %s\n", classroom.getClassTime());
        out.printf("프로젝터 유무 : %s\n", (classroom.isProjector()) ? "YES" : "NO");
        out.printf("사물함 유무 : %s\n", (classroom.isLocker()) ? "YES" : "NO");
        out.println("-----------------------");
      }
    }
    
  }

  private void doDelete(String params) {
    String[] kv = params.split("=");
    for (Classroom classroom : list) {
      if (classroom.getRoomNo() == Integer.parseInt(kv[1])) {
        list.remove(classroom);
        changed = true;
        out.println("삭제하였습니다.");
        return;
      }
    }
  }

  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    for (Classroom classroom : list) {
      if (classroom.getRoomNo() == Integer.parseInt(paramMap.get("roomno"))) {
        classroom.setCapacity(Integer.parseInt(paramMap.get("capacity")));
        classroom.setClassName(paramMap.get("classname"));
        classroom.setClassTime(paramMap.get("classtime"));
        classroom.setProjector(paramMap.get("projector").equals("t") ? true : false);
        classroom.setLocker(paramMap.get("locker").equals("t") ? true : false);
        changed = true;
        out.println("변경하였습니다.");
        return;
      }
    }
    out.println("해당 데이터를 찾지 못했습니다.");
  }

}
