package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component
public class ContactController {
  ContactDao contactDao;
  
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }
  
  @RequestMapping(value = "contact/add")
  public void add(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    if (contactDao.existEmail(paramMap.get("email"))) {
      out.println("같은 이메일이 존재합니다. 등록을 취소합니다.");
      return;
    }
    Contact contact = new Contact();
    contact.setName(paramMap.get("name"));
    contact.setPosition(paramMap.get("position"));
    contact.setTel(paramMap.get("tel"));
    contact.setEmail(paramMap.get("email"));
    contactDao.insert(contact);
    out.println("등록하였습니다.");
  }
  
  @RequestMapping(value = "contact/delete")
  public void delete(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    if (!contactDao.existEmail(paramMap.get("email"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    contactDao.delete(paramMap.get("email"));
    out.println("삭제하였습니다.");
   }
  
  @RequestMapping(value = "contact/list")
  public void list(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ArrayList<Contact> list = contactDao.getList();
    for (Contact contact : list) {
      out.printf("%s %s %s %s\n",
      contact.getName(), contact.getPosition(),
      contact.getTel(), contact.getEmail());
    }
  }
  
  @RequestMapping(value = "contact/update")
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
  
  @RequestMapping(value = "contact/view")
  public void view(HashMap<String,String> paramMap, PrintStream out) throws Exception {
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
