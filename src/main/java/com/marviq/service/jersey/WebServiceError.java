/*
 * Copyright (c) Marviq 2014
 */

package com.marviq.service.jersey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
@XmlType
@XmlRootElement(name="error")
public class WebServiceError {

    @XmlElement(name="msg")
    private String errorMessage;

    @XmlTransient
    private Integer statusCode;

    public WebServiceError() {}

    public WebServiceError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public WebServiceError(String errorMessage, Integer statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Writes the error and status code and message to the response
     * 
     * @param httpRequest
     * @param httpResponse
     * @throws RuntimeException 
     */
    public void write(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws RuntimeException {
        httpResponse.setStatus(this.statusCode);
        String requestContentType = httpRequest.getHeader("Accept");

        if (requestContentType == null) {
            requestContentType = httpRequest.getHeader("Content-Type");
            if (requestContentType == null) {
                requestContentType = "application/xml";
            }
        }

        requestContentType = requestContentType.toLowerCase();
        try {
            if (requestContentType.contains("xml")) {
                JAXBContext jaxbContext = JAXBContext.newInstance(WebServiceError.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(this, httpResponse.getOutputStream());
            } else if (requestContentType.contains("json")) {
//                JSONJAXBContext jaxbContext = new JSONJAXBContext(WebServiceError.class);
//                JSONMarshaller marshaller = jaxbContext.createJSONMarshaller();
//                marshaller.marshallToJSON(this, httpResponse.getOutputStream());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}