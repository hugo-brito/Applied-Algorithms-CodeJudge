all: compile experiment clean

compile:
	javac squareMatrixGenerator.java
	javac naiveMatrixMultiplication.java
	javac recursiveMatrixMultiplication.java
	javac strassensMatrixMultiplication.java
	javac expMatrixMultiplication.java

experiment:
#	for REPS in 100; do \ #
	echo "" > ../out/res01.csv
	java expMatrixMultiplication 1024 -100 100 42 10 y >> ../out/res01.csv

clean:
	rm -f *.class