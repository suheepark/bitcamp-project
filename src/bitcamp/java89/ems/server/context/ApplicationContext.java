//빈 컨테이너(Bean Container) 역할
// => bean = object = instance
package bitcamp.java89.ems.server.context;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import bitcamp.java89.ems.server.annotation.Component;

public class ApplicationContext {
  
  HashMap<String,Object> objPool = new HashMap<>();
  
  public Object getBean(String name) {
    return objPool.get(name);
  }
  
  public Collection<Object> getAllBeans() {
    return objPool.values();
  }
  
  public ApplicationContext(String[] packages) {
    this(packages, null);
  }

  public ApplicationContext(String[] packages, HashMap<String, Object> builtInObjectMap) {
    if (builtInObjectMap != null) {
      Set<Entry<String,Object>> entrySet = builtInObjectMap.entrySet();
      for (Entry<String,Object> entry : entrySet) {
        objPool.put(entry.getKey(), entry.getValue());
      }
    }
    ArrayList<Class<?>> classList = getClassList(packages);
    prepareObjects(classList);
    injectDependencies();
  }

  private void injectDependencies() {
    Collection<Object> objects = objPool.values();
    for (Object obj : objects) {
      System.out.println(obj.getClass().getName());
      Method[] methods = obj.getClass().getMethods();
      for (Method m : methods) {
        if (!m.getName().startsWith("set")) {continue;}
        if (m.getParameterCount() != 1) {continue;}
        Class<?> paramType = m.getParameterTypes()[0];
        Object dependency = findDependency(paramType);
        if (dependency != null) {
          try {
            System.out.println("==>" + m.getName());
            m.invoke(obj, dependency);
          } catch (Exception e) {}
        }
      }
    }
  }

  private Object findDependency(Class<?> paramType) {
    Collection<Object> objects = objPool.values();
    for (Object obj : objects) {
      if (paramType.isInstance(obj)) {return obj;}
    }
    return null;
  }

  private ArrayList<Class<?>> getClassList(String[] packages) {
    ArrayList<Class<?>> classList = new ArrayList<>();
    for (String packageName : packages) {
      try {
        findClasses(packageNameToFile(packageName), classList);
      } catch (Exception e) {e.printStackTrace();}
    }
    return classList;
  }

  private void prepareObjects(ArrayList<Class<?>> classList) {
    for (Class<?> clazz : classList) {
      try {
        Object obj = clazz.newInstance();
        String key = null;
        Component compAnno = clazz.getAnnotation(Component.class);
        if (compAnno.value().length() == 0) {
          objPool.put(clazz.getName(), obj);
        } else {
          objPool.put(compAnno.value(), obj);
          System.out.println(compAnno.value());
        }
        objPool.put(key, obj);
      } catch (Exception e) {e.printStackTrace();}
    }
  }
  
  private File packageNameToFile(String packageName) {
    return new File("./bin/" + packageName.replace(".", "/"));
  }
  
  private void findClasses(File dir, ArrayList<Class<?>> classList) throws IOException {
    if (!dir.isDirectory()) {
      return;
    }
    
    File[] files = dir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.isDirectory()) 
          return true;
        if (pathname.getName().endsWith(".class") && !pathname.getName().contains("$"))
          return true;
        return false;
      }
    });
    
    for (File file : files) {
      if (file.isDirectory()) {
        findClasses(file, classList);
      } else {
        try {
          Class<?> c = loadClass(file);
          if (!isAbstract(c) && isComponent(c)) {
            classList.add(c);
          }
        } catch (Exception e) {e.printStackTrace();}
      }
    }
  }

  private boolean isComponent(Class<?> c) {
    return c.getAnnotation(Component.class) != null;
  }

  private Class<?> loadClass(File file) throws IOException, ClassNotFoundException {
    String path = file.getCanonicalPath().replaceAll("\\\\", "/").replaceAll(".class", "");
    int pos = path.indexOf("/bin/");
    return Class.forName(path.substring(pos + 5).replaceAll("/", "."));
  }
  
  private boolean isAbstract(Class<?> clazz) {
    if ((clazz.getModifiers() & Modifier.ABSTRACT) == Modifier.ABSTRACT) {
      return true;
    }
    return false;
  }
  /*
  public static void main(String[] args) {
    ApplicationContext appContext = new ApplicationContext(
        new String[]{"bitcamp.java89.ems.server.controller", "bitcamp.java89.ems.server.dao"});
  }
 */
}
