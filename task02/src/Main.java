import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Main {

   public static void main(String[] args) throws Exception {

        int port = 3000;
        String serverName = "localhost";

        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            port = Integer.parseInt(args[1]);
            serverName = args[0];
        } else if (args.length == 0) {
            port = 3000;
        } else {
            System.out.println("Input Error");
            return;
        }

        Socket socket = new Socket(serverName, port);

        System.out.println("Connected to server");
        String line = "";

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter ows = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(ows);
        Product product = null;
        LinkedList<String> prodId = new LinkedList<>();
        LinkedList<String> titleList = new LinkedList<>();
        LinkedList<String> priceList = new LinkedList<>();
        LinkedList<String> ratingList = new LinkedList<>();
        
        

        while (null != (line = br.readLine())) {
            String result = br.readLine();
            result = result.trim();
            System.out.printf(" %s\n", result);

            if (line.length() <=0)
                continue;
            String[] des = line.split(":");

            switch (des[0]) {
                case prod_id:
                    prodId.add(des[1]);
                    break;
                case title:
                    titleList.add(des[1]);
                    break;
                case price:
                    priceList.add(des[1]);
                    break;
                case rating:
                    ratingList.add(des[1]);
                    break;                    
            }
        }
      }
}
