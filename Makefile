lint:
	# run lint on the code
	./gradlew ktlintCheck

	# lint the hadolint
	''docker run --rm -i hadolint/hadolint < Dockerfile''

build-app-run-tests:
	# clean build
	./gradlew clean build

	# run tests
	./gradlew test

build-push-docker-image:
	echo "stage: Build and Push Docker Image"

deploy:
	echo "stage: Deploy"
