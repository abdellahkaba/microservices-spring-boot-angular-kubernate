package com.isi.microservices.notification.service;


import com.isi.microservices.order.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender javaMailSender;
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);



    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {

        log.info("J'ai reçu un sujet de commande de message {}", orderPlacedEvent);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("abdallahkaba98@gmail.com");
            messageHelper.setTo(orderPlacedEvent.getEmail());
            messageHelper.setSubject(String.format("Votre commande avec le numéro de commande %s a été passée avec succès", orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format(
                    "<p>Salut,</p><p>Votre commande portant le numéro de commande %s a maintenant été passée avec succès.</p><p>Cordialement,<br/>Boutique de Bonfi saoudi</p>",
                    orderPlacedEvent.getOrderNumber(),
                    orderPlacedEvent.getSkuCode(),
                    orderPlacedEvent.getPrice(),
                    orderPlacedEvent.getQuantity(),
                    orderPlacedEvent.getFirstName(),
                    orderPlacedEvent.getLastName(),
                    orderPlacedEvent.getEmail()
            ), true);
        };

        try {
            javaMailSender.send(messagePreparator);
            log.info("E-mail de notification de commande envoyé!!");
        } catch (MailException e) {
            log.error("Une exception s'est produite lors de l'envoi du courrier", e);
            throw new RuntimeException("Une exception s'est produite lors de l'envoi d'un courrier à " + orderPlacedEvent.getEmail(), e);
        }
    }
}
