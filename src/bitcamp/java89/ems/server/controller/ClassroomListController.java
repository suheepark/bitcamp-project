package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;
public class ClassroomListController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ClassroomDao classroomDao = ClassroomDao.getInstance();
    ArrayList<Classroom> list = classroomDao.getList();
    for (Classroom classroom : list) {
      out.printf("%d %d %s %s %s %s\n",
      classroom.getRoomNo(), classroom.getCapacity(),
      classroom.getClassName(), classroom.getClassTime(),
      ((classroom.isProjector()) ? "Yes" : "No"),
      ((classroom.isLocker()) ? "Yes" : "No"));
    }
  }

  @Override
  public String getCommandString() {
    // TODO Auto-generated method stub
    return "classroom/list";
  }

}
