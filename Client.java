import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
public static void main(String[] args) throws IOException {

FileInputStream fileInputStream = null;
BufferedInputStream bufferedInputStream = null;
BufferedOutputStream bufferedOuptputStream = null;
Socket socket = null;
int count;

try{
    try {
           socket = new Socket("127.0.0.1", 55555);
        } catch (IOException ex) {
            System.out.println("Error: Unable to create Socket ");
        }

            File file = new File("C:\\Users\\dnajaf.bscs12seecs\\Desktop\\Matrices.txt");
            long fileLength = file.length();
            System.out.println(fileLength);
            if ( Integer.MAX_VALUE < fileLength ) {
                    System.out.println("Error: Exceeded the size of transfer");
            }


            byte[] bytes = new byte[(int) fileLength];
            try{
                        fileInputStream = new FileInputStream(file);
                }catch (IOException ex){
                    System.out.println("Error: Unable to open fileInputStream");
                }
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOuptputStream = new BufferedOutputStream(socket.getOutputStream());

while ((count = bufferedInputStream.read(bytes)) > 0) {
    bufferedOuptputStream.write(bytes, 0, count);
}
}

finally{
        bufferedOuptputStream.flush();
        bufferedOuptputStream.close();
        fileInputStream.close();
        bufferedInputStream.close();
        socket.close();
}

}
}
