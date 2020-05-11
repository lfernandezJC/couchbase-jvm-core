/*
 * Copyright (c) 2016 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.couchbase.client.core;

import com.couchbase.client.core.message.CouchbaseRequest;

/**
 * A pre allocated event which carries a {@link CouchbaseRequest} and associated information.
 *
 * @author Michael Nitschinger
 * @since 1.0
 */
public class RequestEvent extends TimedEvent {

    /**
     * Contains the current request.
     */
    private CouchbaseRequest request;

    /**
     * Set the new request as a payload for this event.
     *
     * @param request the request to override.
     * @return the {@link RequestEvent} for method chaining.
     */
    public RequestEvent setRequest(final CouchbaseRequest request) {
        this.request = request;
        updateTimestamp();
        return this;
    }

    public CouchbaseRequest getRequest() {
        return request;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestEvent{");
        sb.append("request=").append(request);
        sb.append('}');
        return sb.toString();
    }
}
