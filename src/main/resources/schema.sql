CREATE TABLE staff (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL,
    email       VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT TRUE 
);

CREATE TABLE shifts (
	
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    staff_id      BIGINT        NOT NULL,
    work_date     DATE          NOT NULL,
    start_time   TIME 			NOT NULL,
    end_time     TIME     		NOT NULL,
    note      VARCHAR(500),
    
    CONSTRAINT fk_shifts_staff
        FOREIGN KEY (staff_id) REFERENCES staff(id)
);