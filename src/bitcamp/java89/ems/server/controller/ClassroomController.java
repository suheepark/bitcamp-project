package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;

@Component
public class ClassroomController {
  ClassroomDao classroomDao;
  
  public void setClassroomDao(ClassroomDao classroomDao) {
    this.classroomDao = classroomDao;
  }
  @RequestMapping(value = "classroom/add")
  public void add(HashMap<String,String> paramMap, PrintStream out) throws Exception {
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
  
  @RequestMapping(value = "classroom/delete")
  public void delete(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    classroomDao.delete(Integer.parseInt(paramMap.get("roomno")));
    out.println("삭제하였습니다");
  }
  
  @RequestMapping(value = "classroom/list")
  public void list(PrintStream out) throws Exception {
    ArrayList<Classroom> list = classroomDao.getList();
    for (Classroom classroom : list) {
      out.printf("%d %d %s %s %s %s\n",
      classroom.getRoomNo(), classroom.getCapacity(),
      classroom.getClassName(), classroom.getClassTime(),
      ((classroom.isProjector()) ? "Yes" : "No"),
      ((classroom.isLocker()) ? "Yes" : "No"));
    }
  }
  
  @RequestMapping(value = "classroom/update")
  public void update(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    Classroom classroom = new Classroom();
    classroom.setRoomNo(Integer.parseInt(paramMap.get("roomno")));
    classroom.setCapacity(Integer.parseInt(paramMap.get("capacity")));
    classroom.setClassName(paramMap.get("classname"));
    classroom.setClassTime(paramMap.get("classtime"));
    classroom.setProjector(paramMap.get("projector").equals("t") ? true : false);
    classroom.setLocker(paramMap.get("locker").equals("t") ? true : false);
    classroomDao.update(classroom);
    out.println("변경하였습니다.");
    return;
  }
  
  @RequestMapping(value = "classroom/view")
  public void view(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ArrayList<Classroom> list = classroomDao.getListByRoomNo(Integer.parseInt(paramMap.get("roomno")));
    for (Classroom classroom : list) {
      if (classroom.getRoomNo() == Integer.parseInt(paramMap.get("roomno"))) {
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
  
}
