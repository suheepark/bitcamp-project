package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;
public class ClassroomUpdateController implements Command {
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      ClassroomDao classroomDao = ClassroomDao.getInstance();
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
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }

}
