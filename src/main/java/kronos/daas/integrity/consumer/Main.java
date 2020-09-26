package kronos.daas.integrity.consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;

public class Main {

  @SneakyThrows
    public static void main(String[] args) {
      Process p;
      try {
        String[] cmd = { "sh", "/Users/pavel/ideaProjects/kronos/integrity-consumer/src/main/resources/test.sh"};
        p = Runtime.getRuntime().exec(cmd);
        p.waitFor();
        BufferedReader reader=new BufferedReader(new InputStreamReader(
            p.getInputStream()));
        String line;
        while((line = reader.readLine()) != null) {
          System.out.println(line);
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

}
