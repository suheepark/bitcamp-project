package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component(value = "contact/update")
public class ContactUpdateController {
  ContactDao contactDao;
  
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }
  @RequestMapping
  public void update(HashMap<String,String> paramMap, PrintStream out) throws Exception {
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
  }
}
