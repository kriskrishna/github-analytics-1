CREATE TABLE issues (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) not null,
  repository VARCHAR(255) not null
)