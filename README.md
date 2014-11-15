SearchEngineV1
==============
At the beginning of our project we decided to split up the work into four parts:

Allocate and Checkpoint: Mitchell Kinney /
KeyStorage and Node: Xiaomeng Yang /
DiskSpace: Khashi Reyes /
ValueStorage: Anu Ghimire /

Our project uses only one datastorage file defined in LinearMemoryDatabase class. This file holds both KeyStorage nodes and
ValueStorage areas (new class). Allocate class also have a bitSet file to save and store the java bitset that manage the free
and allocated chunk of the datastorage file. And DiskSpace is responsible for writing and reading data from the datastorage file.

Overall our integration tests failed. Tests that were made for individual classes were successful but unfortunately we were 
not able to combine them to perform well for the combination of the classes. Future work would be dedicated to following the
errors to figure out where the integration test failed.

