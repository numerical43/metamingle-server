pipeline {
    agent any
    tools {
        jdk 'Java 11'
        gradle 'Gradle 8.3'
    }
    triggers {
        githubPush()
    }
    stages {
        stage ('Main Build') {
            when { branch 'main' }
            stages {
                stage('Checkout') {
                    steps {
                        checkout scm
                    }
                }
                stage('Build') {
                    steps {
                        withCredentials([file(credentialsId: 'application-yml', variable: 'SECRETS_APPLICATION'),
                                         file(credentialsId: 'meta-mingle-firebase-key', variable: 'SECRETS_FIREBASE')]) {
                            script {
                                bat "copy %SECRETS_APPLICATION% C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\meta-mingle\\src\\main\\resources\\application.yml"

                                bat "copy %SECRETS_FIREBASE% C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\meta-mingle\\src\\main\\resources\\meta-mingle-firebase-key.json"
                            }
                        }

                        bat(script: 'gradlew clean build', returnStatus: true)
                    }
                }
                stage('Deploy') {
                    steps {
                        withCredentials([
                            string(credentialsId: 'deploy-dir', variable: 'DEPLOY_DIR'),
                            string(credentialsId: 'docker-image-name', variable: 'DOCKER_IMAGE_NAME'),
                            string(credentialsId: 'docker-container-name', variable: 'DOCKER_CONTAINER_NAME'),
                            usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                            script {

                                bat "copy /Y build\\libs\\meta-mingle-0.0.1-SNAPSHOT.jar %DEPLOY_DIR%"

                                bat "docker build -t %DOCKER_IMAGE_NAME% ."

                                bat "docker login -u %DOCKERHUB_USERNAME% -p %DOCKERHUB_PASSWORD%"

                                // 기존 컨테이너를 중지하고 제거
                                bat "docker stop %DOCKER_CONTAINER_NAME% || (echo Container not running or does not exist. & exit 0)"
                                bat "docker rm %DOCKER_CONTAINER_NAME% || (echo Container not running or does not exist. & exit 0)"

                                // DockerHub에 생성한 이미지 push
                                bat "docker push %DOCKER_IMAGE_NAME%"

                                // Docker 이미지로 새 컨테이너 실행
                                bat "docker run -d --name %DOCKER_CONTAINER_NAME% -p 8080:8080 %DOCKER_IMAGE_NAME%"
                            }
                        }
                    }
                }
            }
        }
    }
}