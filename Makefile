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

build-docker-image:
	# build docker image
	docker build -t capstone-app .

push-docker-image:
	# TODO push docker image to registry
	echo "pushing docker image"

build-push-docker-image: build-docker-image push-docker-image

run-local: lint build-app-run-tests build-docker-image
	docker run -it -p 80:8000 --rm capstone-app

deploy:
	echo "stage: Deploy"
