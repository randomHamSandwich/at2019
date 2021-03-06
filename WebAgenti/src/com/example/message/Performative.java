/**
 * Licensed to the Apache Software Foundation (ASF) under one 
 * or more contributor license agreements. See the NOTICE file 
 * distributed with this work for additional information regarding 
 * copyright ownership. The ASF licenses this file to you under 
 * the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may 
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. 
 * 
 * See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package com.example.message;


/**
 * Represents FIPA ACL message performatives.
 * 
 * @author <a href="tntvteod@neobee.net">Teodor-Najdan Trifunov</a>
 */
public enum Performative {
	//@formatter:off
	ACCEPT_PROPOSAL,
	AGREE, 
	CANCEL, 
	CALL_FOR_PROPOSAL, 
	CONFIRM, 
	DISCONFIRM, 
	FAILURE, 
	INFORM, 
	INFORM_IF, 
	INFORM_REF, 
	NOT_UNDERSTOOD, 
	PROPAGATE, 
	PROPOSE, 
	PROXY, 
	QUERY_IF, 
	QUERY_REF,
	REFUSE, 
	REJECT_PROPOSAL, 
	REQUEST, 
	REQUEST_WHEN, 
	REQUEST_WHENEVER, 
	SUBSCRIBE,
	RESUME,
	INFORM_DONE,
	INFORM_RESULT
	//@formatter:on
}
