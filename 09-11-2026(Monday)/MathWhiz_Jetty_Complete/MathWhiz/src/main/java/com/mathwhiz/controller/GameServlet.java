package com.mathwhiz.controller;
import com.mathwhiz.dao.ScoreDAO;
import com.mathwhiz.model.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class GameServlet extends HttpServlet {
 private final ScoreDAO dao;
 public GameServlet(ScoreDAO dao){this.dao=dao;}
 public static String escape(String s){return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;").replace("'","&#39;");}
 private String title(){return escape(System.getenv().getOrDefault("GAME_TITLE","MAHMUD – MATH WHIZ"));}
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException {
  res.setHeader("Cache-Control","no-store");res.setHeader("X-Content-Type-Options","nosniff");
  String path=req.getRequestURI();
  try {
   switch(path){
    case "/" -> welcome(res,"");
    case "/game" -> {Game g=(Game)req.getSession().getAttribute("game");if(g==null)res.sendRedirect("/");else game(res,g,"");}
    case "/result" -> {Result r=(Result)req.getSession().getAttribute("result");if(r==null)res.sendRedirect("/");else result(res,r);}
    case "/leaderboard" -> leaderboard(res);
    default -> res.sendError(404);
   }
  }catch(SQLException e){log("Database operation failed",e);res.setStatus(503);page(res,"Database unavailable","<h1>Scores are temporarily unavailable</h1><p>Check your database connection and try again.</p><a href='/'>Home</a>");}
 }
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException {
  req.setCharacterEncoding("UTF-8");HttpSession session=req.getSession();
  synchronized(session){
   if(req.getRequestURI().equals("/start")) {
    String name=req.getParameter("playerName");name=name==null?"":name.strip();
    if(name.isEmpty()||name.length()>100){res.setStatus(400);welcome(res,"Enter a player name of 1–100 characters.");return;}
    session.setAttribute("game",Game.create(name));session.removeAttribute("result");res.sendRedirect("/game");return;
   }
   if(!req.getRequestURI().equals("/submit")){res.sendError(404);return;}
   Game g=(Game)session.getAttribute("game");
   if(g==null){res.sendRedirect(session.getAttribute("result")==null?"/":"/result");return;}
   if(!g.token().equals(req.getParameter("token"))){res.sendError(400,"Expired game form.");return;}
   double[] answers=new double[4];
   try {
    answers[0]=Integer.parseInt(req.getParameter("answer0"));if(answers[0]!=0&&answers[0]!=1)throw new IllegalArgumentException();
    answers[1]=Integer.parseInt(req.getParameter("answer1"));
    for(int i=2;i<4;i++){answers[i]=Double.parseDouble(req.getParameter("answer"+i));if(!Double.isFinite(answers[i]))throw new IllegalArgumentException();}
   }catch(IllegalArgumentException|NullPointerException e){res.setStatus(400);game(res,g,"Answer every question. Q1 accepts 0 or 1; Q2 accepts an integer; Q3 and Q4 accept finite decimals.");return;}
   try {
    GameScore score=dao.save(g.player(),g.score(answers));
    session.setAttribute("result",new Result(g,answers,score));session.removeAttribute("game");res.sendRedirect("/result");
   }catch(SQLException e){log("Score could not be saved",e);res.setStatus(503);game(res,g,"Your score was not saved. Check the database and submit again.");}
  }
 }
 private void welcome(HttpServletResponse res,String error)throws IOException {
  page(res,"Welcome","<div class='eyebrow'>JAVA SERVLET + JDBC · NORMAL</div><h1>Four questions.<br>One perfect ten.</h1><p class='lead'>Test your number sense, remainders and trigonometry.</p>"+error(error)+"<form method='post' action='/start'><label for='name'>Player name</label><input id='name' name='playerName' maxlength='100' required placeholder='Enter your name' autocomplete='name'><button>Start game →</button></form><div class='tiles'><span>01 · PRIME <b>2 marks</b></span><span>02 · MODULUS <b>2 marks</b></span><span>03–04 · TRIG <b>6 marks</b></span></div><p>Angles use degrees. Round decimal answers to 2–3 decimal places. Tolerance: ±0.01.</p>");
 }
 private String error(String e){return e.isEmpty()?"":"<p role='alert' class='error'>"+escape(e)+"</p>";}
 private void game(HttpServletResponse res,Game g,String error)throws IOException {
  StringBuilder b=new StringBuilder("<div class='eyebrow'>NORMAL · TOTAL 10 MARKS</div><h1>Your challenge</h1><p>Player: "+escape(g.player())+" · All angles are in degrees.</p>"+error(error)+"<form method='post' action='/submit'><input type='hidden' name='token' value='"+g.token()+"'>");
  String[] q=g.questions();for(int i=0;i<4;i++)b.append("<div class='question'><label for='a").append(i).append("'><small>QUESTION ").append(i+1).append(" · ").append(i<2?2:3).append(" MARKS</small>").append(escape(q[i])).append("</label><input id='a").append(i).append("' name='answer").append(i).append("' type='number' step='").append(i<2?"1":"any").append("' ").append(i==0?"min='0' max='1'":"").append(" required placeholder='Your answer'></div>");
  b.append("<p>Decimal tolerance: ±0.01. Negative modulus follows Java's % operator.</p><button>Submit answers →</button></form>");page(res,"Game",b.toString());
 }
 private record Result(Game game,double[] answers,GameScore score) {}
 private void result(HttpServletResponse res,Result r)throws IOException {
  StringBuilder b=new StringBuilder("<div class='eyebrow'>SCORE SAVED · NORMAL</div><h1>Well played, "+escape(r.game.player())+".</h1><div class='score'>"+r.score.score()+"<span>/ 10</span></div><p>Here is how your answers compare.</p><div class='table'><table><tr><th>Question</th><th>Your answer</th><th>Calculated answer</th><th>Marks</th></tr>");
  double[] e=r.game.expected();String[] q=r.game.questions();for(int i=0;i<4;i++){boolean ok=i<2?r.answers[i]==e[i]:com.mathwhiz.service.MathService.close(r.answers[i],e[i]);b.append("<tr><td>").append(escape(q[i])).append("</td><td>").append(fmt(r.answers[i])).append("</td><td>").append(fmt(e[i])).append("</td><td>").append(ok?(i<2?2:3):0).append("</td></tr>");}
  b.append("</table></div><p><a class='button' href='/leaderboard'>View leaderboard</a> <a href='/'>Play again</a></p>");page(res,"Result",b.toString());
 }
 private String fmt(double d){return String.format(Locale.ROOT,"%.4f",d);}
 private void leaderboard(HttpServletResponse res)throws IOException,SQLException {
  StringBuilder b=new StringBuilder("<div class='eyebrow'>THE TOP TEN</div><h1>Leaderboard</h1><p>Highest score first. For ties, the earlier attempt ranks first.</p><div class='table'><table><tr><th>Rank</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Played at (server time)</th></tr>");
  int rank=0;for(GameScore s:dao.topTen())b.append("<tr><td>").append(++rank).append("</td><td>").append(escape(s.playerName())).append("</td><td><b>").append(s.score()).append(" / 10</b></td><td>").append(escape(s.difficulty())).append("</td><td>").append(s.playedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("</td></tr>");
  b.append("</table></div>");if(rank==0)b.append("<p>No scores yet. Be the first to play.</p>");b.append("<p><a class='button' href='/'>Start a new game →</a></p>");page(res,"Leaderboard",b.toString());
 }
 private void page(HttpServletResponse res,String screen,String body)throws IOException {
  res.setContentType("text/html;charset=UTF-8");res.getWriter().write("<!doctype html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1'><title>"+escape(screen)+" | "+title()+"</title><style>"+CSS+"</style></head><body><header><a class='brand' href='/'>∑ &nbsp;"+title()+"</a><a href='/leaderboard'>Leaderboard ↗</a></header><main>"+body+"</main><footer>MATHEMATICAL THINKING. ONE CHALLENGE AT A TIME.<br>Java · Jetty · JDBC</footer></body></html>");
 }
 private static final String CSS="""
 *{box-sizing:border-box}body{margin:0;background:#f3f5f0;color:#18372f;font:16px/1.6 system-ui,sans-serif}header{padding:24px 6%;display:flex;justify-content:space-between;gap:20px;border-bottom:1px solid #d7e0d5}a{color:#17674e}a.brand{text-decoration:none;font-weight:800}main{max-width:1000px;margin:48px auto;padding:42px;background:white;border:1px solid #dce3da;border-radius:22px;box-shadow:0 18px 50px #16352e08}h1{font-size:clamp(30px,5vw,58px);line-height:1.1;letter-spacing:-2px;margin:20px 0}.eyebrow,small{font-size:12px;letter-spacing:2px;font-weight:700;color:#64816d}.lead{font-size:20px;color:#587063}label{display:block;font-weight:600;margin:18px 0 8px}input{display:block;width:100%;max-width:480px;padding:14px;border:1px solid #adbeae;border-radius:9px;font:inherit}button,.button{display:inline-block;background:#17674e;color:white;padding:14px 24px;border:0;border-radius:9px;font:inherit;font-weight:700;cursor:pointer;text-decoration:none;margin-top:20px}button:hover,.button:hover{background:#104d3a}.tiles{display:flex;gap:16px;margin:30px 0}.tiles span{background:#f3f5f0;padding:18px;flex:1;border-radius:12px;font-size:13px}.tiles b{display:block;font-size:20px}.question{padding:12px 0 22px;border-bottom:1px solid #e4e9e1}small{display:block}.score{font-size:100px;font-weight:800;color:#17674e}.score span{font-size:35px;color:#819388}table{border-collapse:collapse;width:100%;font-size:14px;text-align:left}td,th{padding:16px 12px;border-bottom:1px solid #dce3da}th{background:#eef3eb}.table{overflow-x:auto;margin:26px 0}.error{background:#fff0ed;padding:15px;color:#9c3024;border-radius:9px}footer{text-align:center;font-size:11px;letter-spacing:2px;color:#748576;padding:0 20px 36px}@media(max-width:700px){main{margin:20px 12px;padding:24px}header{font-size:13px;padding:18px}.tiles{flex-direction:column;gap:8px}h1{letter-spacing:-1px}}
 """;
}
