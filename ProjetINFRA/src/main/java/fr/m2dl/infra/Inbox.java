package fr.m2dl.infra;

import java.util.LinkedList;

/**
 * Contain all the messages produced by the agents during the run of their lifecycle.
 *
 * @author Infra core team
 * @since 22-02-2018
 */
public class Inbox {
    //TODO: Should manage the priority of message.
    // Solution: switch to PriorityQueue
    LinkedList<Message> queueOfMessages;

    public Inbox() {
        this.queueOfMessages = new LinkedList<Message>();
    }

    /**
     * Add a new message to the Inbox.
     * @param The message to put in the inbox
     */
    public <P> void addMessage(Message<P> m) {
        this.queueOfMessages.add(m);
    }

    /**
     * Get the list of messages available in the.
     * @param The message to put in the inbox
     */
    public LinkedList<Message> getMessagesInQueue() {
        return this.queueOfMessages;
    }

    /**
     * Drop all the messages in the inbox.
     */
    public void clear() {
        this.queueOfMessages.clear();
    }
}