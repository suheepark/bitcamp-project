package bitcamp.java89.ems.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import bitcamp.java89.ems.server.controller.ClassroomAddController;
import bitcamp.java89.ems.server.controller.ClassroomDeleteController;
import bitcamp.java89.ems.server.controller.ClassroomListController;
import bitcamp.java89.ems.server.controller.ClassroomUpdateController;
import bitcamp.java89.ems.server.controller.ClassroomViewController;
import bitcamp.java89.ems.server.controller.ContactAddController;
import bitcamp.java89.ems.server.controller.ContactDeleteController;
import bitcamp.java89.ems.server.controller.ContactListController;
import bitcamp.java89.ems.server.controller.ContactUpdateController;
import bitcamp.java89.ems.server.controller.ContactViewController;

public class EduAppServer{
  HashMap<String,Command> commandMap = new HashMap<>();
  
  public EduAppServer() throws IOException {
    commandMap.put("contact/list",new ContactListController());
    commandMap.put("contact/view",new ContactViewController());
    commandMap.put("contact/add",new ContactAddController());
    commandMap.put("contact/delete",new ContactDeleteController());
    commandMap.put("contact/update",new ContactUpdateController());
    commandMap.put("classroom/add",new ClassroomAddController());
    commandMap.put("classroom/list",new ClassroomListController());
    commandMap.put("classroom/view",new ClassroomViewController());
    commandMap.put("classroom/delete",new ClassroomDeleteController());
    commandMap.put("classroom/update",new ClassroomUpdateController());
  }
  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
  
  private void service() throws Exception{
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중..");
    while (true) {
      new RequestThread(ss.accept(), commandMap).start();
    }
  }
}
