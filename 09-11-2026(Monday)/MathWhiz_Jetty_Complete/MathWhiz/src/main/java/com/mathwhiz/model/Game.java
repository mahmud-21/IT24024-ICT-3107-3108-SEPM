package com.mathwhiz.model;
import com.mathwhiz.service.MathService;
import java.security.SecureRandom;
import java.util.UUID;
public record Game(String player,String token,int prime,int a,int b,String trig1,int angle1,String trig2,int angle2) {
 private static final SecureRandom R=new SecureRandom();
 public static Game create(String name) {
  String[] ops={"sin","cos","tan"}; int[] angles={0,30,45,60,120,135,150,180,225,300,315};
  int a=R.nextInt(101)-50,b; do {b=R.nextInt(19)-9;} while(b==0);
  return new Game(name,UUID.randomUUID().toString(),R.nextInt(102)-1,a,b,ops[R.nextInt(3)],angles[R.nextInt(angles.length)],ops[R.nextInt(3)],angles[R.nextInt(angles.length)]);
 }
 public String[] questions() {return new String[]{"Is "+prime+" prime? (1 = prime, 0 = not prime)","Calculate "+a+" % "+b,"Calculate "+trig1+"("+angle1+"°)","Calculate "+trig2+"("+angle2+"°)"};}
 private double trig(String op,int angle) {return switch(op) {case "sin" -> MathService.calculateSin(angle);case "cos" -> MathService.calculateCos(angle);default -> MathService.calculateTan(angle);};}
 public double[] expected() {return new double[]{MathService.isPrime(prime),MathService.calculateMod(a,b),trig(trig1,angle1),trig(trig2,angle2)};}
 public int score(double[] answers) {
  if(answers.length!=4) throw new IllegalArgumentException("Four answers required.");
  double[] e=expected();int total=0;
  for(int i=0;i<4;i++) if(i<2?answers[i]==e[i]:MathService.close(answers[i],e[i])) total+=i<2?2:3;
  return total;
 }
}
