package com.zavteam.plugins.api;

import com.zavteam.plugins.packets.MessagePacket;

import java.util.List;

/**
 * Created by Zach on 4/26/14.
 */
public class MessagePacketEvent extends AutoPacketEvent {

    public List<String> getMessages() {
        return getAutoPacket().getMessages();
    }

    public MessagePacketEvent(MessagePacket messagePacket) {
        super(messagePacket);
    }

    public MessagePacket getAutoPacket() {
        return (MessagePacket) super.getAutoPacket();
    }

    /**
     *
     * @param messages The list of strings to be sent
     */
    public void setMessages(List<String> messages) {

    }

}