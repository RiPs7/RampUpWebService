package example;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.xml.ws.Endpoint;

import java.util.HashSet;
import java.util.Set;

import example.ws.LoginREST;
import example.ws.LoginSOAP;

@ApplicationPath("/")
public class MyApplication extends Application{

  @Override
  public Set<Class<?>> getClasses() {
    HashSet h = new HashSet<Class<?>>();
    h.add(LoginREST.class);
    return h;
  }

  static class Main{

    public static void main(String[] argv) {
      Endpoint.publish("http://localhost:9000/login", new LoginSOAP());
    }

  }

}