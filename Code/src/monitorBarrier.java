import java.util.concurrent.Semaphore;

public class monitorBarrier implements Barrier
{
	int numThreads;
	int threadCount;
	final Object beforeBarrier = new Object(); // the main condition variable
	Semaphore atBarrier; // add another condition variable as a semaphore

	public monitorBarrier(int N)
	{
		numThreads = N;
		threadCount = 0;

		atBarrier = new Semaphore(0); // initialize the semaphore condition variable
	}
	public void arriveAndWait()
	{
		synchronized (beforeBarrier)
		{
			try
			{
				threadCount++; // increment threadCount to indicate a new thread is at the barrier
				if (threadCount == numThreads)
					beforeBarrier.notify(); // notify one of the threads waiting before the barrier

				beforeBarrier.wait(); // wait before the barrier
				beforeBarrier.notify(); // notify one more thread waiting before the barrier. Doing it this way allows for numThreads threads to pass

				threadCount--; // decrement threadCount to indicate thread has left the barrier
			}
			catch (InterruptedException e)
			{
				System.out.println(e);
			}
		}

		try
		{
		if (threadCount == 0)
			atBarrier.release(numThreads); // notify all of the threads waiting at the barrier

		atBarrier.acquire(); // wait at the barrier
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
	}
}
