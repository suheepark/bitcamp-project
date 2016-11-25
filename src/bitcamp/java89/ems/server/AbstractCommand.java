package bitcamp.java89.ems.server;

import java.io.PrintStream;
import java.util.HashMap;

public abstract class AbstractCommand implements Command {
  
  public abstract String getCommandString();

  public void service(java.util.HashMap<String,String> paramMap, java.io.PrintStream out) {
    try {
      doResponse(paramMap, out);
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }

  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    System.out.println("작업을 준비중입니다.");
  }
}
