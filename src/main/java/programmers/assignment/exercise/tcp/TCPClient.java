package programmers.assignment.exercise.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        Socket socket = new Socket();
        Scanner scanner = new Scanner(System.in);

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        PrintWriter printWriter = null;

        try {
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 6077));
            System.out.println("[client] connected with server");

            while (true){
                inputStream = socket.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);

                outputStream = socket.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                printWriter = new PrintWriter(outputStreamWriter, true);

                // 데이터 입력
                System.out.print(">>");
                String data = scanner.nextLine();

                // 종료식
                if("exit".equalsIgnoreCase(data)){
                    break;
                }

                printWriter.println(data);

                data = bufferedReader.readLine();
                System.out.println("<< " + data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null && !socket.isClosed()){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}
