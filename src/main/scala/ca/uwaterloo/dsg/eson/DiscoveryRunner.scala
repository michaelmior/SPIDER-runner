/*
 * Copyright 2017-2019 Michael Mior
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.uwaterloo.dsg.eson

import de.metanome.algorithm_integration.result_receiver.InclusionDependencyResultReceiver
import de.metanome.algorithms.spider.SPIDERFile
import de.metanome.backend.input.file.DefaultFileInputGenerator
import scopt.OParser

import java.io.File

case class Config(filename: String = "")

object DiscoveryRunner {
  def main(args: Array[String]): Unit = {
    val builder = OParser.builder[Config]
    val parser = {
      import builder._
      OParser.sequence(
        programName("discovery-runner"),
        opt[String]('f', "filename")
          .required()
          .action((x, c) => c.copy(filename = x))
          .text("Input filename"),
      )
    }

    OParser.parse(parser, args, Config()) match {
      case Some(config) =>
        val receiver = new PrintingInclusionDependencyReceiver
        run(config.filename, receiver)

        System.exit(0)
      case _ =>
    }
  }

  def run(filename: String, receiver: InclusionDependencyResultReceiver) {
    val spider = new SPIDERFile()
    spider.setRelationalInputConfigurationValue(SPIDERFile.Identifier.INPUT_FILES.name(), new DefaultFileInputGenerator(new File(filename)))
    spider.setResultReceiver(receiver)
    spider.execute
  }
}
