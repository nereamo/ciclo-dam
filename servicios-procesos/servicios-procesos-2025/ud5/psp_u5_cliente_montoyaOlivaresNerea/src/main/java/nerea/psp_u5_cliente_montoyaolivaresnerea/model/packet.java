
package nerea.psp_u5_cliente_montoyaolivaresnerea.model;

import java.io.Serializable;

/**
* @author Nerea
*/


//Cliente y servidor usan para intercambiar mensajes cifrados junto con su hash.
//El servidor descifra mensaje, calcula hash, compara hash con el recibido y verifica el contenido
public class packet implements Serializable {
    public byte[] message;
    public byte[] hash;

    public packet(byte[] m, byte[] k) {
        this.message = m; //Mensaje almacenado en objeto pa
        this.hash = k; //Hash asociado al mensaje. Verificar integridad del mensaje.
    }
}
