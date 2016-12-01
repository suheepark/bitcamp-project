package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Contact;

public interface ContactDao {
  ArrayList<Contact> getList() throws Exception;
  ArrayList<Contact> getListByName(String name) throws Exception;
  void insert(Contact contact) throws Exception;
  void update(Contact contact) throws Exception;
  void delete(String email) throws Exception;
  boolean existEmail(String email) throws Exception;
}
