package com.init.mini.refer.util

abstract class MethodTest {

  /**
    * 如果你不写等于号和方法主体，那么方法会被隐式声明为抽象(abstract)，包含它的类型于是也是一个抽象类型。
    *
    * @param x
    * @return
    */
  def methodTest (x : Int) : Int

  def methodTest2(x : Int) : Int = {
    return x * x;
  }
}
