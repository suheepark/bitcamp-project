package bitcamp.java89.ems.server;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class EduAppServer{
  HashMap<String,Command> commandMap = new HashMap<>();
  
  public EduAppServer() throws IOException {
    ArrayList<Class> classList = new ArrayList<>();
    ReflectionUtil.getCommandClasses(new File("./bin"), classList);
    for (Class c : classList) {
      try {
        AbstractCommand command = (AbstractCommand)c.newInstance();
        commandMap.put(command.getCommandString(), command);
      } catch (Exception e) {}
    }

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
