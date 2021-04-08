package com.mini.wc

import org.apache.flink.api.scala.DataSet
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

/**
  * 批处理 world count
  */
object WorldCount {
  def main(args: Array[String]): Unit = {
    // 创建 flink 上下文
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    // 从文件中读取数据
    val inputPath = "/Users/apple/Documents/git/mini-server/mini-flink/src/main/resources/hello.txt"
    val inputDataSet: DataSet[String] = env.readTextFile(inputPath)

    // 先分词、在对词进行分组、最后聚合
//    val resultDataSet: DataSet[(String, Int)] = inputDataSet
//      .flatMap(_.split(" "))
//      .map((_, 1))
//      .groupBy(0)
//      .sum(1)

    // 按空格拆分单词，结果单词集合无序可重复，set 集合
    val r1 = inputDataSet.flatMap(_.split(" "))
    // 将单词集合每个元素转为(_, 1)形式，_代表元素，返回的是无序可重复key的 KV，V 是 1
    val r2 = r1.map((_, 1))
    // 按参数对无序可重复key的 KV 集合分组，参数代表 KV 位置，0 代表 K，1 代表 V，按参数排序,key 相同的 KV 相邻
    val r3= r2.groupBy(0)
    // 对 GroupedDataSet集合进行聚合，参数代表聚合计算元素，聚合后 key 唯一
    val r4 = r3.sum(1)
    // groupBy 后的 GroupedDataSet集合，参数表示 KV 出现次数，最小为 1，至少出现一次
    val r5 = r3.first(2)
    // 分别打印 r1\r2\r3\r4
    r4.print()
  }
}
