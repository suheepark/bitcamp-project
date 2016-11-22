package bitcamp.java89.ems.server.controller;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Contact;
public class ContactController {
  private Scanner in;
  private PrintStream out;
  private ArrayList<Contact> list;
  static boolean changed = false;
  private String filename = "contact-v1.6.data";
  
  public ContactController(Scanner in, PrintStream out) throws IOException {
    this.in = in;
    this.out = out;
    list = new ArrayList<Contact>();
    this.doLoad();
  }

  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void doLoad() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);
      list = (ArrayList<Contact>)in.readObject();
    } catch (EOFException e) {
      // 파일을 모두 읽은 경우
    } catch (Exception e) {
      //System.out.println("데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {}
    }
  }

  public void doSave() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);
    out.writeObject(list);
    changed = false;
    out.close();
    out0.close();
  }

  public boolean service() throws IOException {
    while (true) {
      out.println("연락처관리> ");
      out.println();
      String[] command = in.nextLine().split("\\?");
      try {
        switch (command[0]) {
          case "add" : this.doAdd(command[1]); break;
          case "list" : this.doList(); break;
          case "view" : this.doView(command[1]); break;
          case "delete" : this.doDelete(command[1]); break;
          case "update" : this.doUpdate(command[1]); break;
          case "main" : return true;
          case "quit" : return false;
          default :
            out.println("지원하지 않는 명령어입니다.");
        }
      } catch (Exception e) {
        out.println("실행 중 오류가 발생했습니다.");
        e.printStackTrace();
      }
    }
  }

  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    Contact contact = new Contact();
    boolean save = true;
    contact.setName(paramMap.get("name"));
    contact.setPosition(paramMap.get("position"));
    contact.setTel(paramMap.get("tel"));
    contact.setEmail(paramMap.get("email"));
    if (existEmail(contact.getEmail())) {
      out.println("같은 이메일이 존재합니다. 등록을 취소합니다.");
      return;
    }
    if (save) {
      list.add(contact);
      changed = true;
    }
  }

  private boolean existEmail(String email) {
    for (Contact contact : list) {
      if (contact.getEmail().toLowerCase().equals(email)) {
        return true;
      }
    }
    return false;
  }

  private void doList() {
    for (Contact contact : list) {
      out.printf("%s %s %s %s\n",
      contact.getName(), contact.getPosition(),
      contact.getTel(), contact.getEmail());
    }
  }

  private void doView(String params) {
    String[] kv = params.split("=");
    for (Contact contact : list) {
      if (contact.getName().equals(kv[1])) {
        out.printf("이름: %s\n", contact.getName());
        out.printf("직위: %s\n", contact.getPosition());
        out.printf("전화: %s\n", contact.getTel());
        out.printf("이메일: %s\n", contact.getEmail());
        out.println("-------------------");
      }
    }
  }
  
  private void doDelete(String params) {
    String[] kv = params.split("=");
    for (Contact contact : list) {
      if (contact.getEmail().equals(kv[1])) {
        list.remove(contact);
        changed = true;
        out.println("삭제하였습니다.");
        break;
      }
    }
  }
  
  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    boolean save = true;
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    for (Contact contact : list) {
      if (contact.getEmail().equals(paramMap.get("email"))) {
        contact.setName(paramMap.get("name"));
        contact.setPosition(paramMap.get("position"));
        contact.setTel(paramMap.get("tel"));
        if (save) {
          changed = true;
          out.println("변경하였습니다.");
        }
        return;
      }
    }
    out.println("이메일을 찾지 못했습니다.");
  }

}
