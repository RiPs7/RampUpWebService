package example;

import javax.xml.ws.Endpoint;

public class Main {

  public static void main(String[] argv) {
    Endpoint.publish("http://localhost:9000/speakLogin", new SpeakLogin());

  }

}
