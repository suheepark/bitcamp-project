package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component(value = "contact/view")
public class ContactViewController extends AbstractCommand{
  ContactDao contactDao;
  
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
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
  }
}
