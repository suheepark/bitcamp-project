package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactViewController implements Command{
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      ContactDao contactDao = ContactDao.getInstance();
      ArrayList<Contact> list = contactDao.getListByName(paramMap.get("name"));
      for (Contact contact : list) {
        if (contact.getName().equals(paramMap.get("name"))) {
          out.printf("이름: %s\n", contact.getName());
          out.printf("직위: %s\n", contact.getPosition());
          out.printf("전화: %s\n", contact.getTel());
          out.printf("이메일: %s\n", contact.getEmail());
          out.println("-------------------");
        }
      } 
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}
