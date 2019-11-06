DROP TABLE IF EXISTS tbl_contact_details;
DROP TABLE IF EXISTS tbl_contact;

CREATE TABLE tbl_contact (
  contact_id BIGINT(20) AUTO_INCREMENT  PRIMARY KEY,
  contact_name VARCHAR(250) NOT NULL );
  
CREATE TABLE tbl_contact_details (
  contact_details_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  contact_type VARCHAR(250) NOT NULL,
  contact_value VARCHAR(250) NOT NULL,
  contact_id BIGINT(20) NOT NULL,
  FOREIGN KEY(contact_id) REFERENCES tbl_contact );