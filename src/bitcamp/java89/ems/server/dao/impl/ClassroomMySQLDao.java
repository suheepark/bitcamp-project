package bitcamp.java89.ems.server.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.ClassroomDao;
import bitcamp.java89.ems.server.util.DataSource;
import bitcamp.java89.ems.server.vo.Classroom;

@Component
public class ClassroomMySQLDao implements ClassroomDao{
  
  DataSource ds;

  public void setDataSource(DataSource ds) {
    this.ds = ds;
  }

  public ArrayList<Classroom> getList() throws Exception {
    ArrayList<Classroom> list = new ArrayList<>();
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select roomno, capa, classname, classtime, projector,locker from ex_classrooms");
        ResultSet rs = stmt.executeQuery();
        )
    {
      while (rs.next()) {
        Classroom classroom = new Classroom();
        classroom.setRoomNo(rs.getInt("roomno"));
        classroom.setCapacity(rs.getInt("capa"));
        classroom.setClassName(rs.getString("classname"));
        classroom.setClassTime(rs.getString("classtime"));
        classroom.setProjector(rs.getBoolean("projector"));
        classroom.setLocker(rs.getBoolean("locker"));
        list.add(classroom);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  public ArrayList<Classroom> getListByRoomNo(int roomNo) throws Exception {
    ArrayList<Classroom> list = new ArrayList<>();
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select roomno, capa, classname, classtime, projector, locker from ex_classrooms where roomno=?");
        )
    {
      stmt.setInt(1, roomNo);;
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) {
        Classroom classroom = new Classroom();
        classroom.setRoomNo(rs.getInt("roomno"));
        classroom.setCapacity(rs.getInt("capa"));
        classroom.setClassName(rs.getString("classname"));
        classroom.setClassTime(rs.getString("classtime"));
        classroom.setProjector(rs.getBoolean("projector"));
        classroom.setLocker(rs.getBoolean("locker"));
        list.add(classroom);
      }
      rs.close();
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  public void insert(Classroom classroom) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into ex_classrooms(roomno,capa,classname,classtime,projector,locker) values(?,?,?,?,?,?)");
        )
    {
      stmt.setInt(1, classroom.getRoomNo());
      stmt.setInt(2, classroom.getCapacity());
      stmt.setString(3, classroom.getClassName());
      stmt.setString(4, classroom.getClassTime());
      stmt.setString(5, classroom.isProjector() ? "Y" : "N");
      stmt.setString(6, classroom.isLocker() ? "Y" : "N");
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }
  
  public void update(Classroom classroom) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update ex_classrooms set capa=?, classname=?, classtime=?, projector=?, locker=? where roomno=?");
        )
    {
      stmt.setInt(6, classroom.getRoomNo());
      stmt.setInt(1, classroom.getCapacity());
      stmt.setString(2, classroom.getClassName());
      stmt.setString(3, classroom.getClassTime());
      stmt.setString(4, classroom.isProjector() ? "Y" : "N");
      stmt.setString(5, classroom.isLocker() ? "Y" : "N");
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }

  public void delete(int roomNo) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement("delete from ex_classrooms where roomno=?");
        )
    {
      stmt.setInt(1, roomNo);
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }

  public boolean existRoomNo(int roomNo) throws Exception {
    Connection con = ds.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement("select * from ex_classrooms where roomno=?");
        )
    {
      stmt.setInt(1, roomNo);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) {
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    } finally {
      ds.returnConnection(con);
    }
  }
  
}
