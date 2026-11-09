package com.mathwhiz;
import com.mathwhiz.controller.GameServlet;
import com.mathwhiz.dao.ScoreDAO;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
public class Main {
 public static void main(String[] args) throws Exception {
  ScoreDAO dao=new ScoreDAO(); dao.initialize();
  Server server=new Server(Integer.parseInt(System.getenv().getOrDefault("PORT","8080")));
  ServletContextHandler context=new ServletContextHandler(ServletContextHandler.SESSIONS);
  context.setContextPath("/"); context.addServlet(new ServletHolder(new GameServlet(dao)),"/*");
  server.setHandler(context); server.setStopAtShutdown(true); server.start(); server.join();
 }
}
