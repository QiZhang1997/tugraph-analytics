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

CREATE TABLE IF NOT EXISTS edge_join_vertex_002_result (
	s_id bigint,
	c_id bigint,
	ts bigint,
  c_id0 bigint,
  c_name varchar
) WITH (
	type='file',
	geaflow.dsl.file.path='${target}'
);

USE GRAPH g_student;

INSERT INTO edge_join_vertex_002_result
SELECT sc.srcId, sc.targetId, sc.ts, c.id, c.name
FROM course c, selectCourse sc
WHERE c.id = sc.targetId AND srcId < 1004
ORDER BY srcId, targetId;