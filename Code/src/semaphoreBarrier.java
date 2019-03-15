import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class semaphoreBarrier implements Barrier
{
	Semaphore mutex;
	Semaphore beforeBarrier;
	Semaphore atBarrier;
	AtomicInteger numThreads;
	AtomicInteger threadCount;

	public semaphoreBarrier(int N)
	{
		mutex = new Semaphore(1);
		atBarrier = new Semaphore(0);
		beforeBarrier = new Semaphore(0);
		numThreads = new AtomicInteger(N);
		threadCount = new AtomicInteger(0);
	}

	public void arriveAndWait()
	{
		try
		{
			mutex.acquire();
				threadCount.incrementAndGet(); // increment threadCount to indicate a new thread is at the barrier

				if (threadCount.intValue() == numThreads.intValue())
					beforeBarrier.release(numThreads.intValue()); // release all threads waiting before the barrier (including this thread)
			mutex.release();

			beforeBarrier.acquire(); // wait before the barrier if less than numThreads amount of threads are waiting to get to barrier

			mutex.acquire();
				threadCount.decrementAndGet(); // decrement threadCount to indicate thread has left the barrier

				if (threadCount.intValue() == 0)
					atBarrier.release(numThreads.intValue()); // release all threads waiting at the barrier (including this thread)
			mutex.release();

			atBarrier.acquire(); // wait at the barrier if less than numThreads amount of threads are waiting to get to barrier
		}
		catch (InterruptedException e)
		{
			System.out.println(e); // handle any exceptions that may be thrown
		}
	}
}
