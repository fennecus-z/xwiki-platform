/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.notifications.filters.internal;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptContext;

import org.xwiki.notifications.filters.NotificationFilter;
import org.xwiki.notifications.filters.NotificationFilterDisplayer;
import org.xwiki.notifications.filters.NotificationFilterPreference;
import org.xwiki.script.ScriptContextManager;

/**
 * Abstract definition of {@link NotificationFilterDisplayer}. This definition provides basic methods for setting
 * up a proper velocity context before template rendering.
 *
 * @version $Id$
 * @since 9.7RC1
 */
public abstract class AbstractNotificationFilterDisplayer implements NotificationFilterDisplayer
{
    private static final String FILTER = "filter";

    private static final String FILTER_PREFERENCE = "filterPreference";

    private Map<String, Object> oldContextValues;

    private ScriptContext currentScriptContext;

    protected void setUpContext(ScriptContextManager scriptContextManager, NotificationFilter filter,
            NotificationFilterPreference preference)
    {
        currentScriptContext = scriptContextManager.getCurrentScriptContext();

        oldContextValues = new HashMap<>();

        oldContextValues.put(FILTER, currentScriptContext.getAttribute(FILTER));
        currentScriptContext.setAttribute(FILTER, filter, ScriptContext.ENGINE_SCOPE);

        oldContextValues.put(FILTER_PREFERENCE, currentScriptContext.getAttribute(FILTER_PREFERENCE));
        currentScriptContext.setAttribute(FILTER_PREFERENCE, preference, ScriptContext.ENGINE_SCOPE);
    }

    protected void cleanUpContext()
    {
        if (oldContextValues.get(FILTER) != null) {
            currentScriptContext.setAttribute(FILTER, oldContextValues.get(FILTER), ScriptContext.ENGINE_SCOPE);
        }

        if (oldContextValues.get(FILTER_PREFERENCE) != null) {
            currentScriptContext.setAttribute(FILTER_PREFERENCE, oldContextValues.get(FILTER_PREFERENCE),
                    ScriptContext.ENGINE_SCOPE);
        }
    }
}
