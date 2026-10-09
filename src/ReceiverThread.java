import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.SignatureSpi;

public class ReceiverThread extends Thread {
    private static final int BUFFER_SIZE = 1024;
    private final DatagramSocket socket;
    private final byte[] buffer = new byte[BUFFER_SIZE];

    public ReceiverThread(DatagramSocket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());

                if (msg.equals("_EXIT_")){
                    System.out.println("** El otro usuario se ha desconectado **");
                    socket.close();
                    System.exit(0); // cierra el programa
                }
                if (msg.equals("HELLO_B")){
                    InetAddress aIP = packet.getAddress();
                    System.out.println("El participante A "+aIP+" se conectó de "+aIP.getHostAddress());
                    ChatUDPRed.otherAddress = aIP;
                }

                System.out.println("Recibido: " + msg);
            }
        } catch (Exception e) {
            System.out.println("Receptor finalizado.");
        }
    }
}
