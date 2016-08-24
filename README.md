# netsim


This repository contains skeleton code for the implementation of a simple network simulator. 
It's used for teaching/testing purposes.

Major classes:
  - ``Network``: the network itself
  - ``NetworkPath``: represents the path a packet takes when traveling between to network elements

How to use:
  - ``mvn compile``: Compiles the entire project
  - ``mvn test``: Runs all the test cases
  - ``mvn package``: Package the contents into a jar file
  - ``mvn exec:java -Dexec.mainClass=com.feedzai.netsim.example.Main``: Executes the demo application

