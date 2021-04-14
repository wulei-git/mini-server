package com.mini.wc

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

object StreamWordCount {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // flink 开发环境（其他容器环境默认配置为 1）默认并行度为 CPU 核心数，如果OS启用了超线程技术，并行度会*2，可以自定义任务线程数（并行度）
    // 任务由那个线程执行，使用 key 的 HashCode 映射，效果就是一个 key 只会被一个线程处理，但一个线程会处理多个 key
    // key 乱序因为数据在各种算子任务中传递分区造成，将全局并行度设置为 1 可以处理，但这样失去高并发特性
//    env.setParallelism(8)

    // 全局禁止合并任务链
//    env.disableOperatorChaining()

    // 使用执行参数封装数据源 IP 端口
//    val parameterTool: ParameterTool = ParameterTool.fromArgs(args)
//    val host: String = parameterTool.get("host")
//    val port: Int = parameterTool.getInt("port")

    // nc -lk 7777 启动 7777 端口输入 enter 向 7777 端口发生数据
    // socket 流的并行度只能为 1
    val inputDataStream: DataStream[String] = env.socketTextStream("localhost", 7777)

    // 可以为每一个算子设置独立的并行度, 用的少
    // disableChaining 禁止该算子任务进行合并（任务特殊，消耗资源），会增加任务数
    // startNewChain 开启新的任务链
    // slotSharingGroup 共享组,让某些计算量大的算子独享CPU
    val resultDataStream: DataStream[(String, Int)] = inputDataStream
      .flatMap(_.split(" ")).slotSharingGroup("A")
      .filter(_.nonEmpty).disableChaining()
      .map((_, 1)).startNewChain()
      .keyBy(0)
      .sum(1)

    // 写同一个文件时设置一个线程按顺序写入避免文本混乱
    resultDataStream.print().setParallelism(1)
    // 流式任务，基于事件驱动，需要开启任务
    env.execute("stream word count")
    // 启动后 flink 监听流式数据源，收到数据驱动任务执行
  }
}
