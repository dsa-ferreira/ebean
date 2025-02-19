-- Migrationscripts for ebean unittest
-- apply alter tables
alter table "table" drop column textfield;
alter table "table" drop column textfield2;
alter table migtest_ckey_detail drop column one_key;
alter table migtest_ckey_detail drop column two_key;
alter table migtest_ckey_parent drop column assoc_id;
alter table migtest_e_basic drop column new_string_field;
alter table migtest_e_basic drop column new_boolean_field;
alter table migtest_e_basic drop column new_boolean_field2;
alter table migtest_e_basic drop column progress;
alter table migtest_e_basic drop column new_integer;
alter table migtest_e_history2 drop column test_string2;
alter table migtest_e_history2 drop column test_string3;
alter table migtest_e_history2 drop column new_column;
alter table migtest_e_history5 drop column test_boolean;
alter table migtest_e_softdelete drop column deleted;
alter table migtest_oto_child drop column master_id;
-- apply post alter
drop table if exists drop_main cascade;
drop sequence if exists drop_main_seq;
drop table if exists drop_main_drop_ref_many cascade;
drop table if exists drop_ref_many cascade;
drop sequence if exists drop_ref_many_seq;
drop table if exists drop_ref_one cascade;
drop sequence if exists drop_ref_one_seq;
drop table if exists migtest_e_test_binary cascade;
drop sequence if exists migtest_e_test_binary_seq;
drop table if exists migtest_e_test_json cascade;
drop sequence if exists migtest_e_test_json_seq;
drop table if exists migtest_e_test_lob cascade;
drop sequence if exists migtest_e_test_lob_seq;
drop table if exists migtest_e_test_varchar cascade;
drop sequence if exists migtest_e_test_varchar_seq;
drop table if exists migtest_e_user cascade;
drop sequence if exists migtest_e_user_seq;
drop table if exists migtest_mtm_c_migtest_mtm_m cascade;
drop table if exists migtest_mtm_m_migtest_mtm_c cascade;
drop table if exists migtest_mtm_m_phone_numbers cascade;
