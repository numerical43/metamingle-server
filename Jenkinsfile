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
                withCredentials([
                    file(credentialsId: 'deploy-dir', variable: 'deployDir'),
                    file(credentialsId: 'docker-image-name', variable: 'dockerImageName'),
                    file(credentialsId: 'docker-container-name', variable: 'dockerContainerName')])
                script {

                    // JAR 파일 복사
                    bat "copy /Y build\\libs\\meta-mingle-0.0.1-SNAPSHOT.jar %deployDir%"

                    // Docker 이미지 빌드
                    bat "docker build -t %dockerImageName% ."

                    // Docker 이미지를 Docker Hub에 푸시
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        bat "docker login -u %DOCKERHUB_USERNAME% -p %DOCKERHUB_PASSWORD%"

                        // 기존 컨테이너를 중지하고 제거
                        def dockerPsOutput = bat(script: "docker ps -a --filter name=%dockerContainerName%", returnStdout: true).trim()

                        if (dockerPsOutput.contains(dockerContainerName)) {
                            // 기존 컨테이너를 중지하고 제거
                            bat "docker stop %dockerContainerName% > nul 2>&1 || (echo Container not running or does not exist. & exit 0)"
                            bat "docker rm %dockerContainerName% > nul 2>&1 || (echo Container not running or does not exist. & exit 0)"
                        } else {
                            echo "컨테이너가 없습니다."
                        }

                        // DockerHub에 생성한 이미지 push
                        bat "docker push %dockerImageName%"

                        // Docker 이미지로 새 컨테이너 실행
                        bat "docker run -d --name %dockerContainerName% -p 8080:8080 %dockerImageName%"
                    }
                }
            }
        }
    }
}