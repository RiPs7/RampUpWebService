package example.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "login")
public class LoginSOAP {

  @WebMethod(operationName = "whoAmI")
  public String whoAmI(String login) {
    return "You are " + login;
  }
}
