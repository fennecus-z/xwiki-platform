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
package org.xwiki.notifications.filters.expression;

import org.xwiki.notifications.filters.expression.generics.AbstractBinaryOperatorNode;
import org.xwiki.notifications.filters.expression.generics.AbstractOperatorNode;
import org.xwiki.stability.Unstable;

/**
 * Define a AND condition in a filtering expression.
 *
 * @version $Id$
 * @since 9.7RC1
 */
@Unstable
public final class AndNode extends AbstractBinaryOperatorNode
{
    /**
     * Constructs a new AND node.
     *
     * @param leftOperand the left operand
     * @param rightOperand the right operand
     */
    public AndNode(AbstractOperatorNode leftOperand, AbstractOperatorNode rightOperand)
    {
        super(leftOperand, rightOperand);
    }

    @Override
    public boolean equals(Object o)
    {
        return (o instanceof AndNode && super.equals(o));
    }

    @Override
    public int hashCode()
    {
        return this.getClass().getTypeName().hashCode() * 571 + super.hashCode();
    }

    @Override
    public String toString()
    {
        return String.format("(%s AND %s)", getLeftOperand(), getRightOperand());
    }
}
