# devtools
This repository includes various kind of development tools for Java developers.

# pojo tester
The movtivation of creating this testing tool was to address the requirement of maintaining a igh ccode overage rate.  It saves  the developer a lot of work by testing POJO classes  without creating  one to one test classes.

This class helps testing getter and setter method pairs.  It simply retrieves a pair of get and set methods from the given bean instance which sharing the same name suffix, that is the property name.  Next it will invoke the compare<Type> method based on the parameter/return type of the method and return the result.  The compareXYZ methods can be overriden according for your customized needs.

The tester supports the following prmitive types:  String, int, long , float, double.  The compareOther method can be overriden by subclass when handling specific property types.

# how to use
1. Check out the source code using the URL provided by github.
2. See the MyPojoTest.java in the test package as an example for your unit tests.  It is as simple as one method call.
3. Invoke the test method if you are using JUnit as follow:

```
@Test
public void test() {
   TestHelper.jUnitWrapper(new MyPojo());
}
```
Alternatively, if you are not using JUnit and still want to test the POJO, you could invoke the PojoTester directly:

```
MyPojo anyPojoBean = new MyPojo();
Map<String, Boolean> result = new PojoTester().testBean(anyPojoBean);
for (String k : result.keySet()) {
   Assert.assertTrue("Property [" + k + "] failed to return the expected value, check implementation.", result.get(k));
   System.out.println(k + " --> " + result.get(k));
}
```
  
