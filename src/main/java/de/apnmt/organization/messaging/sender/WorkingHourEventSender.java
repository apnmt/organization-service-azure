package de.apnmt.organization.messaging.sender;

import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.WorkingHourEventDTO;
import de.apnmt.common.sender.ApnmtEventSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkingHourEventSender implements ApnmtEventSender<WorkingHourEventDTO> {

    private final Logger log = LoggerFactory.getLogger(WorkingHourEventSender.class);

    private JmsTemplate jmsTemplate;

    public WorkingHourEventSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(String topic, ApnmtEvent<WorkingHourEventDTO> event) {
        this.log.info("Send event {} to topic {}", event, topic);
        jmsTemplate.convertAndSend(topic, event);
    }

}
