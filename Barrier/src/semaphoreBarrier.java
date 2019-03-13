import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class semaphoreBarrier implements Barrier
{
	Semaphore atBarrier;
	AtomicInteger numThreads;
	AtomicInteger threadCount;

	public semaphoreBarrier(int N)
	{
		atBarrier = new Semaphore(1);
		numThreads = new AtomicInteger(N);
		threadCount = new AtomicInteger(0);
	}

	public void arriveAndWait()
	{
		threadCount.incrementAndGet(); // increment threadCount to indicate a new thread is at the barrier

		try
		{
			if (threadCount.intValue() < numThreads.intValue())
				atBarrier.acquire(); // wait at barrier if not all threads are at barrier
			else
				atBarrier.release(); // release all threads if all threads are at barrier
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}

		threadCount.decrementAndGet(); // decrement threadCount to indicate thread has left the barrier
	}
}
