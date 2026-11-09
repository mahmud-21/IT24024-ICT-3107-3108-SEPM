package com.mathwhiz;
import com.mathwhiz.controller.GameServlet;
import com.mathwhiz.dao.ScoreDAO;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.ee10.servlet.*;
import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.util.regex.*;
import static org.junit.jupiter.api.Assertions.*;
class WebFlowTest {
 private String request(LocalConnector c,String method,String path,String cookie,String body)throws Exception {
  return c.getResponse(method+" "+path+" HTTP/1.1\r\nHost: localhost\r\n"+(cookie.isEmpty()?"":"Cookie: "+cookie+"\r\n")+"Content-Type: application/x-www-form-urlencoded\r\nContent-Length: "+body.length()+"\r\nConnection: close\r\n\r\n"+body);
 }
 private String capture(String name,String response)throws Exception {
  assertTrue(response.startsWith("HTTP/1.1 200"),response);String html=new String(response.substring(response.indexOf("<!doctype")).getBytes(java.nio.charset.StandardCharsets.ISO_8859_1),java.nio.charset.StandardCharsets.UTF_8);
  Path dir=Path.of("screen-previews");Files.createDirectories(dir);Files.writeString(dir.resolve(name+".html"),html);return html;
 }
 private String group(String text,String regex){Matcher m=Pattern.compile(regex).matcher(text);assertTrue(m.find(),regex);return m.group(1);}
 @Test void completeGameAndValidation()throws Exception {
  ScoreDAO dao=new ScoreDAO("jdbc:h2:mem:web;MODE=MySQL;DB_CLOSE_DELAY=-1","sa","");dao.initialize();Server server=new Server();LocalConnector connector=new LocalConnector(server);server.addConnector(connector);
  ServletContextHandler context=new ServletContextHandler(ServletContextHandler.SESSIONS);context.setContextPath("/");context.addServlet(new ServletHolder(new GameServlet(dao)),"/*");server.setHandler(context);server.start();
  try {
   capture("home",request(connector,"GET","/","",""));
   assertTrue(request(connector,"POST","/start","","playerName=").startsWith("HTTP/1.1 400"));
   String start=request(connector,"POST","/start","","playerName=Demo+Player");assertTrue(start.startsWith("HTTP/1.1 302"));String cookie=group(start,"(?i)Set-Cookie: ([^;\\r]+)");
   String game=capture("game",request(connector,"GET","/game",cookie,""));String token=group(game,"name='token' value='([^']+)'");
   assertTrue(request(connector,"POST","/submit",cookie,"token="+token+"&answer0=1&answer1=2.5&answer2=NaN&answer3=0").startsWith("HTTP/1.1 400"));
   int n=Integer.parseInt(group(game,"Is (-?\\d+) prime"));int prime=com.mathwhiz.service.MathService.isPrime(n);
   Matcher mod=Pattern.compile("Calculate (-?\\d+) % (-?\\d+)").matcher(game);assertTrue(mod.find());int rem=Integer.parseInt(mod.group(1))%Integer.parseInt(mod.group(2));
   Matcher trig=Pattern.compile("Calculate (sin|cos|tan)\\((-?\\d+)[^)]*\\)").matcher(game);StringBuilder body=new StringBuilder("token="+token+"&answer0="+prime+"&answer1="+rem);int i=2;
   while(trig.find()){double a=Math.toRadians(Integer.parseInt(trig.group(2)));double value=switch(trig.group(1)){case "sin"->Math.sin(a);case "cos"->Math.cos(a);default->Math.tan(a);};body.append("&answer").append(i++).append("=").append(java.net.URLEncoder.encode(Double.toString(value),java.nio.charset.StandardCharsets.UTF_8));}
   assertEquals(4,i);assertTrue(request(connector,"POST","/submit",cookie,body.toString()).startsWith("HTTP/1.1 302"));String result=capture("result",request(connector,"GET","/result",cookie,""));assertTrue(result.contains("class='score'>10"));
   request(connector,"GET","/result",cookie,"");request(connector,"POST","/submit",cookie,body.toString());assertEquals(1,dao.topTen().size());capture("leaderboard",request(connector,"GET","/leaderboard",cookie,""));
  }finally{server.stop();}
 }
}
