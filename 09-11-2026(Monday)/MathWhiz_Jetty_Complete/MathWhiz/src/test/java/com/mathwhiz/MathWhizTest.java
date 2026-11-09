package com.mathwhiz;
import com.mathwhiz.service.MathService;
import com.mathwhiz.model.Game;
import com.mathwhiz.dao.ScoreDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class MathWhizTest {
 @Test void prime(){assertEquals(1,MathService.isPrime(7));assertEquals(0,MathService.isPrime(8));assertEquals(0,MathService.isPrime(1));assertEquals(0,MathService.isPrime(-7));assertEquals(1,MathService.isPrime(2));assertEquals(1,MathService.isPrime(Integer.MAX_VALUE));}
 @Test void mod(){assertEquals(2,MathService.calculateMod(17,5));assertEquals(-2,MathService.calculateMod(-17,5));assertEquals(2,MathService.calculateMod(17,-5));assertThrows(IllegalArgumentException.class,()->MathService.calculateMod(7,0));}
 @Test void trig(){assertEquals(.5,MathService.calculateSin(30),1e-12);assertEquals(.5,MathService.calculateCos(60),1e-12);assertEquals(1,MathService.calculateTan(45),1e-12);assertThrows(IllegalArgumentException.class,()->MathService.calculateTan(90));assertThrows(IllegalArgumentException.class,()->MathService.calculateTan(270));assertThrows(IllegalArgumentException.class,()->MathService.calculateSin(Double.NaN));assertFalse(MathService.close(Double.NaN,0));assertTrue(MathService.close(.509,.5));assertFalse(MathService.close(.511,.5));}
 @Test void scoring(){Game g=new Game("Test","token",7,17,5,"sin",30,"tan",45);assertEquals(10,g.score(new double[]{1,2,.5,1}));assertEquals(0,g.score(new double[]{0,3,8,8}));assertEquals(8,g.score(new double[]{0,2,.5,1}));}
 @Test void jdbc()throws Exception{ScoreDAO dao=new ScoreDAO("jdbc:h2:mem:tests;MODE=MySQL;DB_CLOSE_DELAY=-1","sa","");dao.initialize();var saved=dao.save("O'Neil <student>",10);assertEquals("O'Neil <student>",saved.playerName());assertEquals("NORMAL",saved.difficulty());assertNotNull(saved.playedAt());for(int i=0;i<12;i++)dao.save("Player "+i,i%10);var top=dao.topTen();assertEquals(10,top.size());assertEquals(10,top.get(0).score());for(int i=1;i<top.size();i++)assertTrue(top.get(i-1).score()>=top.get(i).score());}
}
