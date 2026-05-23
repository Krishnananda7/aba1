pipeline {

    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }

    environment {
        IMAGE_NAME = "padmanabha18/expense-tracker"
        CONTAINER_NAME = "expense-tracker"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Padmanabha187/open-ended-1-.git',
                    credentialsId: 'github-token'
            }
        }

        stage('Build & Package') {
            steps {
                // Compiles, runs tests, and creates the JAR package in one go
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Login to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh """
                        echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin
                    """
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                sh "docker push ${IMAGE_NAME}:latest"
            }
        }

        stage('Run Container') {
            steps {
                sh """
                    docker stop ${CONTAINER_NAME} || true
                    docker rm ${CONTAINER_NAME} || true

                    docker run -dit -p 8080:8080 \
                        --name ${CONTAINER_NAME} \
                        ${IMAGE_NAME}:latest
                """
            }
        }
    }

    post {

        success {
            emailext (
                subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Pipeline succeeded!\nDocker image pushed & container running.",
                to: "padmanabha462@gmail.com"
            )
        }

        failure {
            emailext (
                subject: "FAILED: ${JOB_NAME} #${BUILD_NUMBER}",
                body: "Pipeline failed!\nCheck Jenkins logs: ${BUILD_URL}",
                to: "padmanabha462@gmail.com"
            )
        }
    }
}
