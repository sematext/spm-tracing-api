/**
 * Copyright 2015 Sematext Group, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sematext.spm.client.tracing.agent.api;

import java.util.Collections;
import java.util.Map;

/**
 * Stub.
 */
public final class TransactionAccess {

  private TransactionAccess() { }

  public static void setName(String name) {
    /* stub */
  }

  public static void ignore() {
    /* stub */
  }

  public static void setTransactionParameter(String key, String value) {
    /* stub */
  }

  public static Map<String, String> getTransactionParameters() {
    return Collections.emptyMap(); /* stub */
  }

  public static void setMethodParameter(String key, String value) {
    /* stub */
  }

  public static Map<String, String> getMethodParameters() {
    return Collections.emptyMap(); /* stub */
  }
}
