Practicas Unidades 4,5, y 6 - Trabajo en Red (Java)
Creador (Gerardo Tapia F.)

Estos ejercicios son de la relacion cliente-servidor, cubren: los sockets UDP y TCP, multicast, hilos para recepción, puertos y direcciones IP, consultas DNS, peticiones HTTP, WHOIS y chats entre dos o mas participantes. Todo para practicar cómo se envían y reciben datos por la red y cómo Java implementa los protocolos mas comunes.

Si se tiene problemas con la ejecución, se tiene que establecer la direccion como "127.0.0.1" fijamente.

1. InetAddressTest.java: ejemplo de InetAddress

2. Whois.java: Socket Cliente
3. URLDemo.java: ejemplo de URL y sus componentes
4. UCDemo.java: ejempolo de URLConnection
5. HttpURLDemo.java: ejemplo de HttpURLConnection
6. WriteServer: DatagramPackets (chat servidor-cliente)
7. ChatUDP: chat bidireccional en localhost (modificando el ejemplo anterior, utiliza ReceiverThread)
8. ChatUDPRed: chat bidireccional en red local (modificando el ejemplo anterior, utiliza el mismo ReceiverThread)
9. ChatUDPMulticast: chat multicast, es decir, con varios usuarios a la vez (modificando el ejemplo anterior, utiliza ReceiverMulticastThread que es una modificación de ReceiverThread)

Imagenes:
* Resultado de WhoIs.java
<img width="1129" height="647" alt="image" src="https://github.com/user-attachments/assets/d99f1987-df3c-406e-b0d9-754211a1d833" />

* WriteServer
  <img width="479" height="268" alt="image" src="https://github.com/user-attachments/assets/d7d0c548-45e4-45be-841a-81fb3ea225e4" />

