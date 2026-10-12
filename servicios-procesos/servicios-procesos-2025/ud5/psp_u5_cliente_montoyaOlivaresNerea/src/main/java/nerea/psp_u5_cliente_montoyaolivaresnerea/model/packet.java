
package nerea.psp_u5_cliente_montoyaolivaresnerea.model;

import java.io.Serializable;

/**
* @author Nerea
*/

public class packet implements Serializable {
    public byte[] message;
    public byte[] hash;

    public packet(byte[] m, byte[] k) {
        this.message = m;
        this.hash = k;
    }
}
