USE `mifos-payment-gateway`;
--
-- Enable creation of foreign keys even if their
-- corresponding tables does not exist.
--
set FOREIGN_KEY_CHECKS=0;

--
-- Add test mfi to mfi table
--
INSERT INTO mfi (mfi_name, description, last_modified_dtm, last_modified_by_id)
VALUES ('default', 'Test Mfi', NOW(), 1);

--
-- Add Beyonic as a mmp
--
INSERT INTO mmp (provider_name, currency_code, last_modified_dtm, last_modified_by_id)
VALUES ('Beyonic', 'BXC', NOW(), 1);

--
-- Add Beyonic as a gateway user
--
INSERT INTO gateway_users (username, password, mmp_id, mfi_id, mifos_user_id, role_id)
VALUES ('beyonic', 'beyonic', 1, NULL, NULL, NUll);

--
-- Add mifos as super user
--
INSERT INTO gateway_users (username, password, mmp_id, mfi_id, mifos_user_id, role_id)
VALUES ('mifos', 'password', NULL, 1, 1, 1);


--
-- API configuration for Beyonic.
--
INSERT INTO configuration (reference_id, reference_type, config_name, config_value,
                           config_category, last_modified_dtm, last_modified_by_id)
VALUES (1, 'MMP', 'callback_url', 'http://localhost:8040/', 1, NOW(), 1);


--
-- Add category for statuses
--
INSERT INTO category (category_name, last_modified_dtm, last_modified_by_id)
VALUES ('mmp_category', NOW(), 2);

INSERT INTO category (category_name, last_modified_dtm, last_modified_by_id)
VALUES ('fineract_category', NOW(), 2);

INSERT INTO category (category_name, last_modified_dtm, last_modified_by_id)
VALUES ('gateway_category', NOW(), 2);

