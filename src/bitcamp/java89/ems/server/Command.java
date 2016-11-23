// EduAppServer(사용자)와 Controller(피사용자) 사이의 호출 규칙
package bitcamp.java89.ems.server;

import java.io.PrintStream;
import java.util.HashMap;

public interface Command {
  void service(HashMap<String,String> paramMap, PrintStream out);
}
