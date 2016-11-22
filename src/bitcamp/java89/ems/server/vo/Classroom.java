package bitcamp.java89.ems.server.vo;
import java.io.Serializable;
public class Classroom implements Serializable {
  protected static final long serialVersionUID = 1L;

  protected int roomNo;
  protected int capacity;
  protected String className;
  protected String classTime;
  protected boolean projector;
  protected boolean locker;

  public int getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(int roomNo) {
    this.roomNo = roomNo;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getClassTime() {
    return classTime;
  }

  public void setClassTime(String classTime) {
    this.classTime = classTime;
  }

  public boolean isProjector() {
    return projector;
  }

  public void setProjector(boolean projector) {
    this.projector = projector;
  }

  public boolean isLocker() {
    return locker;
  }

  public void setLocker(boolean locker) {
    this.locker = locker;
  }

  public Classroom() {}

  public Classroom(int roomNo, String className, String classTime) {
    this.roomNo = roomNo;
    this.className = className;
    this.classTime = classTime;
  }

  @Override
  public String toString() {
    return "Classroom [roomNo=" + roomNo + ", capacity=" + capacity + ", className=" + className + ", classTime="
        + classTime + ", projector=" + projector + ", locker=" + locker + "]";
  }

}
