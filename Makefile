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
	docker build -t segunfamisa/capstone-app .

login-to-docker:
	# Login to dockerhub
	echo $(password) | docker login -u $(username) --password-stdin

push-docker-image:
	# Tag docker image
	docker tag $(dockerpath) $(dockerpath):$(build_number)

	# Pushing docker image to dockerhub
	docker push "$(dockerpath):$(build_number)"

run-local: lint build-app-run-tests build-docker-image
	docker run -it -p 80:8000 --rm segunfamisa/capstone-app

deploy:
	# update kubeconfig
	aws eks update-kubeconfig --name capstone-EksCluster

	# apply config map
	kubectl apply -f .system/k8s/auth.yml

	# apply deployment
	kubectl apply -f .system/k8s/deployment.yml

	# apply service
	kubectl apply -f .system/k8s/service.yml

	# list services
	kubectl get service -o wide
