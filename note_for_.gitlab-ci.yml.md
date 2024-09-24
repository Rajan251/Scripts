image in job notification.

# you can define the used image per job
# this overrides the global defined image or defult image or default image

# local runner is used for testing purposes.
# we can configure any platform as a local runner over the server.
# example aws ec2 and vm and docker container.
# when you are done make sure to stop or delete your aws instance.


# using the tag we can decided which specif job will run on which runner.
# we different runner we can Distributing the lode among the runners.
# different runner types job need powershell, job need docker , job needs k8s.
# you can define the runner type in the runner definition.

# we can define multiple interchangeable runners.
# we can have multiple runner on a machine.
# when we install docker it will only accessible from root user and when we run this command it will make docker available for all user.

# sudo usermod -aG docker $USER
# runner assigned to that specific project.
# you can unlock and assign runner to other projects as well.


# To pick up and run a job, a runner must be assigned every tag listed in the job.
# if we add shell executor docker image will ignored.

# example:
#  run_unit_test:
#   tags:
#    - ec2
#    - docker      # these are the runner tag which to pick up and run a job,a runner must 
#    - remote     # - be assigned every tag listed in the job
#   image: python:3.8-slim-buster
#   stage: test
#   before_script:
#   - echo "Preparing test data"
#   - pip install -r requirements.txt

# if we run any job on runner we should configure our runner properly with dependencies.
# we can also have multiple interchnagable runner.

# for the security reasons, have full control over your infrastructure.

# the company teams's host their git repos & configure ci/cd piplines on the self-managed instance.

# keep runner updated to the latest version because gitlab.com is update continuously.

# we have three type of runner shared, specific, group,

# shared runner avilable to all groups and projects in gitlab instance.
# specific runner associated with specific projects.
# group runner avilable to all projects in a group.

# type of executor defines the environment in which the job gets executed.
# shell executor runs job on the os of the server directly.
# docker executor runs job in a docker container.
# kubernetes executor runs job in a kubernetes cluster.
# vm executor runs job in an already created vm.

# ssh : least supported

# flexibility of which job executes on which runner.
# with which executor.
# with which docker image, etc.
# runners are referenced using tags.



#########################################################################################


# Unit Tests = Testing individual code components rather than the code as a whole.
# static application security Testing(SAST) = SAST analyzes the code itself without actully executing the code.
# it scans your code for any security vulnerabilities.
# build docker images.
# push to docker registry.
# deploy to dev.
#  - we can use vm or aws ec2 as a development server.
# promote to staging 
# promote to PROD  

##########################################################################################

# in this session 32 we will do unit test ,build docker image , push to docker registry, deploy to dev.

# 1. main structure.
#    - how to increment and set images version of applicaiton dynamically.
#    - example: v1.0 ,v1.1 ,v2.0.
#  .gitlab-ci.yaml = Containing our ci/cd configuration.
#  Dockerfile = Consists of instrucations to build the Docker image.
#  /app= the actual application source code.
#  package.json = the heart of any node project defines all the dependencies and metadata about the project
# server.test.js = These are the unit tests we will execute in the ci/cd pipline.
# jest = A javaScript testing framework
# npm install = installs the required dependecncies for the application.
# npm start = Run the application
# npm test = Run the unit tests.
# .gitignore file = Tells Git which files to ignore when committing your Project to the repository.
# .dockerignore file = Tells Docker which files to ignore when building the Docker image.
# in .gitignore we mention files folder, which are temporary or aren't usefull to other team members.
# /app = The actual application source code.
# package.json = Defines the dependencies of the project.
# Dockerfile = Contains instructions to build the Docker image.
# server.test.js = These are the unit tests we will execute in the ci/cd pipline.
# jest = A JavaScript testing framework.
# npm start = Run the application.
# npm test = Run the unit tests.
# job can output an archive of files and directories.
# This output is called a job artifact.
# collects JUnit reports XML files.

# These are uploaded to Gitlab as an artifact.
# JUnit was orginally developed in java



# Different reports like test reports,
# code quality reports, security reports can be collected.
# every gitlab project can have its own space to store its docker images.

# gitlab offer buildin docker registry for every project.
# we can push our docker image to this registry.

# Gitlab strives to become a complete devops platform.
# platform on which you build your complete devops workflows.

# packages & registries = use Gitlab as a private or public registry for variety of supported package managers.
# Container Registry = Registry to store docker images.

# public vs private repositories 

# public > docker images stored in a public registry, can be used by everyone.
# private > Docker images stored in a private registry, can only be accessed by loggin into the doker repository.

# Registry URL
# registry.gitlab.com/username/projectname.

# package registry = use gitlab as a private or public registry for variety of supported package managers.
# container registry = Registry to store docker images.
# infrastructure Registry = private registry for Iac packages.


# before you can push images, you must authenticate with the container Registry.
# GitLab provides two ways to authenticate with a container Registry:
# - GitLab Container Registry (GCR)
# - GitLab Registry (GR)

# GitLab Container Registry (GCR) 
# - GitLab Container Registry (GCR) is a private container registry provided by GitLab.
# - It is available in all GitLab plans.
# - You can authenticate with GCR using your GitLab credentials.
# - You can push images to GCR using GitLab CI/CD.

# CI_REGISTRY_USER and CI_REGISTRY_PASSWORD.
# Gitlab provides temporary credentials for the Container Registry in your ci/cd pipline.
# Read-write access to the container Registry.
# Pre-define CI/CD variable variable values of these are only valid for 1 job only.

# No hard_coded values
# NO Code duplication 

# Your container registry can have multiple image repositories.
# Each image repository can store multiple image version(tags).
# root image is used for images with no name.
# it is gloable level images where all the images will push.

# Protected variables = variable only available when the pipline runs on a protected branch.
# Masked variables = Variable containing secrets should always be masked.
# With this, you avoid the risk of exposing the value of the variable, e.g. when outputing it in a job log like "echo $VARIABLE"

# in the automated pipline we want to disable this intrective step.

# on the dev-server we want to execute the docker Commands

# we use ec2 instance then we will use private_key.pem file of ec2 instance.
