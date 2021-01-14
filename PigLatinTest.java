import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PigLatinTest {

   @Before public void setUp() {
   }
   
   @Test public void myTests(){
      Assert.assertEquals("The pig latin version of pig is igpay.", "igpay", PigLatin.pig("pig"));
      Assert.assertEquals("The pig latin version of latin is atinlay.", "atinlay", PigLatin.pig("latin"));
      Assert.assertEquals("The pig latin version of this is isthay.", "isthay", PigLatin.pig("this"));
      Assert.assertEquals("The pig latin version of strange is angestray.", "angestray", PigLatin.pig("strange"));
      Assert.assertEquals("The pig latin version of bcdfgh is INVALID.", "INVALID", PigLatin.pig("bcdfgh"));
      Assert.assertEquals("The pig latin version of apple is appleway", "appleway", PigLatin.pig("apple"));
      Assert.assertEquals("The pig latin version of eye is eyeway", "eyeway", PigLatin.pig("eye"));
      Assert.assertEquals("The pig latin version of question is estionquay", "estionquay", PigLatin.pig("question"));
      Assert.assertEquals("The pig latin version of squeeze is eezesquay", "eezesquay", PigLatin.pig("squeeze"));
      Assert.assertEquals("The pig latin version of yes is esyay.", "esyay", PigLatin.pig("yes"));
      Assert.assertEquals("The pig latin version of rhyme is ymerhay", "ymerhay", PigLatin.pig("rhyme"));
      Assert.assertEquals("The pig latin version of Thomas is Omasthay", "Omasthay", PigLatin.pig("Thomas"));
      Assert.assertEquals("The pig latin version of Jefferson is Effersonjay", "Effersonjay", PigLatin.pig("Jefferson"));
      Assert.assertEquals("The pig latin version of What? is Atwhay?", "Atwhay?", PigLatin.pig("What?"));
      Assert.assertEquals("The pig latin version of Oh! is Ohway!", "Ohway!", PigLatin.pig("Oh!"));
      Assert.assertEquals("The pig latin version of \"hello\" is \"ellohay\"", "\"ellohay\"", PigLatin.pig("\"hello\""));
      Assert.assertEquals("The pig latin version of Hi!!??!? is Ihay!!??!?", "Ihay!!??!?", PigLatin.pig("Hi!!??!?"));

   }
}
