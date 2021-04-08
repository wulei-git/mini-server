package com.init.mini.refer

object Main {
  /* 这是我的第一个 Scala 程序
   * 以下程序将输出'Hello World!'
   */
  def main(args: Array[String]) {
    var str =
      """one
        |two
        |three
      """.stripMargin
    println(str)

    var str2 : String = "this is string var def"
    println(str2)

    val str3 : String = "this is string val def"

    println(str3)

    val funcTest = (x : Int) => x + 3;

    val env = StreamExec

  }

  /**
    * 定义方法
    *
    * @param x
    * @return
    */
  def methodTest(x: Int) = x+3

}