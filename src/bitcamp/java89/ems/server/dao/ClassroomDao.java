package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Classroom;

public interface ClassroomDao {
  ArrayList<Classroom> getList() throws Exception;
  ArrayList<Classroom> getListByRoomNo(int roomNo) throws Exception;
  void insert(Classroom classroom) throws Exception;
  void delete(int roomNo) throws Exception;
  void update(Classroom classroom) throws Exception;
  boolean existRoomNo(int roomNo) throws Exception;
}
