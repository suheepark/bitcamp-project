package bitcamp.java89.ems.server.dao;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Classroom;
public class ClassroomDao extends AbstractDao<Classroom> {
  
  public ClassroomDao() throws Exception {
    this.setFilename("classroom-v1.9.data");
    this.load();
  }

  public boolean existRoomNo(int roomNo) {
    for (Classroom classroom : list) {
      if (classroom.getRoomNo() == roomNo) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Classroom> getList() {
    return this.list;
  }
  
  public ArrayList<Classroom> getListByRoomNo(int roomNo) {
    ArrayList<Classroom> results = new ArrayList<>();
    for (Classroom classroom : list) {
      if (classroom.getRoomNo() == roomNo) {
        results.add(classroom);
      }
    }
    return results;
  }
  
  synchronized public void insert(Classroom classroom) {
    list.add(classroom);
    try {this.save();} catch (Exception e) {e.printStackTrace();}
    return;
  }

  synchronized public void delete(int roomNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getRoomNo() == roomNo) {
        list.remove(i);
        try {this.save();} catch (Exception e) {e.printStackTrace();}
        return;
      }
    }
  }

  synchronized public void update(Classroom classroom) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getRoomNo() == classroom.getRoomNo()) {
        list.set(i, classroom);
        try {this.save();} catch (Exception e) {e.printStackTrace();}
        return;
      }
    }
  }

}
