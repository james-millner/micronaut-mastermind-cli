PACKAGE = 'mastermind'

default: clean build

build-and-run: clean build run

clean:
	./gradlew clean

build:
	./gradlew build

run:
	java -jar build/libs/micronaut-mastermind-0.1.jar
