package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;
public class ClassroomUpdateController extends AbstractCommand {
  ClassroomDao classroomDao;
  
  public void setClassroomDao(ClassroomDao classroomDao) {
    this.classroomDao = classroomDao;
  }
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
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

  @Override
  public String getCommandString() {
    // TODO Auto-generated method stub
    return "classroom/update";
  }

}
