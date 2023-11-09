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

                        bat(script: 'dir /s /b')
                        bat "copy %SECRETS_APPLICATION src\\main\\resources\\application.yml"
                    }
                }

                withCredentials([file(credentialsId: 'meta-mingle-firebase-key', variable: 'SECRETS_FIREBASE')]) {
                    script {
                        bat "copy %SECRETS_FIREBASE src\\main\\resources\\meta-mingle-firebase-key.json"
                    }
                }

                bat(script: 'gradlew clean build', returnStatus: true)
            }
        }
        stage('Terminate Process') {
            steps {
                script {
                    echo "Start terminate process."

                    def jarName = "meta-mingle-0.0.1-SNAPSHOT.jar"
                    def jpsOutput = bat(script: 'jps -m', returnStatus: true).toString().trim()
                    def processLine = jpsOutput.readLines().find { it.contains(jarName) }

                    if (processLine) {
                        def processParts = processLine.split(' ')
                        def pid = processParts[0]

                        def killCommand = "taskkill /F /PID ${pid}"
                        bat script: killCommand, returnStatus: true

                        if (currentBuild.resultIsBetterOrEqualTo('FAILURE')) {
                            error("Failed to terminate the process.")
                        } else {
                            echo "Terminated process with PID ${pid}."
                        }
                    } else {
                        echo "No process found for ${jarName}."
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def jarName = "meta-mingle-0.0.1-SNAPSHOT.jar"
                    def jarPath = "build\\libs\\${jarName}"
                    def deployDir = "C:\\Users\\user\\Desktop\\metamingle\\"

                    // JAR 파일 복사
                    bat "copy /Y ${jarPath} ${deployDir}"

                    // Spring Boot 애플리케이션 실행
                    def startCommand = "start java -jar \"${deployDir}${jarName}\" > \"${deployDir}application.log\" 2>&1"

                    echo startCommand
                    bat startCommand
                }
            }
        }
    }
}