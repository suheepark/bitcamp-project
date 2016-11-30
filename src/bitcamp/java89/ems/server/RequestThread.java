package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.context.RequestHandlerMapping;
import bitcamp.java89.ems.server.context.RequestHandlerMapping.RequestHandler;

public class RequestThread extends Thread {
  private Socket socket;
  private Scanner in;
  private PrintStream out;
  private RequestHandlerMapping handlerMapping;
  
  public RequestThread(Socket socket, RequestHandlerMapping handlerMapping) {
    this.socket = socket;
    this.handlerMapping = handlerMapping;
  }
  
  @Override
  public void run() {
    try {
      in = new Scanner(new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
      
      out.println("비트캠프 관리시스템에 오신걸 환영합니다.");
      
      while (true) {
        out.println("명령> ");
        out.println();
        
        String[] command = in.nextLine().split("\\?");

        HashMap<String,String> dataMap = new HashMap<>();
        if (command.length == 2) {
          String[] params = command[1].split("&");
          for (String value : params) {
            String[] kv = value.split("=");
            dataMap.put(kv[0], kv[1]);
          }
        }
        
        RequestHandler requestHandler = handlerMapping.getRequestHandler(command[0]);
        
        if (requestHandler == null) {
          if (command[0].equals("quit")) {
            doQuit();
            break;
          }
          out.println("지원하지 않는 명령어입니다.");
          continue;
        }
        try {
          requestHandler.method.invoke(requestHandler.obj, getArguments(requestHandler.method, dataMap, out));
        } catch (Exception e) {
          out.println("작업 중 오류가 발생하였습니다.");
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }
  }

  private Object[] getArguments(Method method, HashMap<String, String> dataMap, PrintStream out) {
    Parameter[] params = method.getParameters();
    Object[] args = new Object[params.length];
    for (int i = 0; i < params.length; i++) {
      RequestParam anno = params[i].getAnnotation(RequestParam.class);
      if (anno != null) {
        String value = anno.value();
        if (params[i].getType() == int.class) {
          args[i] = Integer.parseInt(dataMap.get(value));
        } else if (params[i].getType() == boolean.class) {
          args[i] = Boolean.parseBoolean(dataMap.get(value));
        } else if (params[i].getType() == String.class) {
          args[i] = dataMap.get(value);
        } else
          args[i] = null;
      } else {
        if (params[i].getType() == PrintStream.class) {
          args[i] = out;
        } else if (params[i].getType() == HashMap.class) {
          args[i] = dataMap;
        } else {
          args[i] = null;
        }
      }
    }
    return args;
  }

  private boolean doQuit() {
    System.out.println("클라이언트 연결 종료");
    return true;
  }

}
