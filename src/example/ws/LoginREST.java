package example.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/login")
public class LoginREST {
  @GET
  @Produces("application/xml")
  public String callLoginSOAP(@QueryParam("uname") String uname) {

    try {
      String soapEndpoint = "http://localhost:9000/login?WSDL";
      HttpURLConnection connection = (HttpURLConnection) (new URL(soapEndpoint).openConnection());
      String soapReq =
          "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.example/\">\n" +
          "   <soapenv:Header/>\n" +
          "   <soapenv:Body>\n" +
          "      <ws:whoAmI>\n" +
          "         <arg0>" + uname + "</arg0>\n" +
          "      </ws:whoAmI>\n" +
          "   </soapenv:Body>\n" +
          "</soapenv:Envelope>";
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-Length", String.valueOf(soapReq.getBytes().length));
      connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
      connection.setRequestProperty("SOAPAction", "\"whoAmI\"");
      connection.setDoInput(true);
      connection.setDoOutput(true);

      OutputStream outputStream = connection.getOutputStream();
      outputStream.write(soapReq.getBytes());
      outputStream.close();

      InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
      BufferedReader in = new BufferedReader(inputStreamReader);
      String soapResp = "", response;
      while ((response = in.readLine()) != null){
        soapResp += response;
      }

      return soapResp;

    } catch (Exception e) {
      return "\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n" + e.getStackTrace();
    }
  }
}