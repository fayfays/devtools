/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 KD World
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package devtools.pojotester;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * This class tests the getter and setter method of any POJO Java bean. 
 *
 * @author Raymond Tsang | 2fayfays@gmail.com
 */

public class PojoTester {
   private Random random = new Random(System.currentTimeMillis());
   private int stringLen = 128;

   public PojoTester() {
      this(128);
   }

   public PojoTester(int _stringLen) {
      this.stringLen = _stringLen;
   }

   public Map<String, Boolean> testBean(Object bean) {
      Map<String, Boolean> ret = new HashMap<String, Boolean>();
      Map<String, Method[]> methodMaps = getMethodMap(bean.getClass());

      for (Method[] m : methodMaps.values()) {
         Boolean result = testGetSet(bean, m);
         if (result != null) {
            ret.put(m[0].getName().substring(3), result);
         }
      }
      return ret;
   }

   protected Boolean testGetSet(Object bean, Method[] m) {
      Boolean ret = null;
      if (m != null && m[0] != null && m[1] != null) {
         Method getM = m[0];
         Method setM = m[1];
         if (setM.getParameterTypes() != null && setM.getParameterTypes().length == 1
               && setM.getParameterTypes()[0].equals(getM.getReturnType())) {
            Class<?> paramType = getM.getReturnType();
            if (paramType.equals(String.class)) {
               try {
                  String expect = getRandomString(this.stringLen);
                  setM.invoke(bean, expect);
                  String actual = (String) getM.invoke(bean);
                  ret = compareString(expect, actual);
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
            } else if (paramType.equals(int.class) || paramType.equals(Integer.class)) {
               try {
                  int expect = getRandomInt();
                  setM.invoke(bean, expect);
                  int actual = (int) getM.invoke(bean);
                  ret = compareInt(expect, actual);
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
            } else if (paramType.equals(long.class) || paramType.equals(Long.class)) {
               try {
                  long expect = getRandomLong();
                  setM.invoke(bean, expect);
                  long actual = (long) getM.invoke(bean);
                  ret = compareLong(expect, actual);
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
            } else if (paramType.equals(float.class) || paramType.equals(Float.class)) {
               try {
                  float expect = getRandomFloat();
                  setM.invoke(bean, expect);
                  float actual = (float) getM.invoke(bean);
                  ret = compareFloat(expect, actual);
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
            } else if (paramType.equals(double.class) || paramType.equals(Double.class)) {
               try {
                  double expect = getRandomDouble();
                  setM.invoke(bean, expect);
                  double actual = (double) getM.invoke(bean);
                  ret = compareDouble(expect, actual);
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
            } else {
               compareOther(getM, setM);
            }
         }
      }
      return ret;
   }

   protected Map<String, Method[]> getMethodMap(Class<?> clazz) {
      Map<String, Method[]> ret = new HashMap<String, Method[]>();
      Method[] methods = clazz.getMethods();
      for (Method m : methods) {
         if ((m.getName().startsWith("get") && !m.getName().equals("getClass")) || m.getName().startsWith("set")) {
            String name = m.getName().substring(3);
            String prefix = m.getName().substring(0, 3);
            Method[] valuePair = null;
            if (!ret.containsKey(name)) {
               valuePair = new Method[2];
               valuePair[0] = null;
               valuePair[1] = null;
               ret.put(name, valuePair);
            } else {
               valuePair = ret.get(name);
            }
            if (prefix.equals("get")) {
               valuePair[0] = m;
            } else if (prefix.equals("set")) {
               valuePair[1] = m;
            } else {
               throw new RuntimeException("Unknown method: " + prefix);
            }
         }
      }
      return ret;
   }

   protected int getRandomInt() {
      return random.nextInt();
   }

   protected long getRandomLong() {
      return random.nextLong();
   }

   protected float getRandomFloat() {
      return random.nextFloat();
   }

   protected double getRandomDouble() {
      return random.nextDouble();
   }

   protected String getRandomString(int len) {
      StringBuilder sb = new StringBuilder();
      while (sb.length() < len) {
         sb.append(UUID.randomUUID().toString());
      }
      return sb.substring(0, len - 1);
   }

   protected boolean compareInt(int a, int b) {
      return a == b;
   }

   protected boolean compareLong(long a, long b) {
      return a == b;
   }

   protected boolean compareFloat(float a, float b) {
      return a == b;
   }

   protected boolean compareDouble(double a, double b) {
      return a == b;
   }

   protected boolean compareString(String a, String b) {
      return a.equals(b);
   }

   protected boolean compareOther(Method getM, Method setM) {
      System.out.println("Missing implementation for non-primitive types: " + getM.getName() + "/" + setM.getName());
      return false;
   }

   public int getStringLen() {
      return stringLen;
   }

   public void setStringLen(int stringLen) {
      this.stringLen = stringLen;
   }
}
