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
package com.sematext.spm.client.tracing;

import java.util.Collections;
import java.util.Map;

import com.sematext.spm.client.tracing.agent.api.TransactionAccess;

public final class SPM {

  private SPM() { }

  private static boolean active = false;

  static {
    try {
      Class.forName("com.sematext.spm.client.tracing.agent.api.TransactionAccess");
      active = true;
    } catch (ClassNotFoundException e) {
      active = false;
    }
  }

  /**
   * Set name of current transaction.
   * @param name name
   */
  public static void setTransactionName(String name) {
    if (active) {
      TransactionAccess.setName(name);
    }
  }

  /**
   * Ignore current transaction from being recorded.
   */
  public static void ignoreTransaction() {
    if (active) {
      TransactionAccess.ignore();
    }
  }

  /**
   * Set custom parameter for transaction.
   * @param key key
   * @param value value
   */
  public static void setCustomTransactionParameter(String key, String value) {
    if (active) {
      TransactionAccess.setTransactionParameter(key, value);
    }
  }

  /**
   * Get current transaction custom parameters.
   * @return parameters
   */
  public static Map<String, String> getCustomTransactionParameters() {
    if (active) {
      return TransactionAccess.getTransactionParameters();
    }
    return Collections.emptyMap();
  }

  /**
   * Set custom parameter for current method.
   * @param key key
   * @param value value
   */
  public static void setCustomMethodParameter(String key, String value) {
    if (active) {
      TransactionAccess.setMethodParameter(key, value);
    }
  }

  /**
   * Get current custom method parameters.
   * @return parameters
   */
  public static Map<String, String> getCustomMethodParameters() {
    if (active) {
      return TransactionAccess.getMethodParameters();
    }
    return Collections.emptyMap();
  }

}