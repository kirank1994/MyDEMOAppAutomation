pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9'     // Name configured in Jenkins
        jdk 'JDK-21'            // Name configured in Jenkins
    }

    environment {
        JAVA_HOME = "${tool 'JDK-21'}"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/kirank1994/MyDEMOAppAutomation.git', branch: 'master'
            }
        }

        stage('Build & Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'reports/**/*.html', allowEmptyArchive: true
                junit 'test-output/testng-results.xml'
            }
        }
    }

    post {
        always {
            echo "Build finished!"
        }
        failure {
            echo "Build failed!"
        }
    }
}