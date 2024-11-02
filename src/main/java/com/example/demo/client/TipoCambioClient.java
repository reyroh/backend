package com.example.demo.client;

import com.example.demo.model.TipoCambio;
import com.example.demo.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.time.LocalDateTime;

@Service
public class TipoCambioClient {

    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    //funcione inicial 
    public String obtenerYGuardarTipoCambioString() {
        String tipoCambioXML = obtenerTipoCambioString();
        guardarTipoCambio(tipoCambioXML);
        return tipoCambioXML;
    }
    public String obtenerYGuardarVariablesDisponibles() {
        String tipoCambioXML = obtenerVariablesDisponibles();
        guardarTipoCambio(tipoCambioXML);
        return tipoCambioXML;
    }
    public String obtenerYGuardarVariables() {
        String tipoCambioXML = obtenerVariables();
        guardarTipoCambio(tipoCambioXML);
        return tipoCambioXML;
    }
     public String obtenerYGuardarTipoCambio() {
        String tipoCambioXML = obtenerTipoCambio();
        guardarTipoCambio(tipoCambioXML);
        return tipoCambioXML;
    }
    
     //funcion de consumo SOAP
    public String obtenerTipoCambioString() {
        String requestPayload =
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>" +
                "    <TipoCambioDiaString xmlns=\"http://www.banguat.gob.gt/variables/ws/\"/>" +
                "  </soap:Body>" +
                "</soap:Envelope>";

        try {
            StringSource requestSource = new StringSource(requestPayload);
            StringWriter responseWriter = new StringWriter();

            webServiceTemplate.sendAndReceive(
                    "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx",
                    message -> {
                        SoapMessage soapMessage = (SoapMessage) message;
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(requestSource, soapMessage.getPayloadResult());
                        soapMessage.setSoapAction("http://www.banguat.gob.gt/variables/ws/TipoCambioDiaString");
                    },
                    message -> {
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(message.getPayloadSource(), new StreamResult(responseWriter));
                    }
            );

            return responseWriter.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el tipo de cambio", e);
        }
    }
    public String obtenerTipoCambio() {
        String requestPayload =
                 "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>" +
                "    <TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\"/>" +
                "  </soap:Body>" +
                "</soap:Envelope>";

        try {
            StringSource requestSource = new StringSource(requestPayload);
            StringWriter responseWriter = new StringWriter();

            webServiceTemplate.sendAndReceive(
                    "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx",
                    message -> {
                        SoapMessage soapMessage = (SoapMessage) message;
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(requestSource, soapMessage.getPayloadResult());
                        soapMessage.setSoapAction("http://www.banguat.gob.gt/variables/ws/TipoCambioDia");
                    },
                    message -> {
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(message.getPayloadSource(), new StreamResult(responseWriter));
                    }
            );

            return responseWriter.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el tipo de cambio", e);
        }
    }

    public String obtenerVariablesDisponibles() {
        String requestPayload =
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "  <soap:Body>\n" +
                "    <VariablesDisponibles xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />" +
                "  </soap:Body>" +
                "</soap:Envelope>";

        try {
            StringSource requestSource = new StringSource(requestPayload);
            StringWriter responseWriter = new StringWriter();

            webServiceTemplate.sendAndReceive(
                    "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx",
                    message -> {
                        SoapMessage soapMessage = (SoapMessage) message;
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(requestSource, soapMessage.getPayloadResult());
                        soapMessage.setSoapAction("http://www.banguat.gob.gt/variables/ws/VariablesDisponibles");
                    },
                    message -> {
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(message.getPayloadSource(), new StreamResult(responseWriter));
                    }
            );

            return responseWriter.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el tipo de cambio", e);
        }
    }

    public String obtenerVariables() {
        String requestPayload =
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "  <soap:Body>" +
                "    <Variables xmlns=\"http://www.banguat.gob.gt/variables/ws/\">" +
                "      <variable>5</variable>\n" +
                "    </Variables>" +
                "  </soap:Body>" +
                "</soap:Envelope>";

        try {
            StringSource requestSource = new StringSource(requestPayload);
            StringWriter responseWriter = new StringWriter();

            webServiceTemplate.sendAndReceive(
                    "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx",
                    message -> {
                        SoapMessage soapMessage = (SoapMessage) message;
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(requestSource, soapMessage.getPayloadResult());
                        soapMessage.setSoapAction("http://www.banguat.gob.gt/variables/ws/Variables");
                    },
                    message -> {
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(message.getPayloadSource(), new StreamResult(responseWriter));
                    }
            );

            return responseWriter.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el tipo de cambio", e);
        }
    }
    //Guarda el cambio
    private void guardarTipoCambio(String xmlResponse) {
        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setXmlResponse(xmlResponse);
        tipoCambio.setTimestamp(LocalDateTime.now());
        tipoCambioRepository.save(tipoCambio);
    }

    
    
    @Scheduled(cron = "0 0 */4 * * *")
    public void obtenerYGuardarVariablesDisponiblesAutomatico() {
        obtenerYGuardarVariablesDisponibles();
    }
    @Scheduled(cron = "0 0 */4 * * *")
    public void obtenerYGuardarVariablesAutomatico() {
        obtenerYGuardarVariables();
    }
    @Scheduled(cron = "0 0 */4 * * *")
    public void obtenerYGuardarTipoCambioStringAutomatico() {
        obtenerYGuardarTipoCambioString();
    }
    @Scheduled(cron = "0 0 */4 * * *")
    public void obtenerYGuardarTipoCambioAutomatico() {
        obtenerYGuardarTipoCambio();
    }
    
}
