
CREATE TABLE IF NOT EXISTS courses (
  id INT NOT NULL auto_increment,
  course varchar(100) NOT NULL,
  semester varchar(100) NOT NULL,
  status boolean NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
);

INSERT INTO courses (course, semester) VALUES
('OO Programming Java', 'Autumn'),
('Hypermedia', 'Spring');
