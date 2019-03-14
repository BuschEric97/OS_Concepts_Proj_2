import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class monitorBarrier implements Barrier
{
	int numThreads;
	int threadCount;
	final Object atBarrier = new Object();

	public monitorBarrier(int N)
	{
		numThreads = N;
		threadCount = 0;
	}
	public void arriveAndWait()
	{
		synchronized (atBarrier)
		{
			threadCount++; // increment threadCount to indicate a new thread is at the barrier

			try
			{
				if (threadCount < numThreads)
					atBarrier.wait(); // wait at barrier if not all threads are at barrier
				else
					atBarrier.notifyAll(); // release all threads if all threads are at barrier
			}
			catch (InterruptedException e)
			{
				System.out.println(e);
			}

			threadCount--; // decrement threadCount to indicate thread has left the barrier
		}
	}
}
