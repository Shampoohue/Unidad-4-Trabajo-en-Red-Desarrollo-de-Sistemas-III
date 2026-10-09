import java.net.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class  ChatUDPRed {
    public static final int PORT_A = 998;
    public static final int PORT_B = 999;
    public static final int BUFFER_SIZE = 1024;
    public static byte[] buffer = new byte[BUFFER_SIZE];

    public static volatile InetAddress otherAddress = null;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Eres el participante A? (s/n): ");
        boolean isA = scanner.nextLine().trim().equalsIgnoreCase("s");

        String ipLocal = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Tu ip es: "+ipLocal+"\n");
        String otherIP;
        if (isA){
            System.out.print("Ingresa la IP del otro participante: ");
            otherIP = scanner.nextLine().trim();
        }else{  // eres B
            System.out.println("Esperando mensaje inicial del participante A...");
            otherIP = null;
        }

        int ownPort = isA ? PORT_A : PORT_B;
        int otherPort = isA ? PORT_B : PORT_A;

        DatagramSocket socket = new DatagramSocket(ownPort);
        // InetAddress otherAddress = null;
        if (isA){
            otherAddress = InetAddress.getByName(otherIP);
        }

        // Hilo para recibir mensajes (función de cliente)
        Thread receiveThread = new ReceiverThread(socket);
        receiveThread.start();

        if (isA){
            // Enviar mensaje a B
            String hello = "HELLO_B";
            byte[] data = hello.getBytes();
            DatagramPacket helloPacket = new DatagramPacket(data, data.length, otherAddress, otherPort);
            socket.send(helloPacket);
        }
        // El hilo principal funciona como servidor para enviar mensajes
        System.out.println("Chat iniciado. Escribe tu mensaje (escribe 'salir' para terminar):");
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("salir")) {
                // Aviso al otro usuario antes de cerrar
                String exit = "_EXIT_";
                byte[] exitData = exit.getBytes();
                DatagramPacket exitPacket = new DatagramPacket(exitData, exit.length(), otherAddress, otherPort);
                socket.send(exitPacket);

                socket.close();
                break;
            }

            if (otherAddress == null){
                System.out.println("Aún no se conoce la IP del otro usuario.");
                continue;
            }

            byte[] data = line.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, otherAddress, otherPort);
            socket.send(packet);
        }
    }
}
