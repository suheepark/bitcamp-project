package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ClassroomDao;
public class ClassroomDeleteController implements Command {
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      ClassroomDao classroomDao = ClassroomDao.getInstance();
      classroomDao.delete(Integer.parseInt(paramMap.get("roomno")));
      out.println("삭제하였습니다");
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }

}
