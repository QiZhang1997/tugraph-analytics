/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.antgroup.geaflow.dsl.runtime.plan.converters;

import com.antgroup.geaflow.dsl.runtime.plan.PhysicConvention;
import com.antgroup.geaflow.dsl.runtime.plan.PhysicFilterRelNode;
import org.apache.calcite.plan.Convention;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.convert.ConverterRule;
import org.apache.calcite.rel.core.Filter;

public class ConvertFilterRule extends ConverterRule {

    public static final ConverterRule INSTANCE = new ConvertFilterRule();

    private ConvertFilterRule() {
        super(Filter.class, Convention.NONE,
            PhysicConvention.INSTANCE, ConvertFilterRule.class.getSimpleName());
    }

    @Override
    public RelNode convert(RelNode rel) {
        Filter filter = (Filter) rel;
        RelTraitSet relTraitSet = filter.getTraitSet().replace(PhysicConvention.INSTANCE);

        RelNode convertedInput = convert(filter.getInput(),
            filter.getInput().getTraitSet().replace(PhysicConvention.INSTANCE));

        return new PhysicFilterRelNode(filter.getCluster(), relTraitSet,
            convertedInput, filter.getCondition());
    }
}
