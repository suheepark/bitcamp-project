package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ClassroomDao;
public class ClassroomDeleteController extends AbstractCommand {
  ClassroomDao classroomDao;
  
  public void setClassroomDao(ClassroomDao classroomDao) {
    this.classroomDao = classroomDao;
  }
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    classroomDao.delete(Integer.parseInt(paramMap.get("roomno")));
    out.println("삭제하였습니다");
  }

  @Override
  public String getCommandString() {
    // TODO Auto-generated method stub
    return "classroom/delete";
  }

}
