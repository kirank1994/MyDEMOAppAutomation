pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9'
        jdk 'JDK-21'
    }

    parameters {
        string(name: 'PLATFORM', defaultValue: 'ANDROID', description: 'Target platform (ANDROID or IOS)')
        string(name: 'TAG', defaultValue: 'regression', description: 'Test group to run (e.g., regression, E2E)')
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
                    mvn clean test \
                    -Dplatform=${params.PLATFORM} \
                    -Dtag=${params.TAG} \
                    -DsuiteXmlFile=${params.SUITE}
                """
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
            echo "Build completed"
        }
        failure {
            echo "Build failed!"
        }
    }
}