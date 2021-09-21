/*
 * Copyright 2021 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.teku.spec.logic.common.util;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.tuweni.bytes.Bytes32;
import tech.pegasys.teku.infrastructure.unsigned.UInt64;
import tech.pegasys.teku.spec.datastructures.execution.ExecutionPayload;
import tech.pegasys.teku.spec.executionengine.ExecutionEngineChannel;

public class ExecutionPayloadUtil {

  public ExecutionPayloadUtil() {}

  public boolean verifyExecutionStateTransition(
      ExecutionEngineChannel executionEngineChannel, ExecutionPayload executionPayload) {
    checkNotNull(executionEngineChannel);
    return executionEngineChannel.newBlock(executionPayload).join();
  }

  public void prepareExecutionPayload(
      ExecutionEngineChannel executionEngineChannel,
      Bytes32 parentHash,
      UInt64 timestamp,
      UInt64 payloadId) {
    executionEngineChannel.prepareBlock(parentHash, timestamp, payloadId).join();
  }

  public ExecutionPayload getExecutionPayload(
      ExecutionEngineChannel executionEngineChannel,
      Bytes32 parentHash,
      UInt64 timestamp,
      UInt64 payloadId) {
    return executionEngineChannel.assembleBlock(parentHash, timestamp).join();
  }
}
