# OS_Concepts_Proj_2

The working project is contained inside the Barrier/ directory. Compilation and execution of code is handled inside that directory.

Project Description:
Design and implement a barrier using (i) semaphores, and (ii) monitors. Your barrier should support one method, namely arriveAndWait( ). A thread invoking the method is blocked until all threads have arrived at the barrier (in other words, invoked the arriveAndWait( ) method of the barrier) at which point all threads are released from the barrier and can resume their respective executions. The barrier should be reusable, that is, threads can use the same barrier object multiple times to synchronize with each other. Ensure that your design and implementation of a barrier satisfies the following two properties: (a) safety: no thread is released from the barrier prematurely, and (b) liveness: once all threads have arrived at the barrier, all threads are released from the barrier eventually.
