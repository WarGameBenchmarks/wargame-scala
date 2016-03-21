import Card._
import Deck._


class Benchmark {

  val MS = 1000000
  val NS = 1000000000

  def benchmark() {

    /**
     * Pretend this is 4 threads, if this were finished.
     *
     *
     * On my 2011MBA, this setup will produce ~2000 games across
     * 4 threads, about 500 each.
     * With 1 thread, this same setup produces ~2000.
     *
     * I suppose that implementing the Message Passing | Actor
     * system from Scala might be insightful, but at this point,
     * with the performance so poor, this might as well be a dead end.
     *
     * Thanks for reading though.
     */

    val thread1 = new WarGameThread
    val thread2 = new WarGameThread
    val thread3 = new WarGameThread
    val thread4 = new WarGameThread
    println("before start")
    thread1.start
    thread2.start
    thread3.start
    thread4.start
    println("before delay")
    Thread.sleep(1000)
    println("after delay")
    thread1.end
    thread2.end
    thread3.end
    thread4.end
    println("after end")
  }


}
