/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.identity.application.common.listener;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.core.model.IdentityEventListener;
import org.wso2.carbon.identity.core.util.IdentityCoreConstants;
import org.wso2.carbon.identity.core.util.IdentityUtil;
import org.wso2.carbon.user.core.listener.UserOperationEventListener;

import javax.cache.event.CacheEntryListener;

public abstract class AbstractCacheListener<K, V> implements CacheEntryListener<K, V>{

    /**
     * Return is listener enable
     *
     * @return enable/disable
     */
    public boolean isEnable() {
        IdentityEventListener identityEventListener = IdentityUtil.readEventListenerProperty
                (UserOperationEventListener.class.getName(), this.getClass().getName());

        if (identityEventListener == null) {
            return true;
        }

        if (StringUtils.isNotBlank(identityEventListener.getEnable())) {
            return Boolean.parseBoolean(identityEventListener.getEnable());
        } else {
            return true;
        }
    }

    /**
     * Return order of the listener
     * @return order id
     */
    public int getOrderId() {
        IdentityEventListener identityEventListener = IdentityUtil.readEventListenerProperty
                (UserOperationEventListener.class.getName(), this.getClass().getName());
        if (identityEventListener == null) {
            return IdentityCoreConstants.EVENT_LISTENER_ORDER_ID;
        }
        return identityEventListener.getOrder();
    }
}
