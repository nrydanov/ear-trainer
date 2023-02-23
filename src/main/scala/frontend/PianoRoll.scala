package frontend

import java.awt.{Color, Dimension, Graphics, Graphics2D, GridLayout}
import javax.swing.JPanel

class PianoRoll(x_left: Int, y_bottom: Int, width: Int, lowest_pitch: Int, highest_pitch: Int) extends JPanel {

  private val number_of_notes = highest_pitch - lowest_pitch + 1
  private val cellWidth = 60
  private val cellHeight = 20
  private val numRows = number_of_notes
  private val numCols = width / cellWidth

  private def createCells(): Unit = {
    for (row <- 0 until numRows; col <- 0 until numCols) {
      val cell = new Cell(row, col, cellWidth, cellHeight)
      this.add(cell)
    }
  }

  this.setLayout(new GridLayout(numRows, numCols))
  this.setPreferredSize(new Dimension(width, number_of_notes * cellHeight))
  createCells()

  private def convertIntToNote(pitch: Int): String = {

    val reversed = highest_pitch - pitch
    val octave = (reversed / 12) - 1
    val noteNames = Array("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
    val noteName = noteNames(reversed % 12)

    s"$noteName$octave"
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    val g2d = g.asInstanceOf[Graphics2D]

    g2d.setColor(Color.WHITE)
    g2d.fillRect(x_left, y_bottom, getWidth, getHeight)

    g2d.setColor(Color.BLACK)
    for (row <- 0 until numRows) {
      g2d.drawString(convertIntToNote(row), 0, row * cellHeight)
    }
  }
}
