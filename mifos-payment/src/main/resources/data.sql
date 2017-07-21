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
VALUES ('mifos', 'Test Mfi', NOW(), 1);

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
-- Add a config category
--
INSERT INTO category(category_name, last_modified_dtm, last_modified_by_id)
VALUES ('api', NOW(), 1);

--
-- Add a config category
--
INSERT INTO category(category_name, last_modified_dtm, last_modified_by_id)
VALUES ('api-token', NOW(), 1);


--
-- API configuration for Beyonic.
--
INSERT INTO configuration (reference_id, reference_type, config_name, config_value,
                           config_category, last_modified_dtm, last_modified_by_id)
VALUES (1, 'mmp', 'endPoint', 'https://app.beyonic.com/api', 1, NOW(), 1);

INSERT INTO configuration (reference_id, reference_type, config_name, config_value,
                           config_category, last_modified_dtm, last_modified_by_id)
VALUES (1, 'mmp', 'api-token', '71650dd01ae0fd4e3efdfc9764f9d12308b7244f', 2, NOW(), 1);

--
-- Add category for statuses
--
INSERT INTO category (category_name, last_modified_dtm, last_modified_by_id)
VALUES ('payment_api_status', NOW(), 2);

INSERT INTO category (category_name, last_modified_dtm, last_modified_by_id)
VALUES ('system_status', NOW(), 2);

INSERT INTO category (category_name, last_modified_dtm, last_modified_by_id)
VALUES ('payment_status', NOW(), 2);

