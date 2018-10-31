package example;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "speakLogin")
public class SpeakLogin {

  @WebMethod(operationName = "reportLogin")
  public String reportLogin(String login) {
    String result = "You are " + login;
    System.out.println(result);
    return result;
  }
}
