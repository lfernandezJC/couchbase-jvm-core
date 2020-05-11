/*
 * Copyright (c) 2020 Couchbase, Inc.
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

import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceReportingEventHandler;

abstract public class SequenceAwareEventHandler<T extends TimedEvent> implements SequenceReportingEventHandler<T> {
    private Sequence sequence;
    private Listener listener;

    public interface Listener {
        void onEvent(long sequence, long delay, boolean endOfBatch);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void setSequenceCallback(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void onEvent(T event, long sequence, boolean endOfBatch) throws Exception {
        if (listener != null) {
            try {
                long delay = System.nanoTime() - event.getTimestamp();
                listener.onEvent(sequence, delay, endOfBatch);
            } catch (Throwable t) {}
        }
    }

    public Sequence getSequence() {
        return sequence;
    }
}