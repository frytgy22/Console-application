CREATE TABLE IF NOT EXISTS txt_files(
  id INT auto_increment primary key NOT NULL,
  name varchar(255) NOT NULL UNIQUE,
  longest_word varchar(100) NOT NULL,
  shortest_word varchar(100) NOT NULL, 
  line_length INT NOT NULL,
  average_word_length DECIMAL(5,2) NOT NULL,
  duplication_of_words TEXT);

CREATE TABLE IF NOT EXISTS statistics_for_each_line(
  file_id INT NOT NULL,
  line TEXT NOT NULL,
  longest_word varchar(100) NOT NULL,
  shortest_word varchar(100) NOT NULL, 
  line_length INT NOT NULL,
  average_word_length DECIMAL(5,2) NOT NULL,
  duplication_of_words TEXT,
  FOREIGN KEY(file_id) REFERENCES txt_files(id));


DROP PROCEDURE IF EXISTS java2019.sp_add_txt_file;
DELIMITER $$
CREATE PROCEDURE java2019.sp_add_txt_file(
      IN `f_name` varchar(255),
      IN `f_longest_word` varchar(100),
      IN `f_shortest_word` varchar(100),
      IN `f_line_length` INT,
      IN `f_average_word_length` DECIMAL(5,2),
      IN `f_duplication_of_words` TEXT,
      OUT `f_id` INT)
  BEGIN 
  IF NOT EXISTS(SELECT * FROM txt_files
                WHERE txt_files.name = f_name)
            THEN
                INSERT INTO txt_files(`name`, `longest_word`, `shortest_word`,
                                      `line_length`, `average_word_length`, `duplication_of_words`) 

                VALUES(`f_name`,`f_longest_word`,`f_shortest_word`,`f_line_length`,
                       `f_average_word_length`,`f_duplication_of_words`);

       SET f_id = last_insert_id();
  ELSE
       SET f_id = 0;
  END IF;
  END $$
  DELIMITER ;
