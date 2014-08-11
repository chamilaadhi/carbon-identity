/*
 *Copyright (c) 2005-2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *WSO2 Inc. licenses this file to you under the Apache License,
 *Version 2.0 (the "License"); you may not use this file except
 *in compliance with the License.
 *You may obtain a copy of the License at
 *
 *http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing,
 *software distributed under the License is distributed on an
 *"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *KIND, either express or implied.  See the License for the
 *specific language governing permissions and limitations
 *under the License.
 */

package org.wso2.carbon.identity.application.common.model;

import java.io.Serializable;
import java.util.*;

import org.apache.axiom.om.OMElement;

public class InboundAuthenticationRequestConfig implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4619706374988196634L;

    private String inboundAuthKey;
    private String inboundAuthType;
    private Property[] properties = new Property[0];

    /**
     * 
     * @return
     */
    public String getInboundAuthKey() {
        return inboundAuthKey;
    }

    /**
     * 
     * @param inboundAuthKey
     */
    public void setInboundAuthKey(String inboundAuthKey) {
        this.inboundAuthKey = inboundAuthKey;
    }

    /**
     * 
     * @return
     */
    public String getInboundAuthType() {
        return inboundAuthType;
    }

    /**
     * 
     * @param inboundAuthType
     */
    public void setInboundAuthType(String inboundAuthType) {
        this.inboundAuthType = inboundAuthType;
    }

    /**
     * 
     * @return
     */
    public Property[] getProperties() {
        return properties;
    }

    /**
     * 
     * @param properties
     */
    public void setProperties(Property[] properties) {
        if(properties == null){
            return;
        }
        Set<Property> propertySet = new HashSet<Property>(Arrays.asList(properties));
        this.properties = propertySet.toArray(new Property[propertySet.size()]);
    }

    /*
     * <InboundAuthenticationRequestConfig> <InboundAuthKey></InboundAuthKey>
     * <InboundAuthType></InboundAuthType> <Properties></Properties>
     * </InboundAuthenticationRequestConfig>
     */
    public static InboundAuthenticationRequestConfig build(
            OMElement inboundAuthenticationRequestConfigOM) {

        if (inboundAuthenticationRequestConfigOM == null) {
            return null;
        }

        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig;
        inboundAuthenticationRequestConfig = new InboundAuthenticationRequestConfig();

        Iterator<?> members = inboundAuthenticationRequestConfigOM.getChildElements();

        while (members.hasNext()) {
            OMElement member = (OMElement) members.next();

            if (member.getLocalName().equalsIgnoreCase("InboundAuthKey")) {
                inboundAuthenticationRequestConfig.setInboundAuthKey(member.getText());
            } else if (member.getLocalName().equalsIgnoreCase("InboundAuthType")) {
                inboundAuthenticationRequestConfig.setInboundAuthType(member.getText());
            } else if (member.getLocalName().equalsIgnoreCase("Properties")) {
                Iterator<?> propertiesIter = member.getChildElements();
                ArrayList<Property> propertiesArrList = new ArrayList<Property>();

                if (propertiesIter != null) {
                    while (propertiesIter.hasNext()) {
                        OMElement propertiesElement = (OMElement) (propertiesIter.next());
                        Property prop = Property.build(propertiesElement);
                        if (prop != null) {
                            propertiesArrList.add(prop);
                        }
                    }
                }

                if (propertiesArrList.size() > 0) {
                    Property[] propertiesArr = propertiesArrList.toArray(new Property[0]);
                    inboundAuthenticationRequestConfig.setProperties(propertiesArr);
                }
            }
        }
        return inboundAuthenticationRequestConfig;
    }

}