pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9'
        jdk 'JDK-21'
    }

    parameters {
        string(name: 'SUITE', defaultValue: 'testNGSuites/testng_ANDROID_Jenkins.xml', description: 'TestNG suite file')
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

        stage('Run Tests') {
            steps {
                sh """
                    mvn clean test -DsuiteXmlFile=${params.SUITE}
                """
            }
        }

        stage('Archive Reports') {
            steps {
                // Archive all report files including HTML, XML, screenshots, etc.
                archiveArtifacts artifacts: '**/test-output/**/*.*', allowEmptyArchive: true

                // Publish JUnit-compatible testng-results.xml
                junit '**/test-output/testng-results.xml'
            }
        }
    }

    post {
        always {
            echo "✅ Build completed"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}