package bitcamp.java89.ems.server;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.dao.ContactDao;

public class EduAppServer{
  HashMap<String,Command> commandMap = new HashMap<>();
  
  public EduAppServer() {
    ContactDao contactDao = new ContactDao();
    contactDao.setFilename("contact-v1.9.data");
    try {
      contactDao.load();
    } catch (Exception e) {System.out.println("연락처 로딩 중 오류 발생");}
    
    ClassroomDao classroomDao = new ClassroomDao();
    classroomDao.setFilename("classroom-v1.9.data");
    try {
      classroomDao.load();
    } catch (Exception e) {System.out.println("강의실 로딩 중 오류 발생");}
    
    ArrayList<Class> classList = new ArrayList<>();
    ReflectionUtil.getCommandClasses(new File("./bin"), classList);
    for (Class c : classList) {
      try {
        AbstractCommand command = (AbstractCommand)c.newInstance();
        
        try {
          Method m = command.getClass().getMethod("setContactDao", ContactDao.class);
          //System.out.printf("%s:%s\n", command.getClass().getName(), m.getName());
          m.invoke(command, contactDao);
        } catch (Exception e) {}
        
        try {
          Method m = command.getClass().getMethod("setClassroomDao", ClassroomDao.class);
          m.invoke(command, classroomDao);
        } catch (Exception e) {}
        
        commandMap.put(command.getCommandString(), command);
      } catch (Exception e) {}
    }
  }
  
  private void service() throws Exception{
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중..");
    while (true) {
      new RequestThread(ss.accept(), commandMap).start();
    }
  }
    
  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
  
}
