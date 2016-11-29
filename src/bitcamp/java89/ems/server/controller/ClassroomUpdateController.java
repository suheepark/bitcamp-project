package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;

@Component(value = "classroom/update")
public class ClassroomUpdateController {
  ClassroomDao classroomDao;
  
  public void setClassroomDao(ClassroomDao classroomDao) {
    this.classroomDao = classroomDao;
  }
  @RequestMapping
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
}
