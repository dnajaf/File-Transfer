import java.io.*;
import java.util.*;
import java.net.*;


public class Server {


public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    Socket socket = null;
    InputStream inputStream = null;
    FileOutputStream fileOutputStream = null;
    BufferedOutputStream bufferedOutputStream = null;
    int sizeBuffer = 0;

    try{

            try {
                    serverSocket = new ServerSocket(55555);
                } catch (IOException ex) {
                    System.out.println("Error: Unable to Connect to Server ");
                }
            System.out.println("Created Socket");
            try {
                    socket = serverSocket.accept();
                } catch (IOException ex) {
                    System.out.println("Error: Unable to connect to client ");
                }
            System.out.println("Accepted Client Connection ");
            try {
                    inputStream = socket.getInputStream();
                    sizeBuffer = socket.getReceiveBufferSize();
                    System.out.println("Size of Buffer " + sizeBuffer);
                } catch (IOException ex) {
                        System.out.println("Error: unable to ger socket input stream ");
                }       

            try {
                    fileOutputStream = new FileOutputStream("C:\\Users\\dnajaf.bscs12seecs\\Desktop\\Hello.txt");
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                } catch (FileNotFoundException ex) {
                        System.out.println("File not found. ");
                }

            byte[] bytes = new byte[sizeBuffer];

            int count;

            while ((count = inputStream.read(bytes)) > 0) {
                    bufferedOutputStream.write(bytes, 0, count);
            }

            System.out.println("Done");
    }// end of first try

    finally{
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
    inputStream.close();
    socket.close();
    serverSocket.close();
    }
}

}
