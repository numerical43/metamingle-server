pipeline {
    agent any
    tools {
        jdk 'Java 11'
        gradle 'Gradle 8.3'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                withCredentials([file(credentialsId: 'application-yml', variable: 'SECRETS_APPLICATION')]) {
                    script {
                        bat "copy %SECRETS_APPLICATION% C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\test\\src\\main\\resources\\application.yml"
                    }
                }

                withCredentials([file(credentialsId: 'meta-mingle-firebase-key', variable: 'SECRETS_FIREBASE')]) {
                    script {
                        bat "copy %SECRETS_FIREBASE% C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\test\\src\\main\\resources\\meta-mingle-firebase-key.json"
                    }
                }

                bat(script: 'gradlew clean build', returnStatus: true)
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def deployDir = "C:\\Users\\user\\Desktop\\metamingle\\"
                    def dockerImageName = 'numerical43/meta-mingle'
                    def dockerContainerName = 'meta-mingle-container'

                    // JAR 파일 복사
                    bat "copy /Y build\\libs\\meta-mingle-0.0.1-SNAPSHOT.jar ${deployDir}"

                    // Docker 이미지 빌드
                    bat "docker build -t ${dockerImageName} ."

                    // Docker 이미지를 Docker Hub에 푸시
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        bat "docker login -u %DOCKERHUB_USERNAME% -p %DOCKERHUB_PASSWORD%"

                        // 기존 컨테이너를 중지하고 제거
                        def isRunningContainer = bat(script: "docker ps -qa --filter name=${dockerContainerName}")

                        if (isRunningContainer) {
                            echo "Stopping and removing existing ${dockerContainerName}..."
                            bat(script: "docker stop ${dockerContainerName}")
                            bat(script: "docker rm ${dockerContainerName}")
                        } else {
                            echo "No running containers with the name ${dockerContainerName} found."
                        }

                        // 기존 이미지 제거
                        def imageExists = bat(script: "docker images -q ${dockerImageName}")

                        if (imageExists) {
                            echo "Docker image exists. Removing..."
                            bat "docker rmi ${dockerImageName}"
                        } else {
                            echo "Docker image does not exist."
                        }
                        def danglingImages = bat(script: "docker images -f "dangling=true" -q)")
                        bat(script: "docker rmi ${danglingImages}")

                        // DockerHub에 생성한 이미지 push
                        bat "docker push ${dockerImageName}"

                        // Docker 이미지로 새 컨테이너 실행
                        bat "docker run -d --name ${dockerContainerName} -p 8080:8080 ${dockerImageName}"
                    }
                }
            }
        }
    }
}
