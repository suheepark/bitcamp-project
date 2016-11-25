package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.vo.Classroom;
public class ClassroomListController implements Command {
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      ClassroomDao classroomDao = ClassroomDao.getInstance();
      ArrayList<Classroom> list = classroomDao.getList();
      for (Classroom classroom : list) {
        out.printf("%d %d %s %s %s %s\n",
        classroom.getRoomNo(), classroom.getCapacity(),
        classroom.getClassName(), classroom.getClassTime(),
        ((classroom.isProjector()) ? "Yes" : "No"),
        ((classroom.isLocker()) ? "Yes" : "No"));
      }
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }

}
