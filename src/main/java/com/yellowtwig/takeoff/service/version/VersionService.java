/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.service.version;

import com.yellowtwig.service.jersey.RestResource;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

//@Stateless
@Path("version")
public class VersionService extends RestResource {
    
    @Context
    private ServletContext context;
    
    @GET
    @Produces("text/plain")
    public String getVersion() {
        String version = "Takeoff - Version 1.1 20141213";
        return version;
//        final InputStream inputStream = context.getResourceAsStream("/META-INF/MANIFEST.MF");
//        try {
//            final Manifest manifest = new Manifest(inputStream);
//            return Response.ok().entity(stringifyManifest(manifest)).build();
//        } catch (IOException e) {
//            return Response.serverError().type(MediaType.TEXT_PLAIN_TYPE).entity(e).build();
//        }
    }

    private String stringifyManifest(final Manifest manifest) {
        final StringBuilder sb = new StringBuilder();
        final Attributes entries = manifest.getMainAttributes();
        for (Object key : entries.keySet()) {
            sb.append(key.toString());
            sb.append(':');
            sb.append(entries.getValue(key.toString()));
            sb.append('\n');
        }

        return sb.toString();
    }
    
}
