# devtools
This repository includes various kind of development tools for Java developers.

# pojo tester
The movtivation of creating this testing tool was to address the requirement of maintaining a igh ccode overage rate.  It saves  the developer a lot of work by testing POJO classes  without creating  one to one test classes.

This class helps testing getter and setter method pairs.  It simply retrieves a pair of get and set methods from the given bean instance which sharing the same name suffix, that is the property name.  Next it will invoke the compare<Type> method based on the parameter/return type of the method and return the result.  The compareXYZ methods can be overriden according for your customized needs.

The tester supports the following prmitive types:  String, int, long , float, double.  The compareOther method can be overriden by subclass when handling specific property types.

# how to use
1. Check out the source code from github: "git@github.com:fayfays/devtools
2. See the MyPojoTest.java in the test package as an example for your unit tests.  It is as simple as one method call.
3. In your test code, invoke the method testBean, for example:

Here is the sample POJO class:

  public class MyPojo {
     private int i;
     private long l;
     private float f;
     private double d;
     private Integer i2;
     private Long l2;
     private Float f2;
     private Double d2;
     private String s;
  
     public int getI() {
        return i;
     }
  
     public void setI(int i) {
        this.i = i;
     }
  
     public long getL() {
        return l;
     }
  
     public void setL(long l) {
        this.l = l;
     }
  
     public float getF() {
        return f;
     }
  
     public void setF(float f) {
        this.f = f;
     }
  
     public double getD() {
        return d;
     }
  
     public void setD(double d) {
        this.d = d;
     }
  
     public Integer getI2() {
        return i2;
     }
  
     public void setI2(Integer i2) {
        this.i2 = i2;
     }
  
     public Long getL2() {
        return l2;
     }
  
     public void setL2(Long l2) {
        this.l2 = l2;
     }
  
     public Float getF2() {
        return f2;
     }
  
     public void setF2(Float f2) {
        this.f2 = f2;
     }
  
     public Double getD2() {
        return d2;
     }
  
     public void setD2(Double d2) {
        this.d2 = d2;
     }
  
     public String getS() {
        return s;
     }
  
     public void setS(String s) {
        this.s = s;
     }
  }

Then invoke the test method using JUnit as follow:

   @Test
   public void test() {
      TestHelper.jUnitWrapper(new MyPojo());
   }

Alternatively, if you are not using JUnit and still want to test the POJO, you could invoke the PojoTester directly:

  MyPojo anyPojoBean = new MyPojo();
  Map<String, Boolean> result = new PojoTester().testBean(anyPojoBean);
  for (String k : result.keySet()) {
     Assert.assertTrue("Property [" + k + "] failed to return the expected value, check implementation.", result.get(k));
     System.out.println(k + " --> " + result.get(k));
  }
  
