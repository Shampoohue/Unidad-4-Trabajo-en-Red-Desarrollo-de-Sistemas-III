import java.net.*;
import java.util.Scanner;

class WriteServer {
    public static int serverPort = 998;
    public static int clientPort = 999;
    public static int buffer_size = 1024;
    public static DatagramSocket ds;
    public static byte buffer[] = new byte[buffer_size];

    public static void TheServer() throws Exception {
        String ipLocal = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Eres el SERVIDOR y tu IP es: "+ipLocal+"\n");

        //Esperarar el paquete HELLO del cleinte
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        ds.receive(p);

        InetAddress clientAddress = p.getAddress();

        System.out.println("Cliente detectado con IP "+clientAddress.getHostAddress());
        System.out.println("Escribe mensajes y presiona ENTER para enviarlos: ");

        int pos = 0;
        while (true) {
            int c = System.in.read();
            switch (c) {
                case -1:

                    System.out.println("El servidor termina.");
                    return;
                case '\r':
                    break;
                case '\n':
                    ds.send(new DatagramPacket(buffer, pos, clientAddress, clientPort));
                    pos = 0;
                    break;
                default:
                    buffer[pos++] = (byte) c;
            }
        }
    }

    public static void TheClient() throws Exception {

        String ipLocal = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Eres el cñliente y tu IP es: "+ipLocal+"\n");

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa la IP del servidor: ");
        String serverIp = sc.nextLine().trim();

        InetAddress serverAddress = InetAddress.getByName(serverIp); // el byName por que pide bytes

        // Enviar mensaje al servidor
        String hello = "HELLO_SERVER";
        byte[] data = hello.getBytes();
        DatagramPacket helloPacket = new DatagramPacket(data, data.length, serverAddress, serverPort);
        ds.send(helloPacket);

        System.out.println("Mensaje HELLO enviado al servidor.");
        System.out.println("Cliente escuchando mensajes...\n");

        while (true) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            ds.receive(p);
            System.out.println(new String(p.getData(), 0, p.getLength()));
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("¿Eres servidor (S) o cliente (C)?");
        String option = sc.nextLine().trim().toUpperCase();
        if (option.equals("S")) {
            ds = new DatagramSocket(serverPort);
            TheServer();
        } else {
            ds = new DatagramSocket(clientPort);
            TheClient();
        }
    }
}
