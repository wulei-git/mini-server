package com.init.mini.refer.util

object FunctionTest {

  val funcTest = (x : Int) => x + 3;

  val f1 = (x:Int,y:Int) => x + y

  def m1(x : Int, y : Int) : Int = x * y;


  def main(args: Array[String]): Unit = {
    val r1 = m1(f1)
    println(r1)

    print(m1(1, 2))
  }

  def methodTest2(x : Int) : Int = {
    return x * x;
  }

  def m1(f:(Int,Int) => Int) : Int = {
    f(2,6)
  }


}
