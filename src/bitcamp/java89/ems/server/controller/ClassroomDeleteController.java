package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ClassroomDao;

@Component(value = "classroom/delete")
public class ClassroomDeleteController {
  ClassroomDao classroomDao;
  
  public void setClassroomDao(ClassroomDao classroomDao) {
    this.classroomDao = classroomDao;
  }
  @RequestMapping
  public void delete(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    classroomDao.delete(Integer.parseInt(paramMap.get("roomno")));
    out.println("삭제하였습니다");
  }
}
