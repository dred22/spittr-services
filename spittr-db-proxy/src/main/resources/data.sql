BEGIN;
insert into reference
  (email, firstName, lastName, password, ts, username)
values
  ('test1@test.com', 'test1_firstName', 'test1_lastName', 'pass1', '2019-01-25', 'test1'),
  ('test2@test.com', 'test2_firstName', 'test2_lastName', 'pass2', '2019-01-25', 'test2'),
  ('test3@test.com', 'test3_firstName', 'test3_lastName', 'pass3', '2019-01-25', 'test3'),
  ('test4@test.com', 'test4_firstName', 'test4_lastName', 'pass4', '2019-01-25', 'test4'),
  ('test5@test.com', 'test5_firstName', 'test5_lastName', 'pass5', '2019-01-25', 'test5'),
  ('test6@test.com', 'test6_firstName', 'test6_lastName', 'pass6', '2019-01-25', 'test6'),
  ('test7@test.com', 'test7_firstName', 'test7_lastName', 'pass7', '2019-01-25', 'test7'),
  ('test8@test.com', 'test8_firstName', 'test8_lastName', 'pass8', '2019-01-25', 'test8'),
  ('test9@test.com', 'test9_firstName', 'test9_lastName', 'pass9', '2019-01-25', 'test9'),
  ('test10@test.com', 'test10_firstName', 'test10_lastName', 'pass10', '2019-01-25', 'test10'),
  ('test11@test.com', 'test11_firstName', 'test11_lastName', 'pass11', '2019-01-25', 'test11'),
  ('test12@test.com', 'test12_firstName', 'test12_lastName', 'pass12', '2019-01-25', 'test12');

insert into
  users (username, password, authority, enabled)
values
  ('user','$2a$10$Z00qRR286FPYsfNe6a/Ehezhpgi00UGLWVD8CEVh13jQ2WDfkm.m6', 'ROLE_USER', true),
  ('admin','$2a$10$SOM2NgjndVdYzGD/u9Iqg.2dGwuG8EXCx87eEMEk03VcZ8zOs8z3u','ROLE_ADMIN', true);

COMMIT;