# devtools
# devtools
This repository includes various kind of development tools for Java developers.

# pojo tester
This class help testing any getter and setter method pairs sharing the same primitive type.  It supports the following prmitive types:  String, int and float

# how to use
1. Simple copy and rename the PojoTester.java to any pakckage and invole the testBean meethod by passing an instance of any POJO i style class.
2. In your test code, invokee the method  testtBean, for example:
  MyPojoBean bean = new MyPojoBean();
  PojoTester.testBean(bean);

It will show the following results:

3. Replace with your desire asserts methods instead of printing the fields.


