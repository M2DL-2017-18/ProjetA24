package fr.m2dl.infra;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Describe a message send between agent.
 * @author Infra core team
 * @since 22-02-2018
 */
public class Message<P> {
    private LinkedList<UUID> recipients;
    private UUID sender;
    private P payload;

    public Message(UUID sender, LinkedList<UUID> recipients, P payload) {
        this.sender = sender;
        this.recipients = recipients;
        this.payload = payload;
    }

    /**
     * Get the list of UUID agents recipient.
     * @return the recipients UUID
     */
    public LinkedList<UUID> getRecipients() {
        return this.recipients;
    }

    /**
     * Get the UUID of the agent sender.
     * @return UUID of the sencer
     */
    public UUID getSender() {
        return this.sender;
    }

    /**
     * Get the payload message.
     * @return payload
     */
    public P getPaylaod() {
        return this.payload;
    }
}