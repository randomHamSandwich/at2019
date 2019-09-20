package com.example.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import com.example.beans.AID;

public class ACLMessage  implements Serializable{
	private Performative performative;
	private AID sender;
	private AID[] receavers;
	private AID replyTo;
	private String contetnt;
	private Object contentObject;
	private HashMap<String, Object> userArgs;
	private String language;
	private String encoding;
	private String ontology;
	private String protocol;
	private String conversationId;
	private String replyWith;
	private String inreplyTo;
	private Long replyBy;

	public ACLMessage() {
		// TODO Auto-generated constructor stub
	}
	
	@Deprecated
	public ACLMessage(ACLMessage msgCopy, AID agentID) {
		this.performative = msgCopy.performative;
		this.sender = msgCopy.sender;
		this.receavers = new AID[] { agentID };
		this.replyTo = msgCopy.replyTo;
		this.contetnt = msgCopy.contetnt;
		this.contentObject = msgCopy.contentObject;
		this.userArgs = msgCopy.userArgs;
		this.language = msgCopy.language;
		this.encoding = msgCopy.encoding;
		this.ontology = msgCopy.ontology;
		this.protocol = msgCopy.protocol;
		this.conversationId = msgCopy.conversationId;
		this.replyWith = msgCopy.replyWith;
		this.inreplyTo = msgCopy.inreplyTo;
		this.replyBy = msgCopy.replyBy;
	}

	public Performative getPerformative() {
		return performative;
	}

	public void setPerformative(Performative performative) {
		this.performative = performative;
	}

	public AID getSender() {
		return sender;
	}

	public void setSender(AID sender) {
		this.sender = sender;
	}

	public AID[] getReceavers() {
		return receavers;
	}

	public void setReceavers(AID[] receavers) {
		this.receavers = receavers;
	}

	public AID getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(AID replyTo) {
		this.replyTo = replyTo;
	}

	public String getContetnt() {
		return contetnt;
	}

	public void setContetnt(String contetnt) {
		this.contetnt = contetnt;
	}

	public Object getContentObject() {
		return contentObject;
	}

	public void setContentObject(Object contentObject) {
		this.contentObject = contentObject;
	}

	public HashMap<String, Object> getUserArgs() {
		return userArgs;
	}

	public void setUserArgs(HashMap<String, Object> userArgs) {
		this.userArgs = userArgs;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getOntology() {
		return ontology;
	}

	public void setOntology(String ontology) {
		this.ontology = ontology;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getReplyWith() {
		return replyWith;
	}

	public void setReplyWith(String replyWith) {
		this.replyWith = replyWith;
	}

	public String getInreplyTo() {
		return inreplyTo;
	}

	public void setInreplyTo(String inreplyTo) {
		this.inreplyTo = inreplyTo;
	}

	public Long getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(Long replyBy) {
		this.replyBy = replyBy;
	}

}
