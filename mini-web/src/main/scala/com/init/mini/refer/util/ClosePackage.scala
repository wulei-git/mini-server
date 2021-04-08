package com.init.mini.refer.util

object ClosePackage {

  def main(args: Array[String]): Unit = {
    print(func(2))
  }

  var factor = 3
  val func = (i : Int) => i * factor
}
