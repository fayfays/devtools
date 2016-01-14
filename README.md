# devtools
This repository includes various kind of development tools for Java developers.

# pojo tester
The movtivation of creating this testing tool was to address the requirement of maintaining a high code coverage rate.  The tools save the developers a lot of work and time by testing POJO classes without manually creating one to one corresponding test class.

This class helps with testing the getter and setter method pairs.  It leverage Java reflections to find and match pairs of get and set methods from a given bean instance.  After passing the scanning stage, it will invoke the compare<Type> methods based on the parameter/return type of the method pairs and return a boolean whether the value matches or not.  The class method compareXYZ methods can be overriden accordingly, based on own customized needs.

The tester supports the following primitive types:  String, int, long , float, double, Integer, Long, Float and Double.  The compareOther method can also be overriden by the subclass whenever handling non-primitive types is needed.

# how to use
1. Check out the source code using the the git repository URL.
2. See the MyPojoTest.java in the test package as an example for your unit tests.  It is as simple as just one method call.
3. If you are using JUnit, here is an example:

```
@Test
public void test() {
   TestHelper.jUnitWrapper(new MyPojo());
}
```
Alternatively, if you are not using JUnit and you still want to test the POJO, you could invoke the PojoTester directly:

```
MyPojo anyPojoBean = new MyPojo();
Map<String, Boolean> result = new PojoTester().testBean(anyPojoBean);
for (String k : result.keySet()) {
   Assert.assertTrue("Property [" + k + "] failed to return the expected value, check implementation.", result.get(k));
   System.out.println(k + " --> " + result.get(k));
}
```
  
