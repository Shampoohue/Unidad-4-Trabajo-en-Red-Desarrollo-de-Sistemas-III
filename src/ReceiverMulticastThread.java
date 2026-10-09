import java.net.*;

public class ReceiverMulticastThread implements Runnable {
    private MulticastSocket socket;
    private byte[] buffer;
    // private InetAddress multicastAddress;
    //private int port;

    public ReceiverMulticastThread(MulticastSocket socket, byte[] buffer) {
        this.socket = socket;
        this.buffer  = buffer;
        //this.multicastAddress = multicastAddress;
        //this.port = port;
    }

    @Override
    public void run() {
        try {
            // Crear un MulticastSocket para recibir mensajes
            //MulticastSocket multicastSocket = new MulticastSocket(port);
            //multicastSocket.joinGroup(multicastAddress); // Unirse al grupo multicast

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Recibir el mensaje
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("["+packet.getAddress().getHostAddress()+"]: " + msg); // Imprimir el mensaje recibido
            }
        } catch (Exception e) {
            System.out.println("Receptor finalizado.");
        }
    }
}
