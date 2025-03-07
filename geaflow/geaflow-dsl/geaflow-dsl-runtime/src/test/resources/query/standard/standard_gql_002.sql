CREATE TABLE tbl_result (
  f1 bigint
) WITH (
	type='file',
	geaflow.dsl.file.path='${target}'
);

USE GRAPH movie_graph;

INSERT INTO tbl_result
MATCH (a:person WHERE a.id = 1)-[e1:know]->{2,6}(b:person) return b.id;