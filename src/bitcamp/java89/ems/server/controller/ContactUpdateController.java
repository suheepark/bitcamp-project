package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactUpdateController extends AbstractCommand{
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
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
  }

  @Override
  public String getCommandString() {
    // TODO Auto-generated method stub
    return "contact/update";
  }
}
