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

package com.antgroup.geaflow.example.graph.statical;

import com.antgroup.geaflow.common.config.Configuration;
import com.antgroup.geaflow.env.EnvironmentFactory;
import com.antgroup.geaflow.example.base.BaseTest;
import com.antgroup.geaflow.example.graph.statical.compute.clustercoefficient.ClusterCoefficient;
import com.antgroup.geaflow.pipeline.IPipelineResult;
import org.testng.annotations.Test;

public class ClusterCoefficientTest extends BaseTest {

    @Test
    public void test() throws Exception {
        environment = EnvironmentFactory.onLocalEnvironment();
        Configuration configuration = environment.getEnvironmentContext().getConfig();
        configuration.putAll(config);

        IPipelineResult result = ClusterCoefficient.submit(environment);
        if (!result.isSuccess()) {
            throw new Exception("execute failed");
        }
        ClusterCoefficient.validateResult();
    }
}
