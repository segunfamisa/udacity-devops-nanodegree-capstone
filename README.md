# Udacity Cloud DevOps Nanodegree Capstone Project


# Provisioning Jenkins Server
The Jenkins instance is run on an Ubuntu server that is provisioned using CloudFormation.

* Add your machine public address to the `SshIpAddress` json parameter value in `.system/aws/jenkins-params.json`
* On the AWS console, create a KeyPair to be associated with the Jenkins instance.
* Add the name of the keypair to the `KeyName` json parameter value in `.system/aws/jenkins-params.json`
* cd into the `.system/aws` directory for convenience
* Run `./create.sh <infra-stack-name> infra.yml infra-params.json` to create the network infra resources (replace with update or delete as the case may be)
* Run `./create.sh <jenkins-stack-name> jenkins.yml jenkins-params.json` to create the Jenkins server.
* Wait a few seconds to check out the output section of the CloudFormation stack console, to determine what the DNS & IP addresses of the new Jenkins server are.
* The first time you set up Jenkins server, you will have to SSH into the server to retrieve the default password in order to continue to manually configure Jenkins via the UI.