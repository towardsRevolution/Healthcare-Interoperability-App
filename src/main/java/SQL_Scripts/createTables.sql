create table patient_devices (p_id VARCHAR(255) NOT NULL, model VARCHAR(255), version VARCHAR(100), manufacturer VARCHAR(255)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table patient_care_plans (p_id VARCHAR(255) NOT NULL, description VARCHAR(255), last_modified_date DATETIME(0), status VARCHAR(100)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table patient_medication_orders (p_id VARCHAR(255) NOT NULL, date_written DATETIME(0), date_ended DATETIME(0), status VARCHAR(100)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table patient_conditions (p_id VARCHAR(255) NOT NULL, date_recorded DATETIME(0), clinical_status VARCHAR(100), notes VARCHAR(255)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table patient_allergies (p_id VARCHAR(255) NOT NULL, category VARCHAR(255), criticality VARCHAR(255), last_occurrence_date DATETIME(0)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table patient_details (patient_id VARCHAR(255) NOT NULL, date_of_birth DATETIME(0), gender VARCHAR(15), active BOOLEAN DEFAULT NULL, PRIMARY KEY(patient_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table patient_family_history (p_id VARCHAR(255) NOT NULL, name VARCHAR(255), gender VARCHAR(20), status VARCHAR(50), last_recorded_date DATETIME(0)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table users (user_id INT(11) NOT NULL AUTO_INCREMENT,registration_date DATETIME(0), name VARCHAR(100), email VARCHAR(100), role VARCHAR(100), PRIMARY KEY(user_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;