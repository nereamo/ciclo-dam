
package nerea.psp_u1_nereamontoyaolivares;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* @author Nerea
*/

public class Exit {
    static List<Process> childProcesses = new ArrayList();

    public Exit() {
    }

    public static boolean destroyAllChildren() {
        boolean processDestroyed = false;
        Iterator var1 = childProcesses.iterator();

        while(var1.hasNext()) {
            Process process = (Process)var1.next();
            if (process.isAlive()) {
                process.destroy();

                try {
                    process.waitFor();
                    processDestroyed = true;
                } catch (InterruptedException var4) {
                    InterruptedException e = var4;
                    System.err.println("Error esperando a que el proceso termine: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        childProcesses.clear();
        return processDestroyed;
    }

    public static void setProcess(Process process) {
        childProcesses.add(process);
    }
}
