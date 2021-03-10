/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.impl.protocol.codec;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.Generated;
import com.hazelcast.client.impl.protocol.codec.builtin.*;
import com.hazelcast.client.impl.protocol.codec.custom.*;

import javax.annotation.Nullable;

import static com.hazelcast.client.impl.protocol.ClientMessage.*;
import static com.hazelcast.client.impl.protocol.codec.builtin.FixedSizeTypesCodec.*;

/*
 * This file is auto-generated by the Hazelcast Client Protocol Code Generator.
 * To change this file, edit the templates or the protocol
 * definitions on the https://github.com/hazelcast/hazelcast-client-protocol
 * and regenerate it.
 */

/**
 * Returns current lock ownership status of the given FencedLock instance.
 */
@Generated("711575cd59a33ce71a8f2e49b2b350e7")
public final class FencedLockGetLockOwnershipCodec {
    //hex: 0x070400
    public static final int REQUEST_MESSAGE_TYPE = 459776;
    //hex: 0x070401
    public static final int RESPONSE_MESSAGE_TYPE = 459777;
    private static final int REQUEST_INITIAL_FRAME_SIZE = PARTITION_ID_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int RESPONSE_FENCE_FIELD_OFFSET = RESPONSE_BACKUP_ACKS_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;
    private static final int RESPONSE_LOCK_COUNT_FIELD_OFFSET = RESPONSE_FENCE_FIELD_OFFSET + LONG_SIZE_IN_BYTES;
    private static final int RESPONSE_SESSION_ID_FIELD_OFFSET = RESPONSE_LOCK_COUNT_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int RESPONSE_THREAD_ID_FIELD_OFFSET = RESPONSE_SESSION_ID_FIELD_OFFSET + LONG_SIZE_IN_BYTES;
    private static final int RESPONSE_INITIAL_FRAME_SIZE = RESPONSE_THREAD_ID_FIELD_OFFSET + LONG_SIZE_IN_BYTES;

    private FencedLockGetLockOwnershipCodec() {
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class RequestParameters {

        /**
         * CP group id of this FencedLock instance
         */
        public com.hazelcast.cp.internal.RaftGroupId groupId;

        /**
         * Name of this FencedLock instance
         */
        public java.lang.String name;
    }

    public static ClientMessage encodeRequest(com.hazelcast.cp.internal.RaftGroupId groupId, java.lang.String name) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        clientMessage.setRetryable(true);
        clientMessage.setOperationName("FencedLock.GetLockOwnership");
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[REQUEST_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, REQUEST_MESSAGE_TYPE);
        encodeInt(initialFrame.content, PARTITION_ID_FIELD_OFFSET, -1);
        clientMessage.add(initialFrame);
        RaftGroupIdCodec.encode(clientMessage, groupId);
        StringCodec.encode(clientMessage, name);
        return clientMessage;
    }

    public static FencedLockGetLockOwnershipCodec.RequestParameters decodeRequest(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        RequestParameters request = new RequestParameters();
        //empty initial frame
        iterator.next();
        request.groupId = RaftGroupIdCodec.decode(iterator);
        request.name = StringCodec.decode(iterator);
        return request;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class ResponseParameters {

        /**
         * Fence token of the lock
         */
        public long fence;

        /**
         * Reenterant lock count
         */
        public int lockCount;

        /**
         * Id of the session that holds the lock
         */
        public long sessionId;

        /**
         * Id of the thread that holds the lock
         */
        public long threadId;
    }
    public static ClientMessage encodeResponse(long fence, int lockCount, long sessionId, long threadId) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[RESPONSE_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, RESPONSE_MESSAGE_TYPE);
        encodeLong(initialFrame.content, RESPONSE_FENCE_FIELD_OFFSET, fence);
        encodeInt(initialFrame.content, RESPONSE_LOCK_COUNT_FIELD_OFFSET, lockCount);
        encodeLong(initialFrame.content, RESPONSE_SESSION_ID_FIELD_OFFSET, sessionId);
        encodeLong(initialFrame.content, RESPONSE_THREAD_ID_FIELD_OFFSET, threadId);
        clientMessage.add(initialFrame);

        return clientMessage;
    }

    public static FencedLockGetLockOwnershipCodec.ResponseParameters decodeResponse(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        ResponseParameters response = new ResponseParameters();
        ClientMessage.Frame initialFrame = iterator.next();
        response.fence = decodeLong(initialFrame.content, RESPONSE_FENCE_FIELD_OFFSET);
        response.lockCount = decodeInt(initialFrame.content, RESPONSE_LOCK_COUNT_FIELD_OFFSET);
        response.sessionId = decodeLong(initialFrame.content, RESPONSE_SESSION_ID_FIELD_OFFSET);
        response.threadId = decodeLong(initialFrame.content, RESPONSE_THREAD_ID_FIELD_OFFSET);
        return response;
    }

}
