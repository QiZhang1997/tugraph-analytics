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

package com.antgroup.geaflow.dsl.optimize.rule;

import com.antgroup.geaflow.dsl.util.GQLRelUtil;
import org.apache.calcite.plan.RelOptRuleCall;
import org.apache.calcite.rel.core.Aggregate;
import org.apache.calcite.rel.core.Project;
import org.apache.calcite.rel.core.RelFactories;
import org.apache.calcite.rel.rules.AggregateProjectMergeRule;
import org.apache.calcite.tools.RelBuilderFactory;

public class GQLAggregateProjectMergeRule extends AggregateProjectMergeRule {

    public static final GQLAggregateProjectMergeRule INSTANCE =
        new GQLAggregateProjectMergeRule(Aggregate.class,
            Project.class, RelFactories.LOGICAL_BUILDER);

    public GQLAggregateProjectMergeRule(Class<? extends Aggregate> aggregateClass,
                                        Class<? extends Project> projectClass,
                                        RelBuilderFactory relBuilderFactory) {
        super(aggregateClass, projectClass, relBuilderFactory);
    }

    @Override
    public boolean matches(RelOptRuleCall call) {
        Project project = call.rel(1);
        if (GQLRelUtil.isGQLMatchRelNode(project.getInput())) {
            return false;
        }
        return super.matches(call);
    }
}
