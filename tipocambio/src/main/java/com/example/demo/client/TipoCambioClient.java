package com.example.demo.client;

import com.example.demo.model.TipoCambio;
import com.example.demo.repository.TipoCambioRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
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

    public String obtenerYGuardarTipoCambio() {
        String tipoCambioXML = obtenerTipoCambio();
        guardarTipoCambio(tipoCambioXML);
        return "Tipo de cambio guardado correctamente";
    }

    public String obtenerTipoCambio() {
        String requestPayload =
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "   <soap:Body>" +
                "       <TipoCambioDiaString xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />" +
                "   </soap:Body>" +
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

    private void guardarTipoCambio(String xmlResponse) {
        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setXmlResponse(xmlResponse);
        tipoCambio.setTimestamp(LocalDateTime.now());
        tipoCambioRepository.save(tipoCambio);
    }

    @Scheduled(cron = "0 0 */4 * * *")
    public void obtenerYGuardarTipoCambioAutomatico() {
        obtenerYGuardarTipoCambio();
    }
}
