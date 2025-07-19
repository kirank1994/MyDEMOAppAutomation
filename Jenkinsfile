pipeline {
    agent any

    tools {
        maven 'Maven-3.9'       // Name configured in Jenkins > Global Tool Config
        jdk 'JDK-24'            // Name configured in Jenkins > Global Tool Config
    }

    environment {
        // Set environment variables as needed
        JAVA_HOME = "${tool 'JDK-24'}"
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