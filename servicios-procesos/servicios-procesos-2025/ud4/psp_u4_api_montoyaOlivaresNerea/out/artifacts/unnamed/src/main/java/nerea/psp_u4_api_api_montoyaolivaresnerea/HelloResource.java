
package nerea.psp_u4_api_api_montoyaolivaresnerea;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
* @author Nerea
*/

@Path("/x")
public class HelloResource {
    public HelloResource() {
    }

    @GET
    @Produces({"text/plain"})
    public String hello() {
        return "Hello, World!";
    }
}
