# springBootSecurityDemo
This is Spring boot sample with Security

##db insert sql
```sql
INSERT INTO "main"."user" ("id", "age", "authorities", "email_address", "locked", "name", "password", "role") VALUES ('2', 'test', 'ADMIN', 'admintest', 0, 'admintest', '$2a$10$RtQ9UHFTXDBjxn0.nRFGO.7qvrrzljzdT8GHlx.ZuVLD9L7j9Q1XC', 'ADMIN');
INSERT INTO "main"."user" ("id", "age", "authorities", "email_address", "locked", "name", "password", "role") VALUES ('1', '25', 'USER', 'test', 0, 'test', '$2a$10$RtQ9UHFTXDBjxn0.nRFGO.7qvrrzljzdT8GHlx.ZuVLD9L7j9Q1XC', 'USER');
```