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

CREATE TABLE tbl_result (
  a_id bigint,
  weight double,
  b_id bigint,
  b_name varchar,
  b_out_cnt bigint,
  b_out_weight double
) WITH (
	type='file',
	geaflow.dsl.file.path='${target}'
);

USE GRAPH modern;

INSERT INTO tbl_result
SELECT
	a.id,
	e.weight,
	b.id,
	b.name,
	b.out_cnt,
	b.out_weight
FROM (
  MATCH (a:person WHERE id = 1)-[e]->(b)
  Where COUNT((b) ->(c) => c) >= 1
  Let b.out_cnt = COUNT((b) ->(c) => c),
  Let b.out_weight = SUM((b) -[e1]-> (c) => e1.weight)
  RETURN a, e, b
)