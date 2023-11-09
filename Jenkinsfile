pipeline {
    agent any
    tools {
        jdk 'Java 1.8'
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
                sh './gradlew clean build'
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                    cp build/libs/meta-mingle-0.0.1-SNAPSHOT C:/Users/user/Desktop/meta mingle/
                    nohup java -jar C:/Users/user/Desktop/meta mingle/meta-mingle-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
                '''
            }
        }
    }
}
