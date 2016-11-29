package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component(value = "contact/list")
public class ContactListController {
  ContactDao contactDao;
  
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }
  
  @RequestMapping
  public void list(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ArrayList<Contact> list = contactDao.getList();
    for (Contact contact : list) {
      out.printf("%s %s %s %s\n",
      contact.getName(), contact.getPosition(),
      contact.getTel(), contact.getEmail());
    }
  }
}
