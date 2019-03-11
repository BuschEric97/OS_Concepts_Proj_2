package com.concretepage.semaphore.binary;
import java.util.concurrent.Semaphore;

public class semaphoreBarrier implements Barrier
{
	Semaphore atBarrier;
	int numThreads;
	int threadCount;

	public semaphoreBarrier(int N)
	{
		atBarrier = new Semaphore(1);
		numThreads = N;
		threadCount = 0;
	}

	public void arriveAndWait()
	{
		threadCount++; // increment threadCount to indicate a new thread is at the barrier

		if (threadCount < numThreads)
			atBarrier.acquire(); // wait at barrier if not all threads are at barrier
		else
			atBarrier.release(); // release all threads if all threads are at barrier

		threadCount--; // decrement threadCount to indicate thread has left the barrier
	}
}
