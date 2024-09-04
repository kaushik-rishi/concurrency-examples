# Print in order

## Ways to solve
- busy wait + volatile keyword
- synchronized
  - synchronized method
  - synchronized block (this)
  - synchronized block (dummy object)
- semaphore implementation
- re-entrant lock with conditions

## Solutions that will fail
- busy wait
  - will fail due to visibility issues
  - changes made in one thread won't be visible to others
