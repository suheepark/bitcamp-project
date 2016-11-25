package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;

public class ClassroomAddController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ClassroomDao classroomDao = ClassroomDao.getInstance();
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

}
