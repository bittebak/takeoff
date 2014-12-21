/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.service.idp;

import com.yellowtwig.service.jersey.RestResource;
import com.yellowtwig.takeoff.persistance.User;
import com.yellowtwig.takeoff.persistance.dataservice.idp.UserDS;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author marcprive
 */
@Path("idp")
public class AuthenticationRS extends RestResource {

    @EJB(name = "UserDS")
    private UserDS dataService;

    @Resource(lookup = "jndi/yellowtwigmail")
    private Session mailSession;

    @POST
    @Path("authenticate")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public XmlLoginResponse authenticate(final XmlLoginRequest loginRequest) {

        XmlLoginResponse response = new XmlLoginResponse();

        User user = dataService.get(loginRequest.getUsername());
        if (user.validatePassword(loginRequest.getPassword())) {
            response.setUserId(user.getUsername());

        }
        return response;
    }

    UserDS getDataService() {
        return dataService;
    }

    /**
     * Change the password of a user. The password can only be changed if the
     * old password is provided.
     *
     * @param identity identity of the user
     *
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @PUT
    @Path("password/{identity}")
    @Produces({"application/xml", "application/json"})
    public User changePasword(@PathParam("identity") String identity, @QueryParam("newPassword") String newPassword, @QueryParam("oldPassword") String oldPassword) {
        User user = dataService.find(identity);
        if (user.validatePassword(oldPassword)) {
            user.setPassword(newPassword);
            dataService.update(user);

        } else {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }

        return user;

    }

    @POST
    @Path("password/reset")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {

        try {
            User user = dataService.getUniqueWhere("passwordHash", request.getToken());
            if (user.getEmail().equalsIgnoreCase(request.getEmail()) && user.getUsername().equalsIgnoreCase(request.getUsername())) {
                user.setPassword(request.getNewPassword());
                dataService.update(user);
            }

            sendEmail("marc@yellowtwig.nl", "Takeoff - wachtwoord", "Je wachtwoord is gewijzigd.");
        } catch (Exception ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        ResetPasswordResponse resp = new ResetPasswordResponse();
        resp.setMessage("Het wachtwoord is gewijzigd");
        return resp;
    }

    @POST
    @Path("password/forgot")
    @Produces({"text/plain"})
    public String forgotPassword(@QueryParam("email") String email, @QueryParam("user") String username) {
        if (null == email || null == username) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        String message = "User, e-mail or combination not found";
        StringBuilder buffer = new StringBuilder();
        String hostname = "http://takeoff.yellowtwig.nl";
        String resetURL = "/idp/web/index.html#/account/resetpassword";

        String location = hostname + resetURL;
        User user = dataService.getUniqueWhere("email", email);
        if (user.getUsername().endsWith(username)) {
            buffer.append("Je hebt een nieuw wachtwoord aangevraagd.\n");
            buffer.append("Klik op onderstaande link om je wachtwoord opnieuw in te stellen.\n");
            location += "?username=" + username + "&token=" + user.getPasswordHash() + "&email=" + user.getEmail();
            buffer.append(location);

            sendEmail("marc@yellowtwig.nl", "reset", buffer.toString());
        } else {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }

        return message;
    }

    /**
     * http://javaeenotes.blogspot.nl/2010/04/using-javamail-api-with-glassfish-and.html
     *
     * @param to
     * @param subject
     * @param body
     */
    public void sendEmail(String to, String subject, String body) {

        MimeMessage message = new MimeMessage(mailSession);

        try {

            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));

            InternetAddress[] address = {new InternetAddress(to)};

            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject(subject);

            message.setSentDate(new Date());

            message.setText(body);

            Transport.send(message);

        } catch (MessagingException ex) {

            ex.printStackTrace();

        }

    }

}
