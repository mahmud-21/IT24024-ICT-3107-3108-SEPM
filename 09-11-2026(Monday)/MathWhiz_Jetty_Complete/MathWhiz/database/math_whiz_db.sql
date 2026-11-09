-- MySQL 8+. Execute before starting with DB_URL pointing to MySQL.
CREATE DATABASE IF NOT EXISTS math_whiz_db CHARACTER SET utf8mb4;
USE math_whiz_db;
CREATE TABLE IF NOT EXISTS game_scores (
 id INT PRIMARY KEY AUTO_INCREMENT,
 player_name VARCHAR(100) NOT NULL,
 score INT NOT NULL CHECK (score BETWEEN 0 AND 10),
 difficulty VARCHAR(20) NOT NULL,
 played_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Application uses PreparedStatement to bind the INSERT parameters:
-- INSERT INTO game_scores (player_name, score, difficulty) VALUES (?, ?, ?);
SELECT * FROM game_scores ORDER BY score DESC, played_at ASC, id ASC LIMIT 10;
