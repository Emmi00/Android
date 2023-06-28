/*
 * Copyright (c) 2023 DuckDuckGo
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

package com.duckduckgo.networkprotection.impl

import android.content.Context
import com.duckduckgo.app.global.extensions.getPrivateDnsServerName
import java.net.InetAddress

interface PrivateDnsProvider {
    fun getPrivateDns(): List<InetAddress>
}

internal class PrivateDnsProviderImpl constructor(
    private val context: Context,
) : PrivateDnsProvider {
    override fun getPrivateDns(): List<InetAddress> {
        return runCatching {
            context.getPrivateDnsServerName()?.let { InetAddress.getAllByName(it).toList() } ?: emptyList()
        }.getOrDefault(emptyList())
    }
}
