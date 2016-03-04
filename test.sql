
CREATE TABLE testjson  ( id INTEGER, doc JSONB);

create or replace function random_string(length integer) returns text as
  $$
declare
  chars text[] := '{a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z}';
  result text := '';
  i integer := 0;
begin
  if length < 0 then
    raise exception 'Given length cannot be less than 0';
  end if;
  for i in 1..length loop
    result := result || chars[1+random()*(array_length(chars, 1)-1)];
  end loop;
  return result;
end;
$$ language plpgsql;


DO
$do$
DECLARE

    result text ;
    ids INT := 0;
  count INT :=0;

BEGIN
   FOR i in 0 .. 1000000
   LOOP
      count := count +1;
     IF count > 15 THEN
       ids := ids+1;
       count := 0;
     END IF;

     result :=  '{ "message": { "SenderType": "FME", "SenderName": "", "SenderDescription": "", "TaskKey": '|| ids || ', "TaskPriority": 4, "TaskUtcCreationDate": "2014-06-14T18:00:00.'|| i || '", "TaskUtcDueDate": "2014-06-16T18:00:00.'|| i || '", "ActivityKey": "", "ActivityName": "", "ActivityDefaultRunMode": 0, "ProcessKey": "", "ProcessName": "", "RequesterKeyLevel1": "", "RequesterKeyLevel2": "", "RequesterKeyLevel3": "", "RequesterKeyLevel4": "", "RequesterNameLevel1": "", "RequesterNameLevel2": "", "RequesterNameLevel3": "", "RequesterNameLevel4": "", "Resources": [ { "Description": "' || random_string(4) || '", "Key": "resource1" }, { "Description": "' || random_string(4) || '", "Key": "resource2" } ], "Metadata": [ { "Key": "key1", "Value": "' || random_string(4) || '" }, { "Key": "key2", "Value": "' || random_string(4) || '" } ], "UtcEventDate": "2014-06-16T17:19:00.'|| i || '" } }';
     INSERT INTO testjson VALUES (i, result::json);
   END LOOP;
END
$do$;


create index testjson_message_task_key_gin on testjson (((doc->'message'->>'TaskKey')::INTEGER));

select * from testjson where doc @> '{ "message": { "Metadata": [{ "Key": "key1", "Value": "bugl"}]}}';

SELECT count(*)
FROM testjson
WHERE doc @> '{"message" : {"TaskKey" : 231321}}' ;

DELETE from testjson;


CREATE INDEX metadata_idx ON message USING gin (metadata);