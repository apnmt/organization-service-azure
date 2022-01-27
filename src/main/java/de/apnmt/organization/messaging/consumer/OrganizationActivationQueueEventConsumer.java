package de.apnmt.organization.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.OrganizationActivationEventDTO;
import de.apnmt.organization.common.async.controller.OrganizationActivationEventConsumer;
import de.apnmt.organization.common.service.OrganizationService;
import de.apnmt.organization.messaging.QueueConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrganizationActivationQueueEventConsumer extends OrganizationActivationEventConsumer {

    private static final TypeReference<ApnmtEvent<OrganizationActivationEventDTO>> EVENT_TYPE = new TypeReference<>() {
    };

    private final Logger log = LoggerFactory.getLogger(OrganizationActivationQueueEventConsumer.class);

    private final ObjectMapper objectMapper;

    public OrganizationActivationQueueEventConsumer(OrganizationService organizationService, ObjectMapper objectMapper) {
        super(organizationService);
        this.objectMapper = objectMapper;
    }

    public void receiveEvent(String message) {
        try {
            this.log.info("Received event {} from queue {}", message, QueueConstants.ORGANIZATION_ACTIVATION_QUEUE);
            ApnmtEvent<OrganizationActivationEventDTO> event = this.objectMapper.readValue(message, EVENT_TYPE);
            super.receiveEvent(event);
        } catch (JsonProcessingException e) {
            this.log.error("Malformed message {} for queue {}. Event will be ignored.", message, QueueConstants.ORGANIZATION_ACTIVATION_QUEUE);
        }
    }

}
