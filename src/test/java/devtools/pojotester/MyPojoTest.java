package devtools.pojotester;

import org.junit.Test;

/**
 * This class demos how to test a POJO bean.  Developer should create a corresponding test class
 * for each POJO class.  The implementation is as simple as the class illustrated here.
 */
public class MyPojoTest {

   @Test
   public void test() {
      TestHelper.jUnitWrapper(new MyPojo());
   }
}
