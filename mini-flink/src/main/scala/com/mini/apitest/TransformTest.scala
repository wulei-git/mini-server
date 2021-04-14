package com.mini.apitest

import org.apache.flink.api.common.functions.ReduceFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.api.scala._

import scala.collection.Seq

case class SensorReading( id: String, timestamp: Long, temperature: Double )

object TransformTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val inputDataStream = env.readTextFile("/Users/apple/Documents/git/mini-server/mini-flink/src/main/resources/sensor.txt")
    val dataStream = inputDataStream
      .map(
        data => {
          val dataArray = data.split(",")
          SensorReading( dataArray(0).trim, dataArray(1).trim.toLong, dataArray(2).trim.toDouble )
        }
      )

    // 分组聚合，输出传感器当前最小值
    // min minBy 的区别，minBy 最小值元素出现后，后续元素值大于最小值，就直接再输出最小值元素
    // min 只改变元素最小值，其余属性和第一个 key 相同的元素保持一致
    val aggStream = dataStream
      .keyBy("id")
      .minBy("temperature")

//    aggStream.print()

    // 输出当前最小温度值，以及最近时间戳，使用 reduce

    val resultStrem = dataStream
        .keyBy("id")
//        .reduce(
//          (curState, newDate) =>
//            SensorReading(curState.id, newDate.timestamp, curState.temperature.min(newDate.temperature))
//        )
        .reduce(new MyReduceFunction)

//    resultStrem.print()

    // 分流，split, 按 30 度为分界线分流
    val splitDataStreamm = dataStream
        .split(
          data => {
            if (data.temperature > 30.0) Seq("high") else Seq("low")
          }
        )

    // 选举 高温流 低温流
    val highStream = splitDataStreamm.select("high")
    val lowStream = splitDataStreamm.select("low")
//    highStream.print()

    // 合流， connect
    val warningsStream = highStream.map(data => (data.id, data.temperature))

    val connectedStream = warningsStream.connect(lowStream)

    // connect 合成的流在合流中依然按原流分组存在，使用 map 可以按合流加入顺序处理
    val coMapResultStream: DataStream[Any] = connectedStream
        .map(
          warningsData => (warningsData._1, warningsData._2, "warning"),
          lowData => (lowData.id, lowData.temperature, "healthy")
        )

    coMapResultStream.print("coMap")

    env.execute("keyBy test")

  }

}

class MyReduceFunction extends ReduceFunction[SensorReading] {
  override def reduce(t: SensorReading, t1: SensorReading): SensorReading =
    SensorReading(t.id, t1.timestamp,t.temperature.min(t1.temperature))
}
