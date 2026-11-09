package com.mathwhiz.service;
public final class MathService {
 private MathService() {}
 public static int isPrime(int number) {
  if(number<2) return 0;
  for(int i=2;i<=number/i;i++) if(number%i==0) return 0;
  return 1;
 }
 public static int calculateMod(int a,int b) {
  if(b==0) throw new IllegalArgumentException("Divisor must not be zero.");
  return a%b;
 }
 private static double radians(double degree) {
  if(!Double.isFinite(degree)) throw new IllegalArgumentException("Angle must be finite.");
  return Math.toRadians(degree%360.0);
 }
 public static double calculateSin(double degree) { return Math.sin(radians(degree)); }
 public static double calculateCos(double degree) { return Math.cos(radians(degree)); }
 public static double calculateTan(double degree) {
  double r=radians(degree);
  if(Math.abs(Math.cos(r))<1e-12) throw new IllegalArgumentException("Tangent is undefined at this angle.");
  return Math.tan(r);
 }
 public static boolean close(double answer,double expected) {
  return Double.isFinite(answer)&&Math.abs(answer-expected)<=0.01;
 }
}
