package com.proxiad.lawn

import javafx.geometry.Pos
import java.io.File
import java.nio.charset.Charset

@JvmField val spacesRegex: Regex = "\\s+".toRegex()

fun main(args: Array<String>) {
    val fileName = "src/resources/input.txt"
    val mowers: MutableList<Mower> = mutableListOf()
    val fieldWidth: Int
    val fieldHeight: Int

    val lines: List<String> = File(fileName).readLines(Charset.defaultCharset())
    val coordinates: List<Int> = lines[0].split(spacesRegex).map { string -> Integer.parseInt(string)}
    fieldWidth = coordinates[0]
    fieldHeight = coordinates[1]

    for (index in 1 until lines.size step 2) {
        val mowerPositionTokens: List<String> = lines[index].split(spacesRegex)

        val position = Position(Integer.parseInt(mowerPositionTokens[0]),
                Integer.parseInt(mowerPositionTokens[1]), mowerPositionTokens[2])
        mowers.add(Mower(lines[index + 1].split("").filter { direction -> !direction.isEmpty() }, position))
    }


}