class WarGameThread extends Thread {

  var processed: Int = 0
  var terminate: Boolean = false

  override def run {
    val game = new WarGame
    while (!terminate) {
      game.play
      processed += 1
      print("-")
    }
    println("done: " + processed)
  }

  /**
   * End this thread.
   *
   * This was previously called terminate() but
   * it overlapped with the terminate property.
   */
  def end() {
    this.terminate = true
  }

}

object WarGameThread {

  def isAlive(threads: Array[WarGameThread]):Boolean = {
    var life = false
    for (t <- threads) {
      if (t.isAlive) {
        life = true
      }
    }
    return life
  }

  def getCompleted(threads: Array[WarGameThread]):Int = {
    var processed = 0
    for (t <- threads) {
      processed += t.processed
    }
    return processed
  }

  def create(count: Int):Array[WarGameThread] = {
    val threads = new Array[WarGameThread](count)
    for (i <- 0 until count) {
      threads(i) = new WarGameThread
    }
    return threads
  }

  def launch(threads: Array[WarGameThread]) {
    for (t <- threads) {
      t.start
    }
  }

  def endAll(threads: Array[WarGameThread]) {
      for (t <- threads) {
        t.end
      }
  }

}
