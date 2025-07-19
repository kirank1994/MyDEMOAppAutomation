pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9'
        jdk 'JDK-21'
    }

    parameters {
        choice(
            name: 'SUITE',
            choices: [
                'testng_ANDROID_E2E.xml',
                'testng_IOS_E2E.xml',
                'testng_ANDROID_Regression.xml',
                'testng_IOS_Regression.xml'
            ],
            description: 'Select the TestNG suite XML file to run'
        )
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
                archiveArtifacts artifacts: '**/reports/*.html', allowEmptyArchive: true
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