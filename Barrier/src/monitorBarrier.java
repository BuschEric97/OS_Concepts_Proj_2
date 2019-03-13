import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class monitorBarrier implements Barrier
{
	int numThreads;
	int threadCount;
	final Lock lock = new ReentrantLock();
	final Condition atBarrier = lock.newCondition();

	public monitorBarrier(int N)
	{
		numThreads = N;
		threadCount = 0;
	}
	public synchronized void arriveAndWait()
	{
		threadCount++; // increment threadCount to indicate a new thread is at the barrier

		try
		{
			if (threadCount < numThreads)
				atBarrier.await(); // wait at barrier if not all threads are at barrier
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
