package com.expanse.app.payoneer.model;

import java.net.URL;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is designed to hold list of payment networks available for particular transaction based on provided information and result of
 * initialized payment session.
 * <p>
 * An instance of this object is returned as a result of new <code>Transaction</code> initialization, or during list status update via GET
 * method.
 */
@Getter
@Setter
public class ListResult {
	/** Simple API, always present */
	private Map<String, URL> links;
	/** Simple API, always present */
	private String resultInfo;
	/** Simple API, optional, always present in response to action (POST, UPDATE) */
	private Interaction interaction;
	/** Simple API, optional, always present in native LIST */
	private Networks networks;
	/** LIST type based on operation of next referred actions, could be one of CHARGE, PRESET, PAYOUT, UPDATE. */
	private String operationType;
	/** The style object passed in the transaction. */
	private Style style;
	/** Payment information, optional */
	private Payment payment;
	/** Integration type used when creating the LIST session, always present */
	private String integrationType;
	/** Simple API, always present */
	private Map<String, String> identification;
}
