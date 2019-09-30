all: compile experiment clean

compile:
	javac squareMatrixGenerator.java
	javac naiveMatrixMultiplication.java
	javac recursiveMatrixMultiplication.java
	javac strassensMatrixMultiplication.java
	javac expMatrixMultiplication.java

experiment:
	for n in 2 4 8 16 32 64 128 254 512 1024 2048 4096 8192; do \ #
	echo "" > ../out/res01.csv
	java expMatrixMultiplication $$n -100 100 42 10 n >> ../out/res01.csv \ done
	# size(n) rangeStart rangeEnd seed REPS header

clean:
	rm -f *.class