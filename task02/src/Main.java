import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

   public static void main(String[] args) throws Exception {

        int port = 3000;
        String serverName = "localhost";

        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            port = Integer.parseInt(args[1]);
            serverName = args[0];
        } else {
            port = 3000;
        }

      Socket socket = new Socket(serverName, port);

      System.out.println("Connected to server");
      String line = "";

      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);

      line = br.readLine();

      System.out.printf("SERVER RESPONSE: %s\n", line);

    //   boolean stop = false;
    //   while (!stop) {
    //      String line = "";
    //      line = line.trim();
    //      stop = "exit".equals(line);

    //      bw.write(line + "\n");
    //      bw.flush();

    //      if (stop)
    //         continue;

    //      line = br.readLine();
    //      System.out.printf(">> result: %s\n", line);
    //   }

    //   // Close the stream
    //   bw.flush();
    //   osw.flush();
    //   osw.close();
    //   isr.close();
    //   socket.close();

   }
   
}