package bitcamp.java89.ems.server;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import bitcamp.java89.ems.server.context.ApplicationContext;
import bitcamp.java89.ems.server.context.RequestHandlerMapping;

public class EduAppServer{
  
  ApplicationContext appContext;
  RequestHandlerMapping handlerMapping;
  
  public EduAppServer() {
    HashMap<String,Object> builtInObjectMap = new HashMap<>();
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", "java89", "1111");
      builtInObjectMap.put("dbcon", con);
    } catch (Exception e) {e.printStackTrace();}
    
    appContext = new ApplicationContext(
        new String[]{"bitcamp.java89.ems.server.controller", "bitcamp.java89.ems.server.dao"}, builtInObjectMap);
    handlerMapping = new RequestHandlerMapping(appContext.getAllBeans());
  }
  
  private void service() throws Exception{
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중..");
    while (true) {
      new RequestThread(ss.accept(), handlerMapping).start();
    }
  }
    
  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
  
}
