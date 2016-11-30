package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;

@Component
public class ClassroomController {
  ClassroomDao classroomDao;
  
  public void setClassroomDao(ClassroomDao classroomDao) {
    this.classroomDao = classroomDao;
  }
  //classroom/add?roomno=301&capacity=30&classname=자바&classtime=09~18&projector=true&locker=true
  @RequestMapping(value = "classroom/add")
  public void add(
      @RequestParam("roomno")int roomno,
      @RequestParam("capacity")int capacity,
      @RequestParam("classname")String classname,
      @RequestParam("classtime")String classtime,
      @RequestParam("projector")boolean projector,
      @RequestParam("locker")boolean locker,
      PrintStream out) throws Exception {
    Classroom classroom = new Classroom(); 
    classroom.setRoomNo(roomno);
    classroom.setCapacity(capacity);
    classroom.setClassName(classname);
    classroom.setClassTime(classtime);
    classroom.setProjector(projector);
    classroom.setLocker(locker);
    classroomDao.insert(classroom);
    out.println("등록하였습니다.");
  }
  
  @RequestMapping(value = "classroom/delete")
  public void delete(@RequestParam("roomno")int roomno, PrintStream out) throws Exception {
    classroomDao.delete(roomno);
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
  public void update(
      @RequestParam("roomno")int roomno,
      @RequestParam("capacity")int capacity,
      @RequestParam("classname")String classname,
      @RequestParam("classtime")String classtime,
      @RequestParam("projector")boolean projector,
      @RequestParam("locker")boolean locker,
      PrintStream out) throws Exception {
    Classroom classroom = new Classroom();
    classroom.setRoomNo(roomno);
    classroom.setCapacity(capacity);
    classroom.setClassName(classname);
    classroom.setClassTime(classtime);
    classroom.setProjector(projector);
    classroom.setLocker(locker);
    classroomDao.update(classroom);
    out.println("변경하였습니다.");
    return;
  }
  
  @RequestMapping(value = "classroom/view")
  public void view(@RequestParam("roomno")int roomno, PrintStream out) throws Exception {
    ArrayList<Classroom> list = classroomDao.getListByRoomNo(roomno);
    for (Classroom classroom : list) {
      if (classroom.getRoomNo() == roomno) {
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
