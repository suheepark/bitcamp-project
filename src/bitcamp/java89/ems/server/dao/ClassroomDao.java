package bitcamp.java89.ems.server.dao;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Classroom;
public class ClassroomDao {
  private ArrayList<Classroom> list;
  static boolean changed = false;
  private String filename = "classroom-v1.7.data";
  static ClassroomDao obj;
  
  private ClassroomDao() throws IOException {
    this.doLoad();
  }
  
  public static ClassroomDao getInstance() throws IOException {
    if (obj == null) {
      obj = new ClassroomDao();
    }
    return obj;
  }

  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void doLoad() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);
      list = (ArrayList<Classroom>)in.readObject();
    } catch (EOFException e) {
      // 파일을 모두 읽은 경우
    } catch (Exception e) {
      System.out.println("강의실 데이터 로딩 중 오류 발생!");
      list = new ArrayList<Classroom>();
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {}
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);
    out.writeObject(list);
    changed = false;
    out.close();
    out0.close();
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
    changed = true;
    return;
  }

  synchronized public void delete(int roomNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getRoomNo() == roomNo) {
        list.remove(i);
        changed = true;
        return;
      }
    }
  }

  synchronized public void update(Classroom classroom) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getRoomNo() == classroom.getRoomNo()) {
        list.set(i, classroom);
        changed = true;
        return;
      }
    }
  }

}
