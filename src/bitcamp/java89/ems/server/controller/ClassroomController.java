package bitcamp.java89.ems.server.controller;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;
public class ClassroomController {
  private Scanner in;
  private PrintStream out;
  private ClassroomDao classroomDao;
  
  public ClassroomController(Scanner in, PrintStream out) throws IOException {
    this.in = in;
    this.out = out;
    classroomDao = ClassroomDao.getInstance();
  }

  public boolean isChanged() {
    return classroomDao.isChanged();
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
    if (classroomDao.isChanged()) {
      classroomDao.save();
    }
  }
  
  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    if (classroomDao.existRoomNo(Integer.parseInt(paramMap.get("roomno")))) {
      out.println("동일한 강의실 번호가 존재합니다. 등록을 취소합니다.");
      return;
    }
    Classroom classroom = new Classroom(); 
    classroom.setRoomNo(Integer.parseInt(paramMap.get("roomno")));
    classroom.setCapacity(Integer.parseInt(paramMap.get("capacity")));
    classroom.setClassName(paramMap.get("classname"));
    classroom.setClassTime(paramMap.get("classtime"));
    classroom.setProjector(paramMap.get("projector").equals("t") ? true : false );
    classroom.setLocker(paramMap.get("locker").equals("t") ? true : false );
    classroomDao.insert(classroom);
    out.println("등록하였습니다.");
  }

  private void doList() {
    ArrayList<Classroom> list = classroomDao.getList();
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
    ArrayList<Classroom> list = classroomDao.getListByRoomNo(Integer.parseInt(kv[1]));
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
    if (!classroomDao.existRoomNo(Integer.parseInt(kv[1]))) {
      out.println("해당 데이터가 없습니다.");
    }
    classroomDao.delete(Integer.parseInt(kv[1]));
    out.println("삭제하였습니다");
  }

  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    if (!classroomDao.existRoomNo(Integer.parseInt(paramMap.get("roomno")))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    Classroom classroom = new Classroom();
    classroom.setRoomNo(Integer.parseInt(paramMap.get("roomno")));
    classroom.setCapacity(Integer.parseInt(paramMap.get("capacity")));
    classroom.setClassName(paramMap.get("classname"));
    classroom.setClassTime(paramMap.get("classtime"));
    classroom.setProjector(paramMap.get("projector").equals("t") ? true : false);
    classroom.setLocker(paramMap.get("locker").equals("t") ? true : false);
    classroomDao.update(classroom);
    out.println("변경하였습니다.");
  }

}
