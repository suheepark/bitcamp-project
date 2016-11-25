package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;
import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactUpdateController implements Command{
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      ContactDao contactDao = ContactDao.getInstance();
      if (contactDao.existEmail(paramMap.get("email"))) {
        Contact contact = new Contact();
        contact.setName(paramMap.get("name"));
        contact.setPosition(paramMap.get("position"));
        contact.setTel(paramMap.get("tel"));
        contact.setEmail(paramMap.get("email"));
        contactDao.update(contact);
        out.println("변경하였습니다.");
        return;
      }
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}
