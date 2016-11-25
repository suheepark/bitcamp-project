package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ClassroomDao;
public class ClassroomDeleteController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ClassroomDao classroomDao = ClassroomDao.getInstance();
    classroomDao.delete(Integer.parseInt(paramMap.get("roomno")));
    out.println("삭제하였습니다");
  }

  @Override
  public String getCommandString() {
    // TODO Auto-generated method stub
    return "classroom/delete";
  }

}
