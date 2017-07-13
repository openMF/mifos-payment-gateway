--
-- Enable creation of foreign keys even if their
-- corresponding tables does not exist.
--
set FOREIGN_KEY_CHECKS=0;

--
--Add test mfi to MFI table
--
INSERT INTO MFI (mfi_name, description, last_modified_dtm, last_modified_by_id)
VALUES ('mifos', 'Test Mfi', NOW(), 1);

--
-- Add Beyonic as a MMP
--
INSERT INTO MMP (provider_name, currency_code, last_modified_dtm, last_modified_by_id)
VALUES ('Beyonic', 'KES', NOW(), 1);

--
--Add Beyonic as a gateway user
--
INSERT INTO gateway_users (username, password, mmp_id)
VALUES ('beyonic', 'beyonic', 1);

--
--Add mifos as super user
--
INSERT INTO gateway_users (username, password, mfi_id, mifos_user_id, role_id)
VALUES ('mifos', 'password', 1, 1, 1);

--
--Add a config category
--
INSERT INTO category(category_name, last_modified_dtm, last_modified_by_id)
VALUES ('api', NOW(), 1);

--
--Add a config category
--
INSERT INTO category(category_name, last_modified_dtm, last_modified_by_id)
VALUES ('api-token', NOW(), 1);


--
--API configuration for Beyonic.
--
INSERT INTO configuration (reference_id, reference_type, config_name, config_value,
config_category, last_modified_dtm, last_modified_by_id)
VALUES (1, 'MMP', 'endPoint', 'https://app.beyonic.com/api', 1, NOW(), 1);

INSERT INTO configuration (reference_id, reference_type, config_name, config_value,
config_category, last_modified_dtm, last_modified_by_id)
VALUES (1, 'MMP', 'api-token', '', 2, NOW(), 1);

