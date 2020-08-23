# Udacity Cloud DevOps Nanodegree Capstone Project

This project is the capstone project for the Udacity Cloud DevOps Nanodegree, and this is a simple project
to demonstrate understanding of the concepts in the course.
This project deploys a simple API server written in Kotlin using the Ktor framework.

# Submission
The screenshots relevant for this project are in the [screenshots](screenshots) folder.
See the rubrics here: https://review.udacity.com/#!/rubrics/2577/view

# Using CloudFormation to provision the infrastructure
The folder [.system/aws](.system/aws) contains all the parameters and templates to provision the following infrastructure:

* cd into the `.system/aws` directory for convenience 
* Run `./create.sh <infra-stack-name> infra.yml infra-params.json` to create the network infra resources (replace with update or delete as the case may be)
* Update [.system/aws/jenkins-params.json](.system/aws/jenkins-params.json) with the correct SshIpAddress to be the public IP
from which you wish to SSH into the Jenkins server.
* Run `./create.sh <jenkins-stack-name> jenkins.yml jenkins-params.json` to create the Jenkins server.
* Run `./create.sh <eks-cluster-stack-name> eks-cluster.yml eks-cluster-params.json` to create the EKS cluster configuration.

# Configuring up the Jenkins Server
The Jenkins instance is run on an Ubuntu server that is provisioned using CloudFormation.

* Add your machine public address to the `SshIpAddress` json parameter value in `.system/aws/jenkins-params.json`
* On the AWS console, create a KeyPair to be associated with the Jenkins instance.
* Add the name of the keypair to the `KeyName` json parameter value in `.system/aws/jenkins-params.json`
* Wait a few seconds to check out the output section of the CloudFormation stack console, to determine what the DNS & IP addresses of the new Jenkins server are.
* The first time you set up Jenkins server, you will have to SSH into the server to retrieve the default password in order to continue to manually configure Jenkins via the UI.
* Set up [Dockerhub](https://hub.docker.com) credentials called "docker-hub-credentials" on Jenkins 
* Install `aws-iam-authenticator` & `kubectl` on the Ubuntu machine and set configure it to use credentials that are
the same as the ones used to create the app.

# Understanding the Pipeline
The pipeline is a 4-stage one, that includes steps for `Lint`, `Build app & Run tests`, `Build & Push Docker Image`, and `Deploy`  
