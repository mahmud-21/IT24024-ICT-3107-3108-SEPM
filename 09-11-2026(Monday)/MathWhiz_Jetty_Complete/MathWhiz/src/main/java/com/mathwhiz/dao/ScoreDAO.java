package com.mathwhiz.dao;
import com.mathwhiz.model.GameScore;
import java.sql.*;
import java.util.*;
public class ScoreDAO {
 private final String url,user,password;
 public ScoreDAO() {this(System.getenv().getOrDefault("DB_URL","jdbc:h2:./data/math_whiz_db;MODE=MySQL;DATABASE_TO_LOWER=TRUE"),System.getenv().getOrDefault("DB_USER","sa"),System.getenv().getOrDefault("DB_PASSWORD",""));}
 public ScoreDAO(String url,String user,String password) {this.url=url;this.user=user;this.password=password;}
 private Connection open() throws SQLException {return DriverManager.getConnection(url,user,password);}
 public void initialize() throws SQLException {
  try(Connection c=open();Statement s=c.createStatement()) {
   s.executeUpdate("CREATE TABLE IF NOT EXISTS game_scores (id INT AUTO_INCREMENT PRIMARY KEY, player_name VARCHAR(100) NOT NULL, score INT NOT NULL CHECK (score BETWEEN 0 AND 10), difficulty VARCHAR(20) NOT NULL, played_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");
  }
 }
 public GameScore save(String name,int score) throws SQLException {
  try(Connection c=open();PreparedStatement s=c.prepareStatement("INSERT INTO game_scores (player_name,score,difficulty) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
   s.setString(1,name);s.setInt(2,score);s.setString(3,"NORMAL");s.executeUpdate();
   try(ResultSet r=s.getGeneratedKeys()) {if(!r.next()) throw new SQLException("No score ID returned.");return find(c,r.getLong(1));}
  }
 }
 private GameScore find(Connection c,long id) throws SQLException {
  try(PreparedStatement s=c.prepareStatement("SELECT * FROM game_scores WHERE id=?")) {s.setLong(1,id);try(ResultSet r=s.executeQuery()){if(!r.next()) throw new SQLException("Saved score missing.");return row(r);}}
 }
 public List<GameScore> topTen() throws SQLException {
  List<GameScore> rows=new ArrayList<>();
  try(Connection c=open();PreparedStatement s=c.prepareStatement("SELECT * FROM game_scores ORDER BY score DESC, played_at ASC, id ASC LIMIT 10");ResultSet r=s.executeQuery()){while(r.next())rows.add(row(r));}return rows;
 }
 private GameScore row(ResultSet r) throws SQLException {return new GameScore(r.getLong("id"),r.getString("player_name"),r.getInt("score"),r.getString("difficulty"),r.getTimestamp("played_at").toLocalDateTime());}
}
